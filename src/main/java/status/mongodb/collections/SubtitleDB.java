package status.mongodb.collections;

import status.mongodb.MongoSuperClass;
import status.mongodb.exceptions.MissingPropertiesException;
import status.mongodb.exceptions.NoPKFoundException;
import status.mongodb.exceptions.ObjectAlreadyExistsException;

public class SubtitleDB extends MongoSuperClass {

	public static String COLLECTION_NAME = "Subtitle";

	public static String SUBTITLE_URL = "subtitleURL";

	public static String VIDEO_ID = "videoID";

	public SubtitleDB() {
		super(COLLECTION_NAME);
		addKey(SUBTITLE_URL);
	}

	public SubtitleDB(String subtitleURL) {
		super(COLLECTION_NAME);
		addKey(SUBTITLE_URL);
		setSubtitleURL(subtitleURL);
		find(true);
	}

	public String getSubtitleURL() {
		return getField(SUBTITLE_URL).toString();
	}

	public void setSubtitleURL(String subtitleURL) {
		addField(SUBTITLE_URL, subtitleURL);
	}

	public String getVideoID() {
		return getField(VIDEO_ID).toString();
	}

	public void setVideoID(String videoID) {
		addField(VIDEO_ID, videoID);
	}

}
