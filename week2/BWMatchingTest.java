package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BWMatchingTest - client program to test BWMatching
 * Did not pass grader
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

/**
 * Class to implement pattern matching using BurrowsWheelTransformer
 */
class BWTMatching{
    int[] result;
    public static int A = 0;
    public static int C = 1;
    public static int G = 2;
    public static int T = 3;
    public static int $ = 4;
    public static HashMap<Character, Integer> characterIntegerHashMap;

    BWTMatching(String text, String[] patterns){
        popCharIntegerHashMap();
        result = new int[patterns.length];
        for (int i = 0; i < patterns.length; i++){
            result[i] = bwtMatch(text, patterns[i]);
        }
    }

    /**
     * Populate character Integer hash map
     */
    private void popCharIntegerHashMap(){
        characterIntegerHashMap = new HashMap<Character, Integer>();
        characterIntegerHashMap.put('A', A);
        characterIntegerHashMap.put('C', C);
        characterIntegerHashMap.put('T', T);
        characterIntegerHashMap.put('G', G);
        characterIntegerHashMap.put('$', $);
    }

    private char[] getFirstCol(String text){
        char[] firstCol = new char[text.length()];
        for (int i=0; i < text.length(); i++){
            firstCol[i] = text.charAt(i);
        }
        Arrays.sort(firstCol);
        return firstCol;
    }

    private int bwtMatch(String text, String pattern){
        //System.out.println("Reconstruct String " + new ReconstructString(text).firstCol);
        // firstCol in cyclic rotation of sorted text
        char[] firstCol = getFirstCol(text);
        // lastCol in cyclic rotation of sorted text
        char[] lastCol = text.toCharArray();
        Integer[] firstOccurance = getFirstOccurance(firstCol);
        int[][] count = getCount(lastCol);
        int top = 0;
        int bottom = lastCol.length -1;
        int index = pattern.length()-1; //last letter in pattern
        while (top <= bottom){
            //System.out.println("Top " + top);

            //System.out.println("Bottom " + bottom);
            if (index >= 0){
             char symbol = pattern.charAt(index--);
             int symbol_index = characterIntegerHashMap.get(symbol);
             if (containsSymbol(top, bottom, symbol, count)){
                 top = firstOccurance[symbol_index] + count[top][symbol_index];
                 bottom = firstOccurance[symbol_index] + count[bottom+1][symbol_index] -1;
             }else{
                 return 0;
             }
            } else{
                return bottom - top +1;
            }
        }

        return 0;
    }

    /**
     * Checks if positions from top to bottom in last column contain an occurance of symbol
     * @param top position
     * @param bottom position
     * @param symbol (A/C/T/G/$)
     * @param count # of occurances of symbol in first i positions of text
     * @return true if symbol is present
     */
    private boolean containsSymbol(int top, int bottom, char symbol, int[][] count){
        int index = characterIntegerHashMap.get(symbol);
        if (count[bottom+1][index] - count[top+1][index] + count[top+1][index] >0 ){
            return true;
        }
        return false;
    }

    /**
     * First position of symbol(A,C,T,G,$) in first column
     * @param firstCol of the sorted cyclic rotation of text
     * @return Integer[]
     */
    private Integer[] getFirstOccurance(char[] firstCol){
        Integer[] firstOccurance = new Integer[5];
        int counter = 0;
        for (int i=0; i < firstCol.length; i++){
            if (counter == 5){
                break;
            }
            char symbol = firstCol[i];
            int index = characterIntegerHashMap.get(symbol);
            if (firstOccurance[index] == null){
                firstOccurance[index] = i;
                counter ++;
            }
        }
        return  firstOccurance;
    }

    /**
     * No of occurances of symbol in the first i positions of last column
     * @param lastCol of the sorted cyclic rotation of text
     * @return
     */
    private int[][] getCount(char[] lastCol){
        int[][] count = new int[lastCol.length+1][5];
        for (int i= 0; i < lastCol.length; i++){

            for (int j = 0; j < count[0].length; j++) {
                    count[i+1][j] = count[i][j];
            }
            char symbol = lastCol[i];
            int index = characterIntegerHashMap.get(symbol);
            if (lastCol[i] == symbol){
                count[i+1][index] = count[i][index] + 1;
            }
        }
        return count;
    }
}

