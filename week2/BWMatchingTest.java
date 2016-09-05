package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BWMatchingTest - client program to test BWMatching
 *
 * Problem Description
 Task. Implement BetterBWMatching algorithm.
 Input Format. A string BWT(Text), followed by an integer 𝑛 and a collection of 𝑛 strings Patterns =
 {𝑝1, . . . , 𝑝𝑛} (on one line separated by spaces).
 Constraints. 1 ≤ |BWT(Text)| ≤ 106
 ; except for the one $ symbol, BWT(Text) contains symbols A, C,
 G, T only; 1 ≤ 𝑛 ≤ 5 000; for all 1 ≤ 𝑖 ≤ 𝑛, 𝑝𝑖
 is a string over A, C, G, T; 1 ≤ |𝑝𝑖
 | ≤ 1 000.
 Output Format. . A list of integers, where the 𝑖-th integer corresponds to the number of substring matches
 of the 𝑖-th member of Patterns in Text.

 Sample 1.
 Input:
 AGGGAA$
 1
 GA
 Output:
 3
 Explanation:
 In this case, Text = GAGAGA$. The pattern GA appears three times in it.

 Sample 2.
 Input:
 ATT$AA
 2
 ATA A
 8
 Output:
 2 3
 Explanation:
 Text = ATATA$ contains two occurrences of ATA and three occurrences of A.


 Sample 3.
 Input:
 AT$TCTATG
 2
 TCT TATG
 Output:
 0 0
 Explanation:
 Text = ATCGTTTA does not contain any occurrences of two given patterns.

 *
 *
 */
public class BWMatchingTest {
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

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        int n = scanner.nextInt();
        String[] patterns = new String[n];
        for (int i =0; i < n; i++){
            patterns[i] = scanner.next();
        }
        //output
        BWTMatching bwtMatching = new BWTMatching(bwt, patterns);
        for (int i = 0; i < n; i++ ){
            System.out.print(bwtMatching.result[i] + " ");
        }

    }

    static public void main(String[] args) throws IOException {
        new BWMatchingTest().run();
    }
}



