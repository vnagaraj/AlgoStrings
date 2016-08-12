package week2;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Suffix Array from suffixes of strings
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 12th, 2016
 */
class SuffixArray{
    int[] indices;
    private HashMap<String, Integer> map;

    SuffixArray(String text){
        map = new HashMap<>();
       indices =  computeSuffixes(text);
    }

    private int[] computeSuffixes(String text){
        int[] indices = new int[text.length()];
        String[] suffixes = new String[text.length()];
        for (int i=0; i < text.length(); i++){
            suffixes[i] = text.substring(i);
            map.put(suffixes[i], i);
        }
        Arrays.sort(suffixes);
        for (int i=0; i < suffixes.length; i++){
            String suffix = suffixes[i];
            int index = map.get(suffix);
            indices[i] = index;
        }
        return indices;
    }

}