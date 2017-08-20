package io;

import java.io.*;

public class FileDirectoryManagement {
	public static void main(String[] args) {
		String dirname = "/Users/comestime/Dropbox/Graduate/Open Courses/Cousera Algorithm/Programming_Assignment/src/io";
		
		// a directory is a File which can contain a list of other files and directories
		File d = new File(dirname + "/test");
		
		// create directory now
		d.mkdirs();
		
		// list files and subdirectories
		d = new File(dirname);
		String[] paths = d.list();
		for (String p : paths) {
			System.out.println(p);
		}
	}
}
