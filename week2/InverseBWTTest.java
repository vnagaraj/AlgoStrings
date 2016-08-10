package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * InverseBurrowsWheelerTransformTest class - client program to test the  reconstruct string of week2.BurrowsWheelerTransform data structure
 **
 Problem Description
 Task. Reconstruct a string from its Burrows–Wheeler transform.
 Input Format. A string Transform with a single “$” sign.
 Constraints. 1 ≤ |Transform| ≤ 1 000 000; except for the last symbol, Text contains symbols A, C, G, T
 only.
 Output Format. The string Text such that BWT(Text) = Transform. (There exists a unique such string.)

 Sample 1.
 Input:
 AC$A
 Output:
 ACA$
 Explanation:
 𝑀(Text) =
 $ 𝐴 𝐶 𝐴
 𝐴 $ 𝐴 𝐶
 𝐴 𝐶 𝐴 $
 𝐶 𝐴 $ 𝐴

 Sample 2.
 Input:
 AGGGAA$
 Output:
 GAGAGA$
 Explanation:
 𝑀(Text) =
 $ 𝐺 𝐴 𝐺 𝐴 𝐺 𝐴
 𝐴 $ 𝐺 𝐴 𝐺 𝐴 𝐺
 𝐴 𝐺 𝐴 $ 𝐺 𝐴 𝐺
 𝐴 𝐺 𝐴 𝐺 𝐴 $ 𝐺
 𝐺 𝐴 $ 𝐺 𝐴 𝐺 𝐴
 𝐺 𝐴 𝐺 𝐴 $ 𝐺 𝐴
 𝐺 𝐴 𝐺 𝐴 𝐺 𝐴 $
 **/

public class InverseBWTTest {
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
        new InverseBWTTest().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(new ReconstructString(bwt).firstCol);
    }
}


