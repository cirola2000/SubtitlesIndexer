package status.mongodb.collections;

import status.mongodb.MongoSuperClass;

public class WordDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Words";
	
	public static String WORD = "word";

	public WordDB() {
		super(COLLECTION_NAME);
		addMandatoryField(WORD);
		addKey(WORD);
	}	
	
	public WordDB(String word) {
		super(COLLECTION_NAME);
		addMandatoryField(WORD);
		addKey(WORD);
		setWord(word);
		find(true);
	}
	
	public void setWord(String word){
		addField(WORD, word);
	}
	
	public void getWord(){
		getField(WORD);
	}
	

}
