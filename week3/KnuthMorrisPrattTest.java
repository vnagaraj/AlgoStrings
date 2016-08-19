package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * KnuthMorrisPrattTest class - client program to test the problem of finding all occurances of a pattern in text
 *
 *Problem Description
 Task. Find all occurrences of a pattern in a string.
 Input Format. Strings 𝑃 𝑎𝑡𝑡𝑒𝑟𝑛 and 𝐺𝑒𝑛𝑜𝑚𝑒.
 Constraints. 1 ≤ |𝑃 𝑎𝑡𝑡𝑒𝑟𝑛| ≤ 106
 ; 1 ≤ |𝐺𝑒𝑛𝑜𝑚𝑒| ≤ 106
 ; both strings are over A, C, G, T.
 Output Format. All starting positions in 𝐺𝑒𝑛𝑜𝑚𝑒 where 𝑃 𝑎𝑡𝑡𝑒𝑟𝑛 appears as a substring (using 0-based
 indexing as usual).

 Sample 1.
 Input:
 TACG
 GT
 Output:
 Explanation:
 The pattern is longer than the text and hence has no occurrences in the text.

 Sample 2.
 Input:
 ATA
 ATATA
 Output:
 0 2
 Explanation:
 The pattern appears at positions 1 and 3 (and these two occurrences overlap each other).

 Sample 3.
 Input:
 ATAT
 GATATATGCATATACTT
 Output:
 1 3 9
 Explanation:
 The pattern appears at positions 1, 3, and 9 in the text
 *
**/
public class KnuthMorrisPrattTest {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }


    static public void main(String[] args) throws IOException {
        new KnuthMorrisPrattTest().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        new KnuthMorrisPratt(text, pattern);
    }


}
