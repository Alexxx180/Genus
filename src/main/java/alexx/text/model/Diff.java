package alexx.text.model;

import java.util.Objects;
import alexx.text.diffs.dfmhph.DMP;

public class Diff {
    private DMP.Operation diffType;
    private String originalText;
    private String modifiedText;
    private int line;

    public Diff(DMP.Operation diffType, String originalText, String modifiedText, int line) {
        this.diffType = diffType;
        this.originalText = originalText;
        this.modifiedText = modifiedText;
        this.line = line;
    }

    public DMP.Operation getDiffType() {
        return diffType;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getModifiedText() {
        return modifiedText;
    }

    public int getLine() {
        return line;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diffType, originalText, modifiedText, line);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "diffType=" + diffType +
                ", originalText='" + originalText + '\'' +
                ", modifiedText='" + modifiedText + '\'' +
                ", lineNo=" + line +
                '}';
    }
}
