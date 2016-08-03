package week1;

import java.io.*;


/**
 * TrieMatchingTest class - client program to test the week1.TrieMatching data structure
 *
 * Problem Description
 Task. Implement TrieMatching algorithm.
 Input Format. The first line of the input contains a string Text, the second line contains an integer 𝑛,
 each of the following 𝑛 lines contains a pattern from Patterns = {𝑝1, . . . , 𝑝𝑛}.
 Constraints. 1 ≤ |Text| ≤ 10 000; 1 ≤ 𝑛 ≤ 5 000; 1 ≤ |𝑝𝑖
 | ≤ 100 for all 1 ≤ 𝑖 ≤ 𝑛; all strings contain only
 symbols A, C, G, T; no 𝑝𝑖
 is a prefix of 𝑝𝑗 for all 1 ≤ 𝑖 ̸= 𝑗 ≤ 𝑛.
 Output Format. All starting positions in Text where a string from Patterns appears as a substring in
 increasing order (assuming that Text is a 0-based array of symbols).

 Sample 1.
 Input:
 AAA
 1
 AA
 Output:
 0 1
 Explanation:
 The pattern AA appears at positions 0 and 1. Note that these two occurrences of the pattern overlap.

 Sample 2.
 Input:
 AA
 1
 T
 Output:
 Explanation:
 There are no occurrences of the pattern in the text.

 Sample 3.
 Input:
 AATCGGGTTCAATCGGGGT
 2
 ATCG
 GGGT
 Output:
 1 4 11 15
 Explanation:
 The pattern AA appears at positions 0 and 1. Note that these two occurrences of the pattern overlap.

 Sample 4.
 Input:
 ACATA
 3
 AT
 A
 AG
 Output:
 0 2 4
 Explanation:
 Text contains occurrences of A at positions 0, 2, and 4, as well as an occurrence of AT at position 2.
 Note that the trie looks as follows in this case:
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 August 2nd, 2016
 */
public class TrieMatchingTest implements Runnable {

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	String[] patterns = new String[n];
			for (int i = 0; i < n; i++) {
				patterns[i] = in.readLine ();
			}
			TrieMatching trieMatching = new TrieMatching(text, patterns);
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingTest ()).start ();
	}
}



