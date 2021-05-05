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
        result.add("Deleted 2 : Py\nDeleted 4 : #Si\nDeleted 4 : e\nDeleted 4 : Py\nDeleted 4 : h\n");
        result.add("Updated 2 : Java\nUpdated 4 : //Aweso\nUpdated 4 : e Java\nUpdated 4 : \nUpdated 5 : ub\nUpdated 5 : ic\nUpdated 5 : class Main {\nUpdated 6 : public s\nUpdated 6 : atic v\nUpdated 6 : id mai\nUpdated 6 : (String[] args) {\nUpdated 7 : System.out.\nUpdated 7 : ln\nUpdated 8 : }\nUpdated 8 : }\nUpdated 8 : \n");
        testing(fc.compare(args[0], args[1]), result);
    }
    @Test
    public void compareRu() throws IndexOutOfBoundsException {
        String[] args = {"src/test/resources/Russian1.txt", "src/test/resources/Russian2.txt"};
        ArrayList<String> result = new ArrayList<>();
        result.add("Deleted 1 : п\nDeleted 1 : ост\nDeleted 2 : 1\n");
        result.add("Updated 1 : д\nUpdated 1 : уг\nUpdated 2 : 2\n");
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