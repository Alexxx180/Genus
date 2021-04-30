package compare;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FileCompareTest {
    FileCompare fc = new FileCompare();
    private void testing(ArrayList<String> args) throws IndexOutOfBoundsException {
        Assert.assertNotNull(args);
        Assert.assertNotNull(args.get(0));
        Assert.assertNotNull(args.get(1));
        Assert.assertNotEquals(args.get(0), args.get(1));
    }
    @Test
    public void compare1() throws IOException, IndexOutOfBoundsException {
        String[] args = {"Texts/Sample.txt", "Texts/Modify.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare2() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"src/test/resources/Speedtest1.txt", "src/test/resources/Speedtest2.txt"};
        testing(fc.compare(args[0], args[1]));

    }
    @Test
    public void compare3() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"Texts/Sample.txt", "src/test/resources/Speedtest2.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare4() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"src/test/resources/NullFile1.txt", "Texts/Modify.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare5() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"src/test/resources/NullFile1.txt", "src/test/resources/NullFile2.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare6() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"src/test/resources/Russian1.txt", "src/test/resources/Russian2.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare7() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"src/test/resources/Russian1.txt", "src/test/resources/NullFile1.txt"};
        testing(fc.compare(args[0], args[1]));
    }
    @Test
    public void compare8() throws IOException, IndexOutOfBoundsException {
        String[] args = new String[] {"Texts/Sample.txt", "src/test/resources/Russian2.txt"};
        testing(fc.compare(args[0], args[1]));
    }
}