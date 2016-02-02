package status.subtitles.API.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import status.mongodb.IndexesCreator;
import status.mongodb.collections.VideoDB;
import status.mongodb.exceptions.MissingPropertiesException;
import status.mongodb.exceptions.NoPKFoundException;
import status.mongodb.exceptions.ObjectAlreadyExistsException;
import status.subtitles.http.DownloadSubtitle;
import status.subtitles.nlp.NLPProcessor;
import status.subtitles.nlp.stanford.StanfordNLP;
import status.subtitles.parser.SubtibtleParser;
import status.utils.FileUtils;


@RestController
public class MainController {


	@RequestMapping(value = "/main")
	public String vai() throws MissingPropertiesException, ObjectAlreadyExistsException, NoPKFoundException{
		new IndexesCreator().createIndexes();
		
		// save to a file
		String url = "http://10.40.0.3/coisas/Modern.Family.S02.Season.2.720p.5.1Ch.BluRay.ReEnc-DeeJayAhmed/Modern.Family.S02E01.720p.5.1Ch.BluRay.ReEnc-DeeJayAhmed.srt";
		String fileName = "/tmp/";
		
		VideoDB video = new VideoDB();
		video.setExternalID(FileUtils.getFileName(url));
		video.update(true);

		DownloadSubtitle d = new DownloadSubtitle();
		d.Download(url, fileName);

		SubtibtleParser parser = new SubtibtleParser();
		parser.parse(fileName, FileUtils.getExtension(url));
//		parser.printCaptions();

		
		VideoDB videoDB = new VideoDB();
		videoDB.setFileName(FileUtils.getFileName(url));
		videoDB.update(true);
		
		NLPProcessor processor = new StanfordNLP(videoDB.getID());
		
		try {
			processor.processCaptions(parser.captions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "lol";
	}
	
}
