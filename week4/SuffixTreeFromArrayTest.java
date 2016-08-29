package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  SuffixTreeFromArrayTest - client program to test suffix tree construction from suffix array
 *
 *  Task. Construct a su x tree from the su x array and LCP array of a string.
 Input Format. The  first line contains a string Text ending with a “$” symbol, the second line contains Su xArray(Text) as a list of |Text| integers separated by spaces, the last line contains LCP(Text) as a list of |Text| − 1 integers separated by spaces.
 Constraints. 1 ≤ |Text(Text)| ≤ 2·105; except for the last symbol, Text contains symbols A, C, G, T only.
 Output Format. The output format in this problem differs from the output format in the problem “Su x Tree” from the Programming Assignment 2 and is somewhat tricky. It is because this problem is harder: the input string can be longer, so it would take too long to output all the edge labels directly and compare them with the correct ones, as their combined length can be Θ(|Text|2), which is too much when the Text can be as long as 200 000 characters.
 Output the 𝑇𝑒𝑥𝑡 from the input on the  first line.
 Then output all the edges of the suffix tree in a specific order (see below), each on its own line.
 Output each edge as a pair of integers (start, end), where start is the position in Text corresponding to the start of the
 edge label substring in the Text and end is the position right after the end of the edge label in the Text.
 Note that start must be a valid position in the Text, that is, 0 ≤ 𝑠𝑡𝑎𝑟𝑡 ≤ |Text| − 1, and end must be between 1 and
 |Text| inclusive. Substring Text[start..end − 1] must be equal to the edge label of the corresponding edge. For example, if Text = “ACACAA$” and the edge label is “CA”, you can output this edge either as (1,3) corresponding to Text[1..2] = “CA” or as (3,5) corresponding to Text[3..4] = “CA” — both variants will be accepted.

 Sample 1
 Input
 A$
 1 0
 0

 Output
 A$
 1 2
 0 2

 */

public class SuffixTreeFromArrayTest {
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
        new SuffixTreeFromArrayTest().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffixArray = new int[text.length()];
        for (int i = 0; i < suffixArray.length; ++i) {
            suffixArray[i] = scanner.nextInt();
        }
        int[] lcpArray = new int[text.length() - 1];
        for (int i = 0; i + 1 < text.length(); ++i) {
            lcpArray[i] = scanner.nextInt();
        }
        System.out.println(text);
        new SuffixTree(text, suffixArray, lcpArray);

    }
}



