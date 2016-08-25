package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SuffixArrayMatching to find indices of matching patterns in text using BetterSuffixArray)
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 25th, 2016
 *
 * Problem Description
Task. Find all occurrences of a given collection of patterns in a string.

Input Format. The first line contains a string Text). The second line specifies an integer 𝑛. The last line
gives a collection of 𝑛 strings Patterns = {𝑝1, . . . , 𝑝𝑛} separated by spaces.
Constraints. All strings contain symbols A, C, G, T only; 1 ≤ |Text| ≤ 105

Output Format. All starting positions (in any order) in Text where a pattern appears as a substring (using
0-based indexing as usual)

Sample 1.
Input:
AAA
1
A
Output:
0 1 2
Explanation:
The pattern A appears at positions 0, 1, and 2 in the text.

Sample 2.
Input:
ATA
3
C G C
Output:
Explanation:
There are no occurrences of the patterns in the text

Sample 3.
Input:
ATATATA
3
ATA C TATAT
Output:
4 2 0 1
 */

public class SuffixArrayMatching {
    class fastscanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        fastscanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextint() throws IOException {
            return Integer.parseInt(next());
        }
    }


    public int[] computeSuffixArray(String text) {
        return new BetterSuffixArray(text).order;
    }

    public void findOccurrences(String pattern, String text, int[] suffixArray, boolean[] result) {
        Integer start = -1;
        Integer end = -1;
        int minIndex = 0;
        int maxIndex = text.length()-1;
        //computing start
        while (minIndex <= maxIndex){
            int midIndex = (maxIndex - minIndex)/2 + minIndex;
            int pos = suffixArray[midIndex];
            int cmp = comparePrefixSuffix(pattern, pos, text);
            if (cmp == 0){
                //pattern is prefix of suffix
                start = midIndex;
                maxIndex = midIndex-1;
            } else if (cmp > 0){
                minIndex = midIndex + 1;
            }
            else{

                maxIndex = midIndex-1;
            }
        }
        if (start == -1){
            return  ;
        }
        minIndex = 0;
        maxIndex = text.length()-1;
        //computing end
        while (minIndex <= maxIndex){
            int midIndex = (maxIndex - minIndex)/2 + minIndex;
            int pos = suffixArray[midIndex];
            //String suffix = text.substring(pos);
            int cmp = comparePrefixSuffix(pattern, pos, text);
            if (cmp == 0){
                //pattern is prefix of suffix
                end = midIndex;
                minIndex = midIndex + 1;
            } else if (cmp > 0){
                minIndex = midIndex + 1;
            }
            else{
                maxIndex = midIndex-1;;
            }
        }
        for (int i= start; i <= end; i++){
            if (!result[i]){
                System.out.print(suffixArray[i] + " ");
                result[i] = true;
            }
        }
    }

    public int comparePrefixSuffix(String prefix, int posText, String text){
        int count = 0;
        for (int i = 0; i < prefix.length(); i++){
            if (prefix.charAt(i) == text.charAt(posText)){
                count +=1;
                posText += 1;
            }
            else if (prefix.charAt(i) < text.charAt(posText)){
                if (count == prefix.length()){
                    return 0;
                }
                return -1;
            }else{
                if (count == prefix.length()){
                    return 0;
                }
                return 1;
            }
        }
        return 0;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayMatching().run();
    }

    public void run() throws IOException {
        fastscanner scanner = new fastscanner();
        String text = scanner.next() + "$";
        int[] suffixArray = computeSuffixArray(text);
        int patternCount = scanner.nextint();
        boolean[] result = new boolean[text.length()];
        for (int patternIndex = 0; patternIndex < patternCount; ++patternIndex) {
            String pattern = scanner.next();
            findOccurrences(pattern, text, suffixArray, result);
        }
    }
}