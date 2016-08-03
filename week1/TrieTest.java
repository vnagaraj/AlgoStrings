package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * TrieTest class - client program to test the Trie data structure
 *
 Problem Description:
 Task. Implement TrieMatching algorithm.
 Input Format. The first line of the input contains a string Text, the second line contains an integer 𝑛,
 each of the following 𝑛 lines contains a pattern from Patterns = {𝑝1, . . . , 𝑝𝑛}.
 Constraints. 1 ≤ |Text| ≤ 10 000; 1 ≤ 𝑛 ≤ 5 000; 1 ≤ |𝑝𝑖
 | ≤ 100 for all 1 ≤ 𝑖 ≤ 𝑛; all strings contain only
 symbols A, C, G, T; no 𝑝𝑖
 is a prefix of 𝑝𝑗 for all 1 ≤ 𝑖 ̸= 𝑗 ≤ 𝑛.

 Output Format. All starting positions in Text where a string from Pattern
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
 The pattern ATCG appears at positions 1 and 11, the pattern GGGT appears at positions 4 and 15.
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 2nd, 2016
 */

public class TrieTest {
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
        new TrieTest().run();
    }


    void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        Trie trie = new Trie(patterns);
        trie.printoutTrie();
    }
}
