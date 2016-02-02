package status.subtitles.nlp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import status.mongodb.collections.CaptionDB;
import status.mongodb.collections.LemmaDB;
import status.mongodb.collections.POSDB;
import status.mongodb.collections.SentenceDB;
import status.mongodb.collections.WordDB;
import status.mongodb.collections.WordSentenceRelationDB;
import status.subtitles.stringOperations.Normalize;
import subtitleFile.Caption;

public abstract class NLPProcessor {

	private String subtitleID;

	public NLPProcessor(String subtitleID) {
		this.subtitleID = subtitleID;
	}

	protected abstract List<String> parseSentences(String text);

	protected abstract List<NLPWord> parseWords(String sentece);

	public void processCaptions(ArrayList<Caption> captions) throws Exception {

		for (Caption caption : captions) {
			List<String> senteces = parseSentences(caption.content);

			// save the caption
			CaptionDB captionDB = new CaptionDB();
			captionDB.setStart(caption.start.toString());
			captionDB.setEnd(caption.end.toString());
			captionDB.setSubtitleID(getSubtitleID());
			captionDB.update(true);

			for (String sentence : senteces) {

				// save the sentence
				SentenceDB sentenceDB = new SentenceDB();
				sentenceDB.setSentence(sentence);
				sentenceDB.setCaptionID(captionDB.getID());
				sentenceDB.update(true);

				List<NLPWord> words = parseWords(sentence);
				for (NLPWord nlpWord : words) {

					// normalizing words
					Normalize normalize = new Normalize();
					String word = normalize.normalize(nlpWord.getWord());

					// check lemma before save
					String lemma = nlpWord.getLemma();
					String pos = nlpWord.getPos();
					if (checkStopWords(pos)) {
						LemmaDB lemmaDb = new LemmaDB(lemma);

						// save each word
						WordDB wordDB = new WordDB();
						wordDB.setWord(word);
						wordDB.setLemmaID(lemmaDb.getID());
						wordDB.update(true);

						// get POS tagging
						POSDB posDB = new POSDB(pos);
						
						// make relation between word and sentence
						WordSentenceRelationDB relation = new WordSentenceRelationDB();
						relation.setSentenceID(sentenceDB.getID());
						relation.setWordID(wordDB.getID());
						relation.setPOSID(posDB.getID());

						// check whether is there a relation already (that might
						// happen when the word appears multiple times in the
						// sentence)
						if (!relation.find(false))
							relation.update(true);
					}
				}
			}
		}
	}

	private boolean checkStopWords(String pos) {

		HashSet<String> lemmaStopWords = new HashSet<String>();
		lemmaStopWords.add(".");
		lemmaStopWords.add(",");
		lemmaStopWords.add(":");
		lemmaStopWords.add("\"");
		lemmaStopWords.add("''");
		lemmaStopWords.add("``");

		if (lemmaStopWords.contains(pos))
			return false;

		return true;
	}

	public String getSubtitleID() {
		return subtitleID;
	}

	public void setSubtitleID(String subtitleID) {
		this.subtitleID = subtitleID;
	}

}