package alexx.text.extractors;

import java.io.*;
import java.util.ArrayList;

public class TextExtractor {
    public static ArrayList<String> extractText(InputStream stream) throws IOException {
        ArrayList<String> text = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = reader.readLine()) != null)
                text.add(line);
        }
        catch (IOException ex) {
            throw ex;
        }
        return text;
    }
}