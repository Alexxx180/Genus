package compare;

<<<<<<< HEAD
<<<<<<< HEAD
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
>>>>>>> level5

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileCompareTest {
    private FileCompare fc = new FileCompare();
    @Test
    public void compareMain() throws IOException {
<<<<<<< HEAD
        String[] args = {"src/main/resources/Texts/Sample.txt", "src/main/resources/Texts/Modify.txt"};
        ArrayList<String> result = new ArrayList<>(Arrays.asList(
                "3. Deleted line: file. Py code down","3. Updated line: file. Java code down", "5. Deleted line: #Simple Python","6. Deleted line: print(\"Hello, world!\")",
                "5. Updated line: //Awesome Java","6. Updated line: public class Main {","7. Updated line: public static void main(String[] args) {",
                "8. Updated line: System.out.println(\"Hello, world!\")","9. Updated line: }","10. Updated line: }"
        ));
=======
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
>>>>>>> level5
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareRu() throws IOException {
<<<<<<< HEAD
        String[] args = {"src/test/resources/Russian1.txt", "src/test/resources/Russian2.txt"};
        ArrayList<String> result = new ArrayList<>(Arrays.asList(
                "2. Deleted line: это простой пример", "3. Deleted line: русского файла №1", "2. Updated line: это другой пример", "3. Updated line: русского файла №2"
        ));
=======
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
>>>>>>> level5
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareNull() throws IOException {
<<<<<<< HEAD
        String[] args = {"src/test/resources/NullFile1.txt", "src/test/resources/NullFile2.txt"};
=======
        String[] args = {
                "src/test/resources/NullFile1.txt",
                "src/test/resources/NullFile2.txt"
        };
>>>>>>> level5
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, fc.compare(args[0], args[1]));
    }
    @Test
    public void compareIdent() throws IOException {
<<<<<<< HEAD
        String[] args = {"src/main/resources/Texts/Sample.txt", "src/main/resources/Texts/Sample.txt"};
=======
        String[] args = {
                "src/main/resources/Texts/Sample.txt",
                "src/main/resources/Texts/Sample.txt"
        };
>>>>>>> level5
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, fc.compare(args[0], args[1]));
    }

    @Test
    public void compare404() {
<<<<<<< HEAD
        String[] args = {"doesntExist1", "doesntExist2"};
        assertThrows(IOException.class, () -> fc.compare(args[0], args[1]));
=======
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
>>>>>>> level2
=======
        String[] args = {
                "doesntExist1",
                "doesntExist2"
        };
        assertThrows(IOException.class, () -> fc.compare(args[0], args[1]));
>>>>>>> level5
    }
}