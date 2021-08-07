package alexx.text.diffs.compare;

import alexx.text.diffs.dfmhph.DMP;
import alexx.text.diffs.dfmhph.DiffMatchPatchByLine;

import java.util.ArrayList;
import java.util.LinkedList;

import alexx.text.diffs.exceptions.CompareException;
import alexx.text.model.CompareResults;
import alexx.text.model.Diff;
import org.springframework.stereotype.Service;

@Service
public class FileCompare {

    private int lineOrigin = 1;
    private int lineModified = 1;

    public CompareResults compare(ArrayList<String> origin, ArrayList<String> modified) throws CompareException {
        CompareResults results = new CompareResults(new ArrayList<>());
        ArrayList<Diff> diffs = new ArrayList<>();

        lineOrigin = 1;
        lineModified = 1;

        diffs = CompareToEnd(origin, modified, diffs);
        diffs = CompareToEnd(origin, "", diffs);
        diffs = CompareToEnd("", modified, diffs);
        results.setDiffList(diffs);
        return results;
    }

    private ArrayList<Diff> CompareToEnd(ArrayList<String> origin, ArrayList<String> modified, ArrayList<Diff> diffs) throws CompareException {
        while ((lineOrigin-1 < origin.size()) && (lineModified-1 < modified.size())) {
            DiffMatchPatchByLine dp = new DiffMatchPatchByLine();
            LinkedList<DMP.Diff> diff = dp.diff_byLine(origin.get(lineOrigin-1), modified.get(lineModified-1));
            diffs = fillDiffs(diff, diffs);
        }
        return diffs;
    }

    private ArrayList<Diff> CompareToEnd(ArrayList<String> origin, String modified, ArrayList<Diff> diffs) throws CompareException {
        while (lineOrigin-1 < origin.size()) {
            DiffMatchPatchByLine dp = new DiffMatchPatchByLine();
            LinkedList<DMP.Diff> diff = dp.diff_byLine(origin.get(lineOrigin-1), modified);
            diffs = fillDiffs(diff, diffs);
        }
        return diffs;
    }

    private ArrayList<Diff> CompareToEnd(String origin, ArrayList<String> modified, ArrayList<Diff> diffs) throws CompareException {
        while (lineModified-1 < modified.size()) {
            DiffMatchPatchByLine dp = new DiffMatchPatchByLine();
            LinkedList<DMP.Diff> diff = dp.diff_byLine(origin, modified.get(lineModified-1));
            diffs = fillDiffs(diff, diffs);
        }
        return diffs;
    }

    private ArrayList<Diff> fillDiffs(LinkedList<DMP.Diff> diff, ArrayList<Diff> diffs) {
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
