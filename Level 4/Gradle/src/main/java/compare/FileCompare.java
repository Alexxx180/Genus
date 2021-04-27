package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileCompare {
    public String[] compare(String fName1, String fName2) throws IOException {
        String line1, line2;
        String[] files = { null, null };
        int i=1;
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            files[0]="";
            files[1]="";
            while (((line1 = br1.readLine())!= null&&((line2 = br2.readLine())!= null))) {
                if (!line1.equals(line2))
                {
                    System.out.println(" Modded line "+i+": "+line2);
                }
                files[0]+=line1;
                files[1]+=line2;
                i++;
            }
            while ((line1 = br1.readLine())!=null)
                files[0]+=line1;
            while ((line2 = br2.readLine())!=null)
                files[1]+=line2;
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
        return files;
    }
}
