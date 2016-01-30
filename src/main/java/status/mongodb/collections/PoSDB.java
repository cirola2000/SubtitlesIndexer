package status.mongodb.collections;

import status.mongodb.MongoSuperClass;

public class PoSDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "PoS";

	public PoSDB() {
		super(COLLECTION_NAME);
	}

	public static String word = "word";

	public static String pos = "pos";

}
