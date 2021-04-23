package demo;

import compare.FileCompare;

import java.io.IOException;

public class DemoApplication {

	public static void main(String[] args) throws IndexOutOfBoundsException {
		String f1, f2;
		try {
			f1 = args[0];
			f2 = args[1];
		}
		catch (IndexOutOfBoundsException ex) {
			System.err.println("Attributes are unknown or don't exist.\nPlease enter File names correctly.\nOne then another.");
			return;
		}
		FileCompare fc = new FileCompare();
		try {
			fc.Compare(f1, f2);
		}
		catch (IOException ex) {
			System.err.println("Files don't exist or\nnames aren't correctly.");
			return;
		}
	}
}
