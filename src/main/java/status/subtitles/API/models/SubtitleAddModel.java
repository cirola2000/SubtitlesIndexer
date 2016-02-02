package status.subtitles.API.models;

import status.exceptions.SubtitleException;
import status.mongodb.collections.SubtitleDB;
import status.mongodb.collections.VideoDB;
import status.subtitles.http.DownloadSubtitle;
import status.subtitles.nlp.NLPProcessor;
import status.subtitles.nlp.stanford.StanfordNLP;
import status.subtitles.parser.SubtibtleParser;
import status.utils.FileUtils;

public class SubtitleAddModel {

	String error;

	String msg;

	public void addSubtitle(String subtitleURL, String videoID) throws Exception {

		// check whether subtitle had already been processed
		SubtitleDB subtitleDB = new SubtitleDB(subtitleURL);

		if (subtitleDB.getID() != null) {
			throw new SubtitleException("The subtitle had already been processed.");
		}
		subtitleDB.update(true);

		String fileName = "/tmp/"+FileUtils.fileNameToHash(subtitleURL);

		DownloadSubtitle d = new DownloadSubtitle();
		d.Download(subtitleURL, fileName);

		SubtibtleParser parser = new SubtibtleParser();
		parser.parse(fileName, FileUtils.getExtension(subtitleURL));
		
		VideoDB videoDB = new VideoDB(videoID);

		NLPProcessor processor = new StanfordNLP(subtitleDB.getID());
		subtitleDB.setVideoID(videoDB.getID());
		subtitleDB.update(false);

		processor.processCaptions(parser.captions);
		
		setMsg("Ok!");
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
