package by.eugene.importer.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnZip {

	private static final Logger logger = LoggerFactory.getLogger(UnZip.class);
	private static final int BUFFER = 2048;

	/**
	 * Unzip file
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void unzip(String source, String target) throws IOException {

		ZipFile zip = new ZipFile(new File(source));

		new File(target).mkdir();
		Enumeration<?> zipFileEntries = zip.entries();

		logger.info("Start unzip file");

		while (zipFileEntries.hasMoreElements()) {

			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(target, currentEntry);
			File destinationParent = destFile.getParentFile();

			logger.debug(currentEntry);

			destinationParent.mkdirs();

			createFile(zip, entry, destFile);

			unzipInsertedZip(target, currentEntry, destFile);
		}
		logger.info("Done unzip file and save to " + target);
	}

	/**
	 * Unzip inserted file
	 * 
	 * @param target
	 * @param currentEntry
	 * @param destFile
	 * @throws IOException
	 */
	private static void unzipInsertedZip(String target, String currentEntry, File destFile) throws IOException {
		if (currentEntry.endsWith(".zip")) {
			unzip(destFile.getAbsolutePath(), target);
		}
	}

	/**
	 * Create file in file system
	 * 
	 * @param zip
	 * @param entry
	 * @param destFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void createFile(ZipFile zip, ZipEntry entry, File destFile) throws IOException,
			FileNotFoundException {
		if (!entry.isDirectory()) {
			BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
			int currentByte;
			byte data[] = new byte[BUFFER];

			FileOutputStream fos = new FileOutputStream(destFile);
			BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

			while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
				dest.write(data, 0, currentByte);
			}
			close(is, dest);
		}
	}

	/**
	 * Close all streams
	 * 
	 * @param is
	 * @param dest
	 * @throws IOException
	 */
	private static void close(BufferedInputStream is, BufferedOutputStream dest) throws IOException {
		dest.flush();
		dest.close();
		is.close();
	}
}
