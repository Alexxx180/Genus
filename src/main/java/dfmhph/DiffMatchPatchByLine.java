package dfmhph;

import exceptions.CompareException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.LinkedList;
import java.util.List;


/**
 * Extend diff_match_patch functionality allowing to compare texts by lines, not by chars.
 * See https://github.com/google/diff-match-patch/wiki/Line-or-Word-Diffs
 * For some reason, in Java version of DiffMatchPatch, some useful methods and classes are protected.
 */
public class DiffMatchPatchByLine extends DMP {
    /**
     * Compare two texts on a line basis, not a char basis.
     * @param text1
     * @param text2
     * @return List of differences by lines
     * @throws CompareException
     */
    @SuppressWarnings("unchecked")
    public LinkedList<Diff> diff_byLine(String text1, String text2) throws CompareException {
        // Convert lines to unicode chars
        DMP.LinesToCharsResult linesAsChars = diff_linesToChars(text1, text2);
        try {
            // Using reflection to access protected fields
            text1 = (String) FieldUtils.readDeclaredField(linesAsChars, "chars1", true);
            text2 = (String) FieldUtils.readDeclaredField(linesAsChars, "chars2", true);
            List<String> lineArray = (List<String>) FieldUtils.readDeclaredField(linesAsChars, "lineArray", true);
            // Compare lines as unicode chars
            LinkedList<Diff> diffs = diff_main(text1, text2, false);
            // Convert unicode chars back to lines
            diff_charsToLines(diffs, lineArray);
            return diffs;
        } catch (IllegalAccessException e) {
            throw new CompareException(e);
        }
    }




}