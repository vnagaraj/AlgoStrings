package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BurrowsWheelerTransformTest class - client program to test the week2.BurrowsWheelerTransform data structure
 *
 Problem Description
 Task. Construct the Burrows–Wheeler transform of a string.
 Input Format. A string Text ending with a “$” symbol.
 Constraints. 1 ≤ |Text| ≤ 1 000; except for the last symbol, Text contains symbols A, C, G, T only.
 Output Format. BWT(Text).

 Sample 1.
 Input:
 AA$
 Output:
 AA$
 Explanation:
 𝑀(Text) =

 $ 𝐴 𝐴
 𝐴 $ 𝐴
 𝐴 𝐴 $


 Sample 2.
 Input:
 ACACACAC$
 Output:
 CCCC$AAAA
 Explanation:
 𝑀(Text) =

 $ 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶
 𝐴 𝐶 $ 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶
 𝐴 𝐶 𝐴 𝐶 $ 𝐴 𝐶 𝐴 𝐶
 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 $ 𝐴 𝐶
 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 $
 𝐶 $ 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 𝐴
 𝐶 𝐴 𝐶 $ 𝐴 𝐶 𝐴 𝐶 𝐴
 𝐶 𝐴 𝐶 𝐴 𝐶 $ 𝐴 𝐶 𝐴
 𝐶 𝐴 𝐶 𝐴 𝐶 𝐴 𝐶 $ 𝐴

 Sample 3.
 Input:
 AGACATA$
 Output:
 ATG$CAAA
 Explanation:
 𝑀(Text) =

 $ 𝐴 𝐺 𝐴 𝐶 𝐴 𝑇 𝐴
 𝐴 $ 𝐴 𝐺 𝐴 𝐶 𝐴 𝑇
 𝐴 𝐶 𝐴 𝑇 𝐴 $ 𝐴 𝐺
 𝐴 𝐺 𝐴 𝐶 𝐴 𝑇 𝐴 $
 𝐴 𝑇 𝐴 $ 𝐴 𝐺 𝐴 𝐶
 𝐶 𝐴 𝑇 𝐴 $ 𝐴 𝐺 𝐴
 𝐺 𝐴 𝐶 𝐴 𝑇 𝐴 $ 𝐴
 𝑇 𝐴 $ 𝐴 𝐺 𝐴 𝐶 𝐴
 */

public class BurrowsWheelerTransformTest {
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
        new BurrowsWheelerTransformTest().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(new BurrowsWheelerTransform(text).finalCol);
    }
}

