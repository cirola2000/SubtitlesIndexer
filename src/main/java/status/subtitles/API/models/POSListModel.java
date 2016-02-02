package status.subtitles.API.models;

import java.util.HashSet;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import status.exceptions.SubtitleException;
import status.mongodb.collections.POSDB;
import status.mongodb.collections.WordDB;
import status.mongodb.collections.WordSentenceRelationDB;

public class POSListModel {

	int size;

	HashSet<String> words = new HashSet<String>();

	public void makeList(String pos) throws SubtitleException {

		// get POS reference
		POSDB posDB = new POSDB();
		posDB.setPOS(pos);
		posDB.find(true);

		if (posDB.getID() != null) {
			// find all words with the pod ID
			for (DBObject obj : new WordSentenceRelationDB().
					getAll(new BasicDBObject(WordSentenceRelationDB.POS_ID,posDB.getID()))){
				WordDB wordDB = new WordDB(new ObjectId(obj.get(WordSentenceRelationDB.WORD_ID).toString()));
				if(wordDB.getID()!=null){
					words.add(wordDB.getWord());
				}
			}
			setSize(words.size());
			
		} else {
			throw new SubtitleException("Not possible to find: " + pos + " tag.");
		}

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HashSet<String> getWords() {
		return words;
	}

	public void setWords(HashSet<String> words) {
		this.words = words;
	}

}
