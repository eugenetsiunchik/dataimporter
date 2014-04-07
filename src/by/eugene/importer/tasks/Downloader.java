package by.eugene.importer.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Downloader {

	private static final Logger logger = LoggerFactory.getLogger(Downloader.class);
	private static final int BUFFER = 1024;

	/**
	 * Download the file in the urls and save to file system
	 * 
	 * @param urls
	 * @param target
	 * @throws IOException
	 */
	public static void download(String urls, String target) throws IOException {
		URL url = new URL(urls);

		BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream(target);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, BUFFER);

		logger.info("Start download file");

		byte data[] = new byte[BUFFER];
		while (bufferedInputStream.read(data, 0, BUFFER) >= 0) {
			bufferedOutputStream.write(data);
		}
		logger.info("Done download file and save to " + target);

		close(bufferedInputStream, bufferedOutputStream);

	}

	/**
	 * Close all streams
	 * 
	 * @param bufferedInputStream
	 * @param bufferedOutputStream
	 * @throws IOException
	 */
	private static void close(BufferedInputStream bufferedInputStream, BufferedOutputStream bufferedOutputStream)
			throws IOException {
		bufferedOutputStream.close();
		bufferedInputStream.close();
	}

}
