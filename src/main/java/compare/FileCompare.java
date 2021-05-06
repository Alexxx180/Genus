package compare;

import dfmhph.DMP;
import dfmhph.DiffMatchPatchByLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileCompare {

    static Logger log = LogManager.getLogger("FileCompare");

    public ArrayList<String> compare(String fName1, String fName2) throws IOException {
        String line1, line2;
        ArrayList<String> files = new ArrayList<String>();
        log.debug("Comparing procedure has been started.");
        if (fName1.equals(fName2)) {
            log.error("Same file name input.");
            return files;
        }
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            String f1="", f2="";
            while ((line1 = br1.readLine())!=null) f1+=line1+"\n";
            while ((line2 = br2.readLine())!=null) f2+=line2+"\n";
            if (f1.equals("")&&f2.equals("")) {
                log.error("Files is null.");
                return files;
            }
            if (f1.equals(f2)) {
                log.error("No differences.");
                return files;
            }
            DiffMatchPatchByLine dp=new DiffMatchPatchByLine();
            LinkedList<DMP.Diff> diff = dp.diff_byLine(f1, f2);
            int i=1, j=1;
            for (DMP.Diff df : diff) {
                switch (df.operation) {
                    case EQUAL:
                        while (df.text.contains("\n")) {
                            df.text = df.text.substring(df.text.indexOf("\n")+1);
                            i++; j++;
                        }
                        break;
                    case DELETE:
                        while (df.text.contains("\n")) {
                            files.add(i+". Deleted line: " + df.text.substring(0,df.text.indexOf("\n")));
                            df.text = df.text.substring(df.text.indexOf("\n")+1);
                            i++;
                        }
                        break;
                    case INSERT:
                        while (df.text.contains("\n")) {
                            files.add(j + ". Updated line: " + df.text.substring(0,df.text.indexOf("\n")));
                            df.text = df.text.substring(df.text.indexOf("\n")+1);
                            j++;
                        }
                        break;
                }
            }
            log.debug("Success.");
        }
        catch (IOException ex) {
            log.error("Encountered problem of reading from file.\nShutdown program...\n"+ex.getMessage());
            throw ex;
        }
        log.debug("Comparing procedure has been ended.");
        return files;
    }
}
