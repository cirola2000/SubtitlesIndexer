package status.mongodb.collections;

import org.bson.types.ObjectId;

import status.mongodb.MongoSuperClass;

public class SentenceDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Sentence";

	
	public SentenceDB() {
		super(COLLECTION_NAME);
	}
	
	public SentenceDB(Object id) {
		super(COLLECTION_NAME);
		find(true, ID, new ObjectId(id.toString()));
	}

	public static String SENTENCE = "sentence";
	
	public static String CAPTION_ID = "captionId";
	
	
	public void setSentence(String sentence){
		addField(SENTENCE, sentence);
	}
	
	public void setCaptionID(String captionID){
		addField(CAPTION_ID, captionID);
	}
	
	public String getSentence(){
		return getField(SENTENCE).toString();
	}
	
	public String getCaptionID(){
		return getField(CAPTION_ID).toString();
	}
	
}
