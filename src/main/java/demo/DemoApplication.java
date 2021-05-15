package demo;

import compare.FileCompare;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DemoApplication {

	static Logger log = LogManager.getLogger("DemoApplication");

	public static void main(String[] args) throws IOException {
		log.trace("Entering...");
		if (args.length!=2) { log.error("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another."); return; }
		FileCompare fc = new FileCompare();
		ArrayList<String> modifies = fc.compare(args[0], args[1]);
		for (String mod : modifies) System.out.println(mod);
		log.trace("Exiting...");
	}
}
