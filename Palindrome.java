import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Palindrome {

	private static final String FILE_NAME = "test/Palindrome.txt";

	public static void main(String[] args) throws FileNotFoundException {

		try(Scanner reader = new Scanner(new File(FILE_NAME))) {
			// String buffer for storing the output
			StringBuilder output = new StringBuilder();

			String strLine;

			int activeTestCaseNumber = 0;
			while (reader.hasNextLine()) {
				
				strLine = reader.nextLine();
				activeTestCaseNumber++;

				// Invoke algorithm here
				boolean solutionToTestCase = isPalindrome(strLine);

				// Prepare output string
				output.append("Case #").append(activeTestCaseNumber).append(": ").append(solutionToTestCase);
				output.append(System.lineSeparator());
			}

			// Pass output string to method to write to file
			writeOutputToFile(output.toString());
		} 
	}

	/**
	 * Time complexity: O(N)
	 * 
	 * @param palindrome
	 * @return
	 */
	public static boolean isPalindrome(String palindrome) {
		
		String s = palindrome.toLowerCase();
		
		if (s.length() == 0 || s.length() == 1)
			// if length =0 OR 1 then it is
			return true;
		if (s.charAt(0) == s.charAt(s.length() - 1))
			// check for first and last char of String:
			// if they are same then do the same thing for a substring
			// with first and last char removed. and carry on this
			// until you string completes or condition fails
			return isPalindrome(s.substring(1, s.length() - 1));

		// if its not the case than string is not.
		return false;
	}

	private static void writeOutputToFile(String str) {
		Path file = Paths.get(FILE_NAME);
		try {
			Files.write(file, str.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
