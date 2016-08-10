package week2;
import java.util.HashMap;
import java.util.Arrays;
/**
 * Reconstruct String from BurrowsWheelerTransform
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 10th, 2016
 */

class ReconstructString {
    Char[] lastCols ;
    Char[] sortedCols;
    HashMap<Char, Integer> charIndex ;
    String firstCol;

    ReconstructString(String lastCol){
        lastCols = new Char[lastCol.length()];
        sortedCols = new Char[lastCol.length()];
        charIndex = new HashMap<Char, Integer>();
        for (int i=0; i < lastCol.length(); i++){
            lastCols[i] = new Char(lastCol.charAt(i), i);
            sortedCols[i] = lastCols[i];
        }
        Arrays.sort(sortedCols);
        for (int i = 0; i < sortedCols.length; i++){
            charIndex.put(sortedCols[i], i);
            sortedCols[i].index = i;
        }
        firstCol = reconstruct(lastCol);
    }

    private String reconstruct(String lastCol){
        int length = lastCol.length();
        char[] chars = new char[length];
        int index = length -1;
        chars[index] = sortedCols[0].c;
        int counter = 0;
        while (index > 0){
            Char charObj = lastCols[counter];
            char ch = charObj.c;
            chars[--index] = ch;
            int charObjIndex = charIndex.get(charObj);
            counter = sortedCols[charObjIndex].index;
        }
        return new String(chars);
    }
}

class Char implements Comparable<Char>{
    char c;
    int index;

    Char(char c, int index){
        this.c = c;
        this.index = index;
    }

    @Override
    public int compareTo(Char o) {
        if (this.c > o.c){
            return 1;
        }
        if (this.c < o.c){
            return -1;
        }
        if (this.index > o.index){
            return 1;
        }
        if (this.index < o.index){
            return -1;
        }
        return 0;
    }
}