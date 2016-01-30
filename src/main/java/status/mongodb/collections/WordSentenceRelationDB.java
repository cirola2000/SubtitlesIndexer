package status.mongodb.collections;

import status.mongodb.MongoSuperClass;

public class WordSentenceRelationDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "WordSentenceRelation";

	public WordSentenceRelationDB() {
		super(COLLECTION_NAME);
	}

	public static String WORD_ID = "wordID";
	
	public static String SENTENCE_ID = "sentenceID";

	public String getWordID() {
		return getField(WORD_ID).toString();
	}
	
	public String getSentenceID() {
		return getField(SENTENCE_ID).toString();
	}

	public void setWordID(String wordID) {
		addField(WORD_ID, wordID);
	}

	public void setSentenceID(String sentenceID) {
		addField(SENTENCE_ID, sentenceID);
	}
	

}
