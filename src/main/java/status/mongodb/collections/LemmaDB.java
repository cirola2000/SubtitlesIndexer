package status.mongodb.collections;

import status.mongodb.MongoSuperClass;
import status.mongodb.exceptions.MissingPropertiesException;
import status.mongodb.exceptions.NoPKFoundException;
import status.mongodb.exceptions.ObjectAlreadyExistsException;

public class LemmaDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Lemma";

	public LemmaDB() {
		super(COLLECTION_NAME);
		addKey(LEMMA);		
	}
	
	public LemmaDB(String lemma) {
		super(COLLECTION_NAME);
		addKey(LEMMA);		
		setLemma(lemma);
		try {
			update(true);
		} catch (MissingPropertiesException | ObjectAlreadyExistsException | NoPKFoundException e) {
			e.printStackTrace();
		}
	}

	public static String LEMMA = "lemma";
	
	public void setLemma(String lemma){
		addField(LEMMA, lemma);
	}
	
	public String getLemma(){
		return getField(LEMMA).toString();
	}

}
