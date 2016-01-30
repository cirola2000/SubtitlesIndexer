package status.subtitles.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import status.subtitles.stringOperations.Normalize;
import status.utils.HTMLUtils;
import subtitleFile.Caption;
import subtitleFile.FormatASS;
import subtitleFile.FormatSCC;
import subtitleFile.FormatSRT;
import subtitleFile.FormatSTL;
import subtitleFile.FormatTTML;
import subtitleFile.Time;
import subtitleFile.TimedTextFileFormat;
import subtitleFile.TimedTextObject;

public class SubtibtleParser {

	TimedTextObject tto;
	TimedTextFileFormat ttff;

	public ArrayList<Caption> captions = null;

	public void parse(String fileName, String inputFormat) {
		try {
			if ("SRT".equalsIgnoreCase(inputFormat)) {
				ttff = new FormatSRT();
			} else if ("STL".equalsIgnoreCase(inputFormat)) {
				ttff = new FormatSTL();
			} else if ("SCC".equalsIgnoreCase(inputFormat)) {
				ttff = new FormatSCC();
			} else if ("XML".equalsIgnoreCase(inputFormat)) {
				ttff = new FormatTTML();
			} else if ("ASS".equalsIgnoreCase(inputFormat)) {
				ttff = new FormatASS();
			} else {
				throw new Exception(
						"Unrecognized input format: " + inputFormat + " only [SRT,STL,SCC,XML,ASS] are possible");
			}

			File file = new File(fileName);
			InputStream is = new FileInputStream(file);
			tto = ttff.parseFile(file.getName(), is);

			captions = new ArrayList<Caption>();

			for (Caption s : tto.captions.values()) {
				Caption c = s;
				
				// removing HTML from captions
				String content = HTMLUtils.removeHTML(s.content);
				
				if (!content.equals("")) {
					c.content = content;
					captions.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printCaptions() {
		for(Caption c : captions)
			System.out.println(c.content);
	}
}
