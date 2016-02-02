package status.mongodb.collections;

import status.mongodb.MongoSuperClass;
import status.mongodb.exceptions.MissingPropertiesException;
import status.mongodb.exceptions.NoPKFoundException;
import status.mongodb.exceptions.ObjectAlreadyExistsException;

public class VideoDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Video";

	public static String VIDEO_EXTERNAL_ID = "videoID";

	public VideoDB() {
		super(COLLECTION_NAME);
		addKey(VIDEO_EXTERNAL_ID);
	}
	
	public VideoDB(String videoID) {
		super(COLLECTION_NAME);
		addKey(VIDEO_EXTERNAL_ID);
		setExternalID(videoID);
		try {
			update(true); 
		} catch (MissingPropertiesException | ObjectAlreadyExistsException
				| NoPKFoundException e) {
			e.printStackTrace();
		}
	}


	public void setExternalID(String id) {
		addField(VIDEO_EXTERNAL_ID, id);
	}

	public String getExternalID() {
		return getField(VIDEO_EXTERNAL_ID).toString();
	}

}
