package alexx.text.controller;

import alexx.text.diffs.compare.FileCompare;
import alexx.text.diffs.exceptions.CompareException;
import alexx.text.model.CompareResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class CompareController {

    final FileCompare compareTexts;

    public CompareController(FileCompare compareTexts) {
        this.compareTexts = compareTexts;
    }

    @ResponseBody
    public CompareResults compare(ArrayList<String> origin, ArrayList<String> modified) throws CompareException {
        return compareTexts.compare(origin, modified);
    }
}