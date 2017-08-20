package io;

import java.io.*;

public class LineByLineReadTest {
	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		BufferedReader br = null;
		try {
			in = new FileInputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/input.txt");
			
			// construct BufferedReader from InputStreamBuffer
			br = new BufferedReader(new InputStreamReader(in));
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			br.close();
		}
	}
}
