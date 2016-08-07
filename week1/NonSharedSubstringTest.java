package week1;

import java.io.*;

/**
 *
 * NonSharedSubstringTest class - client program to test the week1.MinSharedSuffixTrie data structure
 * DID NOT PASS GRADER
 *
 Problem Description
 Task. Find the shortest substring of one string that does not appear in another string.
 Input Format. Strings Text1 and Text2.
 Constraints. 1 ≤ |Text1|,|Text2| ≤ 2000; strings have equal length (|Text1| = |Text2|), are not equal (Text1 ̸= Text2), and contain symbols A, C, G, T only.
 Output Format. The shortest (non-empty) substring of Text1 that does not appear in Text2. (Multiple solutions may exist, in which case you may return any one.)

 Sample 1.
 Input:
 Output:
 A

 Sample 2.
 Input:
 AAAAAAAAAAAAAAAAAAAA
 TTTTTTTTTTTTTTTTTTTT
 Output:
 A


 Sample 3.
 Input:
 AAAAAAAAAAAAAAAAAAAA
 TTTTTTTTTTTTTTTTTTTT
 CCAAGCTGCTAGAGG
 CATGCTGGGCTGGCT
 14
 Output:
 AA

 Sample 4.
 Input:
 Output:
 ATG
 */

public class NonSharedSubstringTest implements Runnable {

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String p = in.readLine ();
			String q = in.readLine ();
			long time1 = System.currentTimeMillis();
			MinSharedSuffixTrie minSharedSuffixTrie = new MinSharedSuffixTrie(p+ "X" + q + "Y");
			System.out.println(minSharedSuffixTrie.minShared);
			long time2 = System.currentTimeMillis();
			System.out.println((time2- time1) * 0.001);
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new NonSharedSubstringTest()).start ();
	}
}




