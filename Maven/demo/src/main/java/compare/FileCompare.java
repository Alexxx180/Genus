package compare;

//[EN] Libraries to work with
//[RU] Библиотеки для работы с файлами
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompare {
    //[EN] Writing file into array-list
    //[RU] Записываем файл в массив-список
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

    public void Compare(String fName1, String fName2) throws IOException {
        //[EN] Files
        //[RU] Файлы
        ArrayList<String> file1 = getFullText(fName1);
        ArrayList<String> file2 = getFullText(fName2);

        //[EN] First, get changes in same lines
        //[RU] Сначала регистрируем изменения тех же линий обоих файлов
        int i;
        int len = Math.min(file1.size(), file2.size());
        for (i=0;i<len;i++) {
            if (!file1.get(i).equals(file2.get(i)))
                System.out.println(" Modded line "+i+": "+file2.get(i));
        }

        //[EN] Then all changes that out of range of the first file detected
        //[RU] Затем засекаем все изменения вне диапазона файла
        len = Math.max(file1.size(), file2.size());
        if (len != file1.size()) {
            printChanges(i, len, "  Added line ", file2);
        } else {
            printChanges(i, len, "Deleted line ", file1);
        }
    }

    public FileCompare() {
        //Do nothing
    }

    public FileCompare(String fName1, String fName2) throws IOException {
        try{
            Compare(fName1, fName2);
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
