package status.subtitles.nlp.stanford;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import status.subtitles.nlp.NLPProcessor;
import status.subtitles.nlp.NLPWord;

public class StanfordNLP extends NLPProcessor {

	StanfordCoreNLP pipeline = null;

	public StanfordNLP(String videoID) {
		super(videoID);
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");
		pipeline = new StanfordCoreNLP(props, false);

	}

	@Override
	protected List<String> parseSentences(String text) {
		List<String> sentences = new ArrayList<String>();
		Annotation document = pipeline.process(text);

		for (CoreMap sentence : document.get(SentencesAnnotation.class)) {
			sentences.add(sentence.toString());
		}

		return sentences;
	}

	@Override
	protected List<NLPWord> parseWords(String sentece) {

		Annotation document = pipeline.process(sentece);
		List<NLPWord> words = new ArrayList<NLPWord>();

		for (CoreMap sentence2 : document.get(SentencesAnnotation.class)) {

			for (CoreLabel token : sentence2.get(TokensAnnotation.class)) {

				NLPWord nlpWord = new NLPWord();
				nlpWord.setWord(token.word());
				nlpWord.setLemma(token.get(LemmaAnnotation.class));
				nlpWord.setPos(token.get(PartOfSpeechAnnotation.class));

				words.add(nlpWord);
				
			}
		}
		return words;
	}

}
