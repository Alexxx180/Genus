package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileCompare {
    public void compare(String fName1, String fName2) throws IOException {
        String line1, line2;
        int i=1;
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            while (((line1 = br1.readLine())!= null&&((line2 = br2.readLine())!= null))) {
                if (!line1.equals(line2))
                    System.out.println(" Modded line "+i+": "+line2);
                i++;
            }
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
