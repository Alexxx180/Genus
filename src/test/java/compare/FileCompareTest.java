package compare;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class FileCompareTest {
    FileCompare fc = new FileCompare();
    private void testing(ArrayList<String> args, ArrayList<String> result) throws IndexOutOfBoundsException {
        Assert.assertNotNull(args);
        Assert.assertEquals(args, result);
    }
    @Test
    public void compareMain() throws IndexOutOfBoundsException {
        String[] args = {"src/main/resources/Texts/Sample.txt", "src/main/resources/Texts/Modify.txt"};
        ArrayList<String> result = new ArrayList<>();
        result.add("Updated 3: file. Java code down\nUpdated 5: //Awesome Java\nUpdated 6: public class Main {\n");
        result.add("Added 7: public static void main(String[] args) {\nAdded 8: System.out.println(\"Hello, world!\")\nAdded 9: }\nAdded 10: }\n");
        testing(fc.compare(args[0], args[1]), result);
    }
    @Test
    public void compareRu() throws IndexOutOfBoundsException {
        String[] args = {"src/test/resources/Russian1.txt", "src/test/resources/Russian2.txt"};
        ArrayList<String> result = new ArrayList<>();
        result.add("Updated 2: это другой пример\nUpdated 3: русского файла №2\n");
        result.add("");
        testing(fc.compare(args[0], args[1]), result);
    }
    @Test
    public void compareNull() throws IndexOutOfBoundsException {
        String[] args = {"src/test/resources/NullFile1.txt", "src/test/resources/NullFile2.txt"};
        ArrayList<String> result = new ArrayList<>();
        result.add("");
        result.add("");
        testing(fc.compare(args[0], args[1]), result);
    }
    @Test
    public void compareIdent() throws IndexOutOfBoundsException {
        String[] args = {"src/main/resources/Texts/Sample.txt", "src/main/resources/Texts/Sample.txt"};
        ArrayList<String> result = new ArrayList<>();
        testing(fc.compare(args[0], args[1]), result);
    }

    @Test
    public void compare404() throws IndexOutOfBoundsException {
        String path1 = "doesntExist1";
        String path2 = "doesntExist2";
        ArrayList<String> result = new ArrayList<>();
        result.add("");
        result.add("");
        testing(fc.compare(path1, path2), result);
    }
}