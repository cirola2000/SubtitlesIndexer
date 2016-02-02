package status.subtitles.stringOperations;

public class Normalize {

	public String normalize(String str) {
		return str.toLowerCase();
	}
	
	public static String removeHTML(String htmlText){
		return htmlText.replaceAll("\\<[^>]*>"," ");
	}
	
	public static String removeBrackets(String bracketsText){
		return bracketsText.replaceAll("\\[[^]]*]"," ");
	}

}
