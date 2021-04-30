package compare;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FileCompareTest {
    FileCompare fc = new FileCompare();
    private void testing(String[] args) throws IndexOutOfBoundsException {
        Assert.assertNotNull(args[0]);
        Assert.assertNotNull(args[1]);
        //Assert.assertNotEquals(args[0], args[1]);
    }
    private void testing(ArrayList<String> args) throws IndexOutOfBoundsException {
        Assert.assertNotNull(args.get(0));
        Assert.assertNotNull(args.get(1));
        //Assert.assertNotEquals(args.get(0), args.get(1));
    }
    @Test
    public void compare() throws IOException, IndexOutOfBoundsException {
        testing(new String[] { "Texts/Sample.txt", "Texts/Modify.txt" });
        //testing(new String[] { "", "" });
        //testing(new String[] { "Texts/Sample.txt", "" });
        //testing(new String[] { "", "Texts/Sample.txt" });
        testing(new String[] { "Пример", "Samp" });
        testing(new String[] { "Texts/Sample.txt", "Texts/Sample.txt" });
    }
    @Test
    public void compare2() throws IOException, IndexOutOfBoundsException {
        String[] args = { "Texts/Sample.txt", "Texts/Modify.txt" };
        testing(fc.compare(args[0], args[1]));
        //args = new String[] { "", "" };
        //testing(fc.compare(args[0], args[1]));
        //args = new String[] { "Texts/Sample.txt", "" };
        //testing(fc.compare(args[0], args[1]));
        //args = new String[] { "", "Texts/Sample.txt" };
        //testing(fc.compare(args[0], args[1]));
        //args = new String[] { "Пример", "Samp" };
        //testing(fc.compare(args[0], args[1]));
        args = new String[] { "Texts/Sample.txt", "Texts/Sample.txt" };
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare3() throws IOException, IndexOutOfBoundsException {
        String[] args = {"Texts/Sample.txt", "Texts/Modify.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"src/test/resources/Speedtest1.txt", "src/test/resources/Speedtest2.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"Texts/Sample.txt", "src/test/resources/Speedtest2.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"src/test/resources/NullFile1.txt", "Texts/Modify.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"src/test/resources/NullFile1.txt", "src/test/resources/NullFile2.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"src/test/resources/Russian1.txt", "src/test/resources/Russian2.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"src/test/resources/Russian1.txt", "src/test/resources/NullFile1.txt"};
        testing(fc.compare(args[0], args[1]));
        args = new String[] {"Texts/Sample.txt", "src/test/resources/Russian2.txt"};
        testing(fc.compare(args[0], args[1]));
    }
}