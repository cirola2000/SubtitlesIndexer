package status.subtitles.API.controllers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import status.exceptions.SubtitleException;
import status.mongodb.collections.SubtitleDB;
import status.mongodb.collections.VideoDB;
import status.subtitles.API.models.SubtitleAddModel;
import status.subtitles.http.DownloadSubtitle;
import status.subtitles.nlp.NLPProcessor;
import status.subtitles.nlp.stanford.StanfordNLP;
import status.subtitles.parser.SubtibtleParser;
import status.utils.FileUtils;

@RestController
public class SubtitleController {

	public ConcurrentHashMap<String, Integer> subtitlesBeingProcessed = new ConcurrentHashMap<String, Integer>();

	@RequestMapping(value = "/subtitle/add", method = RequestMethod.POST)
	public SubtitleAddModel add(@RequestParam(value = "subtitleURL", required = true) String subtitleURL,
			@RequestParam(value = "videoID", required = true) String videoID) throws Exception {

		SubtitleAddModel model = new SubtitleAddModel();

		// preventing to process the same subtitle multiple times
		if (subtitlesBeingProcessed.contains(subtitleURL)) {
			throw new SubtitleException("Subtitle is already being processed.");
		} else
			subtitlesBeingProcessed.put(subtitleURL, 0);

		model.addSubtitle(subtitleURL, videoID);

		subtitlesBeingProcessed.remove(subtitleURL);

		return model;
	}
	
	
	
}
