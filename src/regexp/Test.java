package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		// String to be scanned to find the pattern
		String line = "This order was placed for QT4000! OK?";
		String pattern = ".*Q.*";
		
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);
		
		// Create a Matcher object
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found!");
		} else {
			System.out.println("NO MATCH!");
		}
	}
}
