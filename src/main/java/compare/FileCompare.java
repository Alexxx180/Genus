package compare;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompare {

    static Logger log = LogManager.getLogger("FileCompare");

    public ArrayList<String> compare(String fName1, String fName2) {
        String line1, line2;
        ArrayList<String> files = new ArrayList<String>();
        if (fName1.equals(fName2)) {
            log.error("Same file input.");
            return files;
        }
        files.add("");
        files.add("");
        int i=1;
        log.debug("Comparing procedure has been started.");
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            while (((line1 = br1.readLine())!= null&&((line2 = br2.readLine())!= null))) {
                if (!line1.equals(line2))
                    files.set(0, files.get(0)+"Updated "+i+": "+line2+"\n");
                i++;
            }
            while ((line1 = br1.readLine())!=null) {
                files.set(1, files.get(1)+"Deleted "+i+": "+line1+"\n");
                i++;
            }
            while ((line2 = br2.readLine())!=null) {
                files.set(1, files.get(1)+"Added "+i+": "+line2+"\n");
                i++;
            }
            if (files.get(0).equals(files.get(1))) {
                log.error("Files is null.");
                return files;
            }
            log.debug("Success.");
        }
        catch (IOException ex) {
            log.error("Encountered problem of reading from file.\nShutdown program...\n" + ex.getMessage());
            return files;
        }
        log.debug("Comparing procedure has been ended.");
        return files;
    }
}
