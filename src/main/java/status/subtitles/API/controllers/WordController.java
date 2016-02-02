package status.subtitles.API.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import status.subtitles.API.models.WordFindModel;

@RestController
public class WordController {

	@RequestMapping(value = "/search")
	public WordFindModel search(@RequestParam(value = "word", required = true) String word) {
		WordFindModel model = new WordFindModel(word);
		model.process();
		return model;
	}
	
	@RequestMapping(value = "/list")
	public WordFindModel list(@RequestParam(value = "word", required = true) String word) {
		WordFindModel model = new WordFindModel(word);
		model.process();
		return model;
	}
}
