package status.subtitles.API.models;

import java.util.ArrayList;

import com.mongodb.DBObject;

import status.mongodb.collections.WordDB;

public class WordListModel {

	int size;

	
	ArrayList<String> words = new ArrayList<String>();
	

	public void makeList(){
		

		for(DBObject o: new WordDB().getAll()){
			words.add(o.get(WordDB.WORD).toString());
		}
		
		setSize(new WordDB().getAll().size());
		
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
