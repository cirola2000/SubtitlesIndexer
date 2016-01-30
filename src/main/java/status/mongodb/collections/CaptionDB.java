package status.mongodb.collections;

import org.bson.types.ObjectId;

import status.mongodb.MongoSuperClass;

public class CaptionDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Caption";

	public CaptionDB() {
		super(COLLECTION_NAME);
	}
	
	public CaptionDB(Object id) {
		super(COLLECTION_NAME);
		find(true, ID, new ObjectId(id.toString()));
	}	

	public static String START = "start";

	public static String END = "end";

	public static String VIDEO_ID = "videoID";

	public void setStart(String start) {
		addField(START, start);
	}

	public void setEnd(String end) {
		addField(END, end);
	}

	public void setVideoID(String videoID) {
		addField(VIDEO_ID, videoID);
	}

	public String getStart() {
		return getField(START).toString();
	}

	public String getEnd() {
		return getField(END).toString();
	}

	public String getVideoID() {
		return getField(VIDEO_ID).toString();
	}

}
