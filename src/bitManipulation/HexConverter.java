/*
Generate the hexadecimal representation for a given non-negative integer number as a string. The hex representation should start with "0x".

There should not be extra zeros on the left side.

Examples

0's hex representation is "0x0"
255's hex representation is "0xFF"
 */

package bitManipulation;

public class HexConverter {
	public String hexConvert(int x) {
		StringBuilder builder = new StringBuilder();
		if (x == 0) builder.append('0');
		while (x != 0) {
			int curHexDigits = x & 15;
			char curHexDigit;
			if (curHexDigits < 10) {
				curHexDigit = (char)('0' + curHexDigits - 0);
			} else {
				curHexDigit = (char)('A' + curHexDigits - 10);
			}
			builder.append(curHexDigit);
			x >>= 4;
		}
		builder.append('x');
		builder.append('0');
		builder.reverse();
		return builder.toString();
	}
	
	public static void main(String[] args) {
		HexConverter solution = new HexConverter();
		System.out.println(solution.hexConvert(255));
		System.out.println(solution.hexConvert(254));
		System.out.println(solution.hexConvert(253));
	}
}
