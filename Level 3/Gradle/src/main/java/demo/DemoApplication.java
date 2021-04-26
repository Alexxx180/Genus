package demo;

import compare.FileCompare;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class DemoApplication {

	static Logger log = LogManager.getLogger("DemoApplication");

	public static void main(String[] args) throws IndexOutOfBoundsException {
		String f1, f2;

		final String LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%-5level] MyApp - %msg%n";
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();

		Layout layout = PatternLayout.createLayout(LOG_PATTERN, null, config, null, null, false, false, null, null);
		Appender appenderInfo = FileAppender.createAppender("Logs/Info.log", "true", "true", "File", "true",
				"false", "false", null, layout, null, "false", null, config);
		Appender appenderDebug = FileAppender.createAppender("Logs/Debug.log", "true", "true", "File2", "true",
				"false", "false", null, layout, null, "false", null, config);
		appenderInfo.start();
		appenderDebug.start();
		config.addAppender(appenderInfo);
		config.addAppender(appenderDebug);
		LoggerConfig loggerConfig = config.getLoggerConfig("demo.DemoApplication");
		loggerConfig.addAppender(appenderInfo, Level.INFO, null);
		loggerConfig.addAppender(appenderDebug, Level.DEBUG, null);
		ctx.updateLoggers();

		log.debug("Entering...");
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
		log.debug("Exiting...");

	}
}
