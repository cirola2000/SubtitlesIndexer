package status.subtitles.stringOperations;

public class Normalize {

	String str;

	public Normalize(String str) {
		this.str = str;
	}

	public String normalize() {
		return str.toLowerCase();
	}

}
