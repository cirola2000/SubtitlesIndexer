package status.utils;

public class HTMLUtils {

	public static String removeHTML(String htmlText){
		return htmlText.replaceAll("\\<[^>]*>"," ");
	}
	
}
