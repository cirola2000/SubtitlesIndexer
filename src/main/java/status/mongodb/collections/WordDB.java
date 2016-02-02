package status.mongodb.collections;

import org.bson.types.ObjectId;

import status.mongodb.MongoSuperClass;

public class WordDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Words";
	
	public static String WORD = "word";

	public static String LEMMA_ID = "lemmaID";

	public WordDB() {
		super(COLLECTION_NAME);
		addMandatoryField(WORD);
		addKey(WORD);
	}	
	
	public WordDB(ObjectId id) {
		super(COLLECTION_NAME);
		addMandatoryField(WORD);
		addKey(WORD);
		find(true, ID, id);
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
	
	public String getWord(){
		return getField(WORD).toString();
	}
	
	public void setLemmaID(String lemmaID){
		addField(LEMMA_ID, lemmaID);
	}
	
	public String getLemmaID(){
		return getField(LEMMA_ID).toString();
	}
	

}
