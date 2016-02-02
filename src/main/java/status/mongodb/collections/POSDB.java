package status.mongodb.collections;

import status.mongodb.MongoSuperClass;
import status.mongodb.exceptions.MissingPropertiesException;
import status.mongodb.exceptions.NoPKFoundException;
import status.mongodb.exceptions.ObjectAlreadyExistsException;

public class POSDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "POS";
	
	public static String POS = "pos";

	public POSDB() {
		super(COLLECTION_NAME);
		addKey(POS);
	}
	
	public POSDB(String pos) {
		super(COLLECTION_NAME);
		addKey(POS);
		setPOS(pos);
		try {
			update(true);
		} catch (MissingPropertiesException | ObjectAlreadyExistsException | NoPKFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void setPOS(String pos){
		addField(POS, pos);
	}
	
	public String getPOS(){
		return getField(POS).toString();
	}
	
	
}
