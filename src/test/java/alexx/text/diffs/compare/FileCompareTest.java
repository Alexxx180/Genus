package alexx.text.diffs.compare;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import alexx.text.diffs.dfmhph.DMP;
import alexx.text.model.Diff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCompareTest {
    private final FileCompare fc = new FileCompare();

    /**
     * This test checks base capabilities of DMP comparing
     * @throws IOException If an exception occurs while comparing
     */
    @Test
    public void compareMain() throws IOException {
        List<String> original = Arrays.asList(
                "There is an example",
                "of ordinary text",
                "file. Py code down",
                "below:",
                "#Simple Python",
                "print(\"Hello, world!\")"
        );
        List<String> modified = Arrays.asList(
                "There is an example",
                "of ordinary text",
                "file. Java code down",
                "below:",
                "//Awesome Java",
                "public class Main {",
                "public static void main(String[] args) {",
                "System.out.println(\"Hello, world!\")",
                "}",
                "}"
        );
        List<Diff> source = Arrays.asList(
                new Diff(DMP.Operation.DELETE, "file. Py code down","", 3),
                new Diff(DMP.Operation.INSERT, "","file. Java code down", 3),
                new Diff(DMP.Operation.DELETE, "#Simple Python","", 5),
                new Diff(DMP.Operation.INSERT, "","//Awesome Java", 5),
                new Diff(DMP.Operation.DELETE, "print(\"Hello, world!\")","", 6),
                new Diff(DMP.Operation.INSERT, "","public class Main {", 6),
                new Diff(DMP.Operation.INSERT, "","public static void main(String[] args) {", 7),
                new Diff(DMP.Operation.INSERT, "","System.out.println(\"Hello, world!\")", 8),
                new Diff(DMP.Operation.INSERT, "","}", 9),
                new Diff(DMP.Operation.INSERT, "","}", 10)
        );
        List<Diff> expected = fc.compare(original, modified).getDiffList();
        for(int i = 0; i < expected.size(); i++) {
            assertEquals(source.get(i).getDiffType(), expected.get(i).getDiffType());
            assertEquals(source.get(i).getOriginalText(), expected.get(i).getOriginalText());
            assertEquals(source.get(i).getModifiedText(), expected.get(i).getModifiedText());
            assertEquals(source.get(i).getLine(), expected.get(i).getLine());
        }
    }
}