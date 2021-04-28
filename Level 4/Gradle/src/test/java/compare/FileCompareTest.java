package compare;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FileCompareTest {
    @Test
    public void compare() throws IOException, IndexOutOfBoundsException {
        String[] args = { "Texts/Sample.txt", "Texts/Modify.txt" };
        FileCompare fc = new FileCompare();
        Assert.assertNotNull(args[0]);
        Assert.assertNotNull(args[1]);
        Assert.assertNotEquals(args[0], args[1]);
        ArrayList<String> fs = fc.compare(args[0], args[1]);
        Assert.assertNotNull(fs.get(0));
        Assert.assertNotNull(fs.get(1));
        Assert.assertNotEquals(fs.get(0), fs.get(1));
    }
}