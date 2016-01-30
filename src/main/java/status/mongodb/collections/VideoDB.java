package status.mongodb.collections;

import status.mongodb.MongoSuperClass;

public class VideoDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Video";

	public static String FILE_NAME = "fileName";

	public VideoDB() {
		super(COLLECTION_NAME);
		addKey(FILE_NAME);
	}


	public void setFileName(String fileName) {
		addField(FILE_NAME, fileName);
	}

	public String getFileName() {
		return getField(FILE_NAME).toString();
	}

}
