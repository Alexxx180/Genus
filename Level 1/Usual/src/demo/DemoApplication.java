package demo;

import compare.FileCompare;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoApplication {

	public static String fileName() throws IOException {
		String s;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			s = br.readLine();
		}
		catch (IOException ex) {
			throw new IOException(ex);
		}
		return s;
	}

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {
		String f1, f2;
		try {
			f1 = args[0];
			f2 = args[1];
		}
		catch (IndexOutOfBoundsException ex) {
			System.err.println("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another.");
			f1 = fileName();
			f2 = fileName();
		}

		FileCompare fc = new FileCompare();
		fc.Compare(f1, f2);
	}
}
