package demo;

import compare.FileCompare;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.lang.System.exit;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	static Logger log = LogManager.getLogger("DemoApplication");

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws IOException {
		log.trace("Entering...");
		if (args.length!=2) {
			log.error("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another.");
			log.trace("Exiting...");
			exit(-1);
		}
		FileCompare fc = new FileCompare();
		ArrayList<String> modifies = fc.compare(args[0], args[1]);
		for (String mod : modifies)
			System.console().writer().println(mod);
		log.trace("Exiting...");
		exit(0);
	}
}
