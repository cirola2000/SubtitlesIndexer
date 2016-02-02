package status.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

	public static String getExtension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
			extension = fileName.substring(i + 1);
		}
		return extension;
	}

	public static String getFileName(String file) {
		return file.substring(file.lastIndexOf('/') + 1, file.length());
	}

	public static String getFileNameWithoutExt(String file) {
		String f = file.substring(file.lastIndexOf('/') + 1, file.length());
		return f.substring(0, file.lastIndexOf('.'));
	}

	public static String fileNameToHash(String fileName) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(fileName.getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
