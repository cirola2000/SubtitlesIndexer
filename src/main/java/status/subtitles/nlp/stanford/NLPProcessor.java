package status.subtitles.nlp.stanford;

import java.util.ArrayList;
import java.util.List;

import status.mongodb.collections.CaptionDB;
import status.mongodb.collections.SentenceDB;
import status.mongodb.collections.WordDB;
import status.mongodb.collections.WordSentenceRelationDB;
import status.subtitles.stringOperations.Normalize;
import subtitleFile.Caption;

public abstract class NLPProcessor {
	
	private String videoID;
	
	public NLPProcessor(String videoID) {
		this.videoID  = videoID;
	}

	protected abstract List<String> parseSentences(String text);

	protected abstract List<String> parseWords(String sentece);


	public void processCaptions(ArrayList<Caption> captions) throws Exception {

		for (Caption caption : captions) {
			List<String> senteces = parseSentences(caption.content);
			
			// save the caption
			CaptionDB captionDB = new CaptionDB();
			captionDB.setStart(caption.start.toString());
			captionDB.setEnd(caption.end.toString());
			captionDB.setVideoID(getVideoID());
			captionDB.update(true);
			
			for(String sentence: senteces){
				
				// save the sentence
				SentenceDB sentenceDB = new SentenceDB();
				sentenceDB.setSentence(sentence);
				sentenceDB.setCaptionID(captionDB.getID());
				sentenceDB.update(true);
				
				List<String> words = parseWords(sentence);
				for(String word: words){
					
					// normalizing words
					Normalize normalize = new Normalize(word);
					word = normalize.normalize();
					
					// save each word
					WordDB wordDB = new WordDB();
					wordDB.setWord(word);
					wordDB.update(true);
					
					// make relation between word and sentence
					WordSentenceRelationDB relation = new WordSentenceRelationDB();
					relation.setSentenceID(sentenceDB.getID());
					relation.setWordID(wordDB.getID());
					relation.update(true);
				}
			}
		}
	}

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	
	

}