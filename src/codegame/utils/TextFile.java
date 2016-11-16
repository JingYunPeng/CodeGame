package codegame.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5618496770708595680L;

	// Read a file as a single string:
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	// Write a single file in one method call:
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Read a file, split by any regular expression:
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		// Regular expression split() often leaves an empty
		// String at the first position:
		if (get(0).equals("")) {
			remove(0);
		}
	}

	// Normally read by lines:
	public TextFile(String fileName) {
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) {
					out.println(item);
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Simple test:
	public static void main(String[] args) {
		String folderName = "D:\\Documents\\Design Plan Study\\FilesToReadAndWrite".replace("\\\\", File.separator);
		String fileName = folderName + File.separator + "a.txt";
		String fileName2 = folderName + File.separator + "b.txt";
		String textFile = folderName + File.separator + "TextFile.java";
		String file = read(textFile);
		write(fileName, file);
		TextFile text = new TextFile(fileName);
		text.write(fileName2);
		// Break into unique sorted list of words:
		TreeSet<String> words = new TreeSet<String>(new TextFile(fileName2, "\\W+"));
		// Display the capitalized words:
		System.out.println(words.headSet("a"));

	}
}
