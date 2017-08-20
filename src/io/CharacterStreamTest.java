package io;

import java.io.*;

public class CharacterStreamTest {
	public static void main(String[] args) throws IOException {
		// character streams are used for input/output for 16-bit unicode
		FileReader in = null;
		FileWriter out = null;
		
		// Example 1: chinese characters are two bytes
		try {
			in = new FileReader("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/chineseinput.txt");
			out = new FileWriter("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/chineseoutput1.txt");
			int c;
			while ((c = in.read()) != -1) {
				System.out.print((char)c);
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			
			if (out != null) {
				out.close();
			}
		}
		
		// Example 2: attempting to print out chinese characters in one byte
		// showing garbage in console, but the right content in output file
		FileInputStream in1 = null;
		FileOutputStream out1 = null;
		
		try {
			in1 = new FileInputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/chineseinput.txt");
			out1 = new FileOutputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/chineseoutput2.txt");
			
			int c;
			while ((c = in1.read()) != -1) {
				System.out.print((char)c);
				out1.write(c);
			}
		} finally {
			if (in1 != null) {
				in1.close();
			}
			
			if (out1 != null) {
				out1.close();
			}
		}
		
	}
}
