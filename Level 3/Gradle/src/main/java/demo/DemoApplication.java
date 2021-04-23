package demo;

import compare.FileCompare;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DemoApplication {

	static Logger log = LogManager.getLogger("DemoApplication");

	public static void main(String[] args) throws IndexOutOfBoundsException {
		String f1, f2;

		log.trace("Entering...");
		try {
			f1 = args[0];
			f2 = args[1];
		}
		catch (IndexOutOfBoundsException ex) {
			log.error("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another.");
			return;
		}
		FileCompare fc = new FileCompare();

		try {
			fc.compare(f1, f2);
		}
		catch (IOException ex) {
			log.error("Files don't exist or\nnames aren't correctly.");
			return;
		}
		log.trace("Exiting...");

	}
}
