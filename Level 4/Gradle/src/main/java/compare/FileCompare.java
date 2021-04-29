package compare;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompare {

    static Logger log = LogManager.getLogger("FileCompare");

    public ArrayList<String> compare(String fName1, String fName2) throws IOException {
        String line1, line2;
        ArrayList<String> files = new ArrayList<String>();
        int i=1;
        log.debug("Comparing procedure has been started.");
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            files.add("");
            files.add("");
            while (((line1 = br1.readLine())!= null&&((line2 = br2.readLine())!= null))) {
                if (!line1.equals(line2))
                {
                    System.out.println(" Modded line "+i+": "+line2);
                }
                files.set(0, files.get(0)+line1);
                files.set(1, files.get(1)+line2);
                i++;
            }
            while ((line1 = br1.readLine())!=null)
                files.set(0, files.get(0)+line1);
            while ((line2 = br2.readLine())!=null)
                files.set(1, files.get(1)+line2);
            log.debug("Success.");
        }
        catch (IOException ex) {
            log.error("Encountered problem of reading from file.\nShutdown program...\n" + ex.getMessage());
            throw new IOException(ex);
        }
        log.debug("Comparing procedure has been ended.");
        return files;
    }
}
