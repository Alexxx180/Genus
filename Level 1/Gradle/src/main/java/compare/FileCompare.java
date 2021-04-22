package compare;

//Libraries to work with
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import dfmhph.DMP;

public class FileCompare {
    //Writing file into array-list
    private static ArrayList<String> fullText(BufferedReader br) throws IOException {
        String line;
        ArrayList<String> text=new ArrayList<>();
        try {
            while ((line=br.readLine())!=null) text.add(line);
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
        return text;
    }

    /*Writing file into string
    private static String fullText(BufferedReader br, String sep) throws IOException {
        String line;
        String text="";
        try {
            while ((line=br.readLine())!=null) text+=line+sep;
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
        return text;
    }*/

    private static void printChanges(int i, int max, String txt, ArrayList<String> file) {
        for (;i<max;i++) System.out.println(txt + i + ": " + file.get(i));
    }

    private ArrayList<String> getFullText(String fName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            return fullText(br);
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    /*private String getFullText(String fName, String sep) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            return fullText(br, sep);
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
    }*/

    private void AddedDeleted(ArrayList<String> file1, ArrayList<String> file2) {
        //First, get changes in same lines
        int i;
        int len = Math.min(file1.size(), file2.size());
        for (i=0;i<len;i++) {
            if (!file1.get(i).equals(file2.get(i)))
                System.out.println(" Modded line "+i+": "+file2.get(i));
        }

        //Then all changes that out of range of the first file detected
        len = Math.max(file1.size(), file2.size());
        if (len != file1.size()) {
            printChanges(i, len, "  Added line ", file2);
        } else {
            printChanges(i, len, "Deleted line ", file1);
        }
    }

    private void MethodComp1(String fName1, String fName2) throws IOException {
        //Files
        ArrayList<String> file1 = getFullText(fName1);
        ArrayList<String> file2 = getFullText(fName2);
        AddedDeleted(file1, file2);
    }

    //private void MethodComp2(String Text1, String Text2) { dp.diff_main(Text1,Text2); }

    public void Compare(String fName1, String fName2) throws IOException {
        MethodComp1(fName1, fName2);
        //MethodComp2(getFullText(fName1, "\n"), getFullText(fName2, "\n"));
    }

    public FileCompare(String fName1, String fName2) throws IOException {
        try{
            Compare(fName1, fName2);
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
