package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileCompare {
    private static String fullText(String fName) throws IOException {
        String line;
        String text="";
        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            while ((line = br.readLine()) != null) text+=line+"\n";
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
        return text;
    }

    public void Compare(String fName1, String fName2) throws IOException {
        String file1 = fullText(fName1), file2 = fullText(fName2);
        int i=1;
        while (true) {
            if (file1==null || file2==null)
                break;
            if (!file1.contains("\n")||!file2.contains("\n"))
                break;
            if (!file1.substring(0,file1.indexOf("\n")).equals(file2.substring(0,file2.indexOf("\n"))))
                System.out.println(" Modded line "+i+": "+file2.substring(0,file2.indexOf("\n")));
            file1=file1.substring(file1.indexOf("\n")+1);
            file2=file2.substring(file2.indexOf("\n")+1);
            i++;
        }
    }
}
