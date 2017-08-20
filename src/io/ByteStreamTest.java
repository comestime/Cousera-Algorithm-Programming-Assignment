package io;

import java.io.*;

public class ByteStreamTest {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		
		// Example 1: using read() API
		try {
			in = new FileInputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/input.txt");
			out = new FileOutputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/output1.txt");
			int c;
			// in.read() returns a int, with lower 8 bits as valid byte data
			// returns -1 when the stream is over
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
		
		// Example 2: using read(byte[] bytes) API
		try {
			in = new FileInputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/input.txt");
			out = new FileOutputStream("/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io/output2.txt");
			
			// in.read(byte[] bytes) returns a int, representing numbe of bytes read from the stream
			// and stores the byte data into bytes array
			byte[] bytes = new byte[7];
			int n = in.read(bytes);
			out.write(bytes);
			System.out.println("n = " + n);
			for (byte b : bytes) {
				System.out.print((char)b);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
