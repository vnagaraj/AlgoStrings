package week2;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Class to implement pattern matching using BurrowsWheelTransformer
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 September 5th, 2016
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
        // firstCol in cyclic rotation of sorted text
        char[] firstCol = getFirstCol(text);
        // lastCol in cyclic rotation of sorted text
        char[] lastCol = text.toCharArray();
        Integer[] firstOccurance = getFirstOccurance(firstCol);
        int[][] count = getCount(lastCol);
        for (int i = 0; i < patterns.length; i++){
            result[i] = bwtMatch(text, patterns[i], firstOccurance, count);
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

    private int bwtMatch(String text,
                         String pattern,
                         Integer[] firstOccurance,
                         int[][] count){
        //System.out.println("Reconstruct String " + new ReconstructString(text).firstCol);
        int top = 0;
        int bottom = text.length() -1;
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