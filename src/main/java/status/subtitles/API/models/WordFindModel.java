package status.subtitles.API.models;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import status.mongodb.MongoSuperClass;
import status.mongodb.collections.CaptionDB;
import status.mongodb.collections.SentenceDB;
import status.mongodb.collections.WordDB;
import status.mongodb.collections.WordSentenceRelationDB;

public class WordFindModel {
	
	public WordFindModel(String word) {
		this.word = word;
	}

	String word;
	
	ArrayList<SentenceCaption> resources = new ArrayList<SentenceCaption>();
	
	class SentenceCaption {
		String sentence;
		String captionStart;
		String captionEnd;
		String videoID;
		
		public String getSentence() {
			return sentence;
		}
		public void setSentence(String sentence) {
			this.sentence = sentence;
		}
		public String getCaptionStart() {
			return captionStart;
		}
		public void setCaptionStart(String captionStart) {
			this.captionStart = captionStart;
		}
		public String getCaptionEnd() {
			return captionEnd;
		}
		public void setCaptionEnd(String captionEnd) {
			this.captionEnd = captionEnd;
		}
		public String getVideoID() {
			return videoID;
		}
		public void setVideoID(String videoID) {
			this.videoID = videoID;
		}
		
		
	}
	
	
	public void process(){
		WordDB wordDB = new WordDB(word);
		
		// find the sentences with the word
		BasicDBObject query = new BasicDBObject(WordSentenceRelationDB.WORD_ID, wordDB.getID());
		DBCursor cursor =  MongoSuperClass.getCollection(WordSentenceRelationDB.COLLECTION_NAME).find(query);
		
		while(cursor.hasNext()){
			SentenceCaption sc = new SentenceCaption();
			DBObject o = cursor.next();
			SentenceDB sentence = new SentenceDB(o.get(WordSentenceRelationDB.SENTENCE_ID));
			
			sc.setSentence(sentence.getSentence());
			
			CaptionDB caption = new CaptionDB(sentence.getCaptionID());			
			sc.setCaptionStart(caption.getStart());
			sc.setCaptionEnd(caption.getEnd());
			sc.setVideoID(caption.getVideoID()); 
			
			resources.add(sc);
		}
	}


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}


	public ArrayList<SentenceCaption> getResources() {
		return resources;
	}


	public void setResources(ArrayList<SentenceCaption> resources) {
		this.resources = resources;
	}
	
	
	
}
