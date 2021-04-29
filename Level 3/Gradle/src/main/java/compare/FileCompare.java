package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FileCompare {

    static Logger log = LogManager.getLogger("FileCompare");

    public void compare(String fName1, String fName2) throws IOException {
        log.debug("Comparing procedure has been started.");
        String line1, line2;
        int i=1;
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            while (((line1 = br1.readLine())!= null&&((line2 = br2.readLine())!= null))) {
                if (!line1.equals(line2))
                    System.out.println(" Modded line "+i+": "+line2);
                i++;
            }
            log.debug("Success.");
        }
        catch (IOException ex) {
            log.error("Encountered problem of reading from file.\nShutdown program...\n" + ex.getMessage());
            throw new IOException(ex);
        }
        log.debug("Comparing procedure has been ended.");
    }
}
