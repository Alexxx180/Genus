package compare;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FileCompareTest {
    static Logger log = LogManager.getLogger("FileCompareTest");
    @Test
    public void compare() {
        String fName1 = "Texts/Sample.txt";
        String fName2 = "Texts/Modify.txt";
        FileCompare fc = new FileCompare();
        try {
            String[] fs = fc.compare(fName1, fName2);
            Assert.assertNotNull(fs[0]);
            Assert.assertNotNull(fs[1]);
            Assert.assertNotEquals(fs[0], fs[1]);
        }
        catch (IOException ex) {
            log.error("Files don't exist or\nnames aren't correctly.");
        }
    }
}