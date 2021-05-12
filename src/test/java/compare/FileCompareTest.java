package compare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileCompareTest {
    private FileCompare fc = new FileCompare();
    @Test
    public void compareMain() throws IOException {
        String[] args = {
                "src/main/resources/Sample.txt",
                "src/main/resources/Modify.txt"
        };
        List<String> result = Arrays.asList(
                "3. Deleted line: file. Py code down",
                "3. Updated line: file. Java code down",
                "5. Deleted line: #Simple Python",
                "6. Deleted line: print(\"Hello, world!\")",
                "5. Updated line: //Awesome Java",
                "6. Updated line: public class Main {",
                "7. Updated line: public static void main(String[] args) {",
                "8. Updated line: System.out.println(\"Hello, world!\")",
                "9. Updated line: }",
                "10. Updated line: }"
        );
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareRu() throws IOException {
        String[] args = {
                "src/test/resources/Russian1.txt",
                "src/test/resources/Russian2.txt"
        };
        List<String> result = Arrays.asList(
                "2. Deleted line: это простой пример",
                "3. Deleted line: русского файла №1",
                "2. Updated line: это другой пример",
                "3. Updated line: русского файла №2"
        );
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareNull() throws IOException {
        String[] args = {
                "src/test/resources/NullFile1.txt",
                "src/test/resources/NullFile2.txt"
        };
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareIdent() throws IOException {
        String[] args = {
                "src/main/resources/Texts/Sample.txt",
                "src/main/resources/Texts/Sample.txt"
        };
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, fc.compare(args[0], args[1]));
    }

    @Test
    public void compare404() {
        String[] args = {
                "doesntExist1",
                "doesntExist2"
        };
        assertThrows(IOException.class, () -> fc.compare(args[0], args[1]));
    }
}