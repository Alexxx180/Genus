package alexx.text.diffs.compare;

import alexx.text.diffs.dfmhph.DMP;
import alexx.text.diffs.dfmhph.DiffMatchPatchByLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import alexx.text.diffs.exceptions.CompareException;
import alexx.text.model.CompareResults;
import alexx.text.model.Diff;
import org.springframework.stereotype.Service;

@Service
public class FileCompare {

    private int lineOrigin = 1;
    private int lineModified = 1;

    /**
     * Compare two list of strings from text files
     *
     * @param origin   List of strings from original file
     * @param modified List of strings from modified file
     * @return Object containing compare results
     * @throws CompareException If an exception occurred while comparing
     */
    public CompareResults compare(List<String> origin, List<String> modified) throws CompareException {
        CompareResults results = new CompareResults(new ArrayList<>());

        lineOrigin = 1;
        lineModified = 1;

        List<Diff> diffs = compareToEnd(origin, modified);
        results.setDiffList(diffs);
        return results;
    }

    /**
     * Compare to end of largest list of strings
     *
     * @param origin   List of strings from original file
     * @param modified List of strings from modified file
     * @return Comfortable to reading list of differences
     * @throws CompareException If an exception occurred while comparing
     */
    private List<Diff> compareToEnd(List<String> origin, List<String> modified) throws CompareException {
        DiffMatchPatchByLine dp = new DiffMatchPatchByLine();
        List<Diff> diffs = new ArrayList<>();
        while ((lineOrigin-1 < origin.size()) && (lineModified-1 < modified.size())) {
            LinkedList<DMP.Diff> diff = dp.diff_byLine(origin.get(lineOrigin-1), modified.get(lineModified-1));
            diffs.addAll(fillDiffs(diff));
        }
        while (lineOrigin-1 < origin.size()) {
            LinkedList<DMP.Diff> diff = dp.diff_byLine(origin.get(lineOrigin-1), "");
            diffs.addAll(fillDiffs(diff));
        }
        while (lineModified-1 < modified.size()) {
            LinkedList<DMP.Diff> diff = dp.diff_byLine("", modified.get(lineModified-1));
            diffs.addAll(fillDiffs(diff));
        }
        return diffs;
    }

    /**
     * Fill differences from list of differences in human readable form
     *
     * @param diff List of differences
     * @return Comfortable to reading list of differences
     * @throws CompareException If an exception occurred while comparing
     */
    private List<Diff> fillDiffs(LinkedList<DMP.Diff> diff) {
        List<Diff> diffs = new ArrayList<>();
        for (DMP.Diff df : diff) {
            switch (df.operation) {
                case EQUAL:
                    lineOrigin++; lineModified++;
                    break;
                case DELETE:
                    diffs.add(new Diff(df.operation, df.text, "", lineOrigin));
                    lineOrigin++;
                    break;
                case INSERT:
                    diffs.add(new Diff(df.operation, "", df.text, lineModified));
                    lineModified++;
                    break;
            }
        }
        return diffs;
    }
}
