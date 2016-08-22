package week4;

import java.util.*;
import java.io.*;

/**
 * SuffixArrayTest - client program to test suffixes of String
 *
 * Problem Description
 Task. Construct the suffix array of a string.
 Input Format. A string Text ending with a “$” symbol.
 Constraints. 1 ≤ |Text(Text)| ≤ 2 · 105; except for the last symbol, Text contains symbols A, C, G, T only.
 Output Format. SuffixArray(Text), that is, the list of starting positions of sorted suffixes separated by
 spaces.

 Sample 1.
 Input:
 AAA$
 Output:
 3 2 1 0
 Explanation:
 Sorted suffixes:
 3 $
 2 A$
 1 AA$
 0 AAA$

 Sample 2.
 Input:
 GAC$
 Output:
 3 1 2 0
 Explanation:
 Sorted suffixes:
 3 $
 1 AC$
 2 C$
 0 GAC$

 Sample 3.
 Input:
 GAGAGAGA$
 Output:
 8 7 5 3 1 6 4 2 0
 Explanation:
 Sorted suffixes:
 8 $
 7 A$
 5 AGA$
 3 AGAGA$
 1 AGAGAGA$
 6 GA$
 4 GAGA$
 2 GAGAGA$
 0 GAGAGAGA$

 Sample 4.
 Input:
 AACGATAGCGGTAGA$
 Output:
 15 14 0 1 12 6 4 2 8 13 3 7 9 10 11 5
 Explanation:
 Sorted suffixes:
 15 $
 14 A$
 0 AACGATAGCGGTAGA$
 1 ACGATAGCGGTAGA$
 12 AGA$
 6 AGCGGTAGA$
 4 ATAGCGGTAGA$
 2 CGATAGCGGTAGA$
 8 CGGTAGA$
 13 GA$
 3 GATAGCGGTAGA$
 7 GCGGTAGA$
 9 GGTAGA$
 10 GTAGA$
 11 TAGA$
 5 TAGCGGTAGA$
 */

public class SuffixArrayTest {
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
        new SuffixArrayTest().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = new BetterSuffixArray(text).order;
        print(suffix_array);
    }
}

