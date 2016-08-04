package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SuffixTreeTest class - client program to test the week1.SuffixTree data structure
 *
 Problem Description
 Task. Construct the suffix tree of a string.
 Input Format. A string Text ending with a “$” symbol.
 Constraints. 1 ≤ |Text| ≤ 5 000; except for the last symbol, Text contains symbols A, C, G, T only.
 Output Format. The strings labeling the edges of SuffixTree(Text) in any order.

 Sample 1.
 Input:
 A$
 Output:
 A$
 $

 Sample 2.
 Input:
 ACA$
 Output:
 $
 A
 $
 CA$
 CA$

 Sample 3.
 Input:
 ATAAATG$
 Output:
 AAATG$
 G$
 T
 ATG$
 TG$
 A
 A
 AAATG$
 G$
 T
 G$
 $
**/
public class SuffixTreeTest {
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
        new SuffixTreeTest().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        SuffixTree suffixTree = new SuffixTree(text);
        suffixTree.printoutSuffixTree();
    }
}
