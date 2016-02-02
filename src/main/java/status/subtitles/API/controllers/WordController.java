package status.subtitles.API.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import status.exceptions.SubtitleException;
import status.subtitles.API.models.LemmaListModel;
import status.subtitles.API.models.POSListModel;
import status.subtitles.API.models.WordFindModel;
import status.subtitles.API.models.WordListModel;

@RestController
public class WordController {

	@RequestMapping(value = "/word/search")
	public WordFindModel search(@RequestParam(value = "word", required = true) String word) {
		WordFindModel model = new WordFindModel(word);
		model.process();
		return model;
	}
	
	@RequestMapping(value = "/word/list")
	public WordListModel list() {
		WordListModel model = new WordListModel();
		model.makeList();
		return model;
	}
	
	@RequestMapping(value = "/word/lemma/list")
	public LemmaListModel lemmaList() {
		LemmaListModel model = new LemmaListModel();
		model.makeList();
		return model;
	}
	
	@RequestMapping(value = "/word/pos/list")
	public POSListModel posList(@RequestParam(value = "pos", required = true) String pos) throws SubtitleException {
		POSListModel model = new POSListModel();
		model.makeList(pos);
		return model;
	}
}
