package status.subtitles.API.models;

import java.util.ArrayList;

import com.mongodb.DBObject;

import status.mongodb.collections.LemmaDB;
import status.mongodb.collections.WordDB;

public class LemmaListModel {

	int size;

	
	ArrayList<String> words = new ArrayList<String>();
	

	public void makeList(){
		

		for(DBObject o: new LemmaDB().getAll()){
			words.add(o.get(LemmaDB.LEMMA).toString());
		}
		
		setSize(new LemmaDB().getAll().size());
		
	}
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	

}
