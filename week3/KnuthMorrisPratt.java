package week3;
import java.util.List;
import java.util.ArrayList;
/**
 * KnuthMorrisPratt class -
 * Find all occurances of a pattern in  a text using KnuthMorrisPratt algorithm
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 18th, 2016
 */

class KnuthMorrisPratt {

        KnuthMorrisPratt(String text, String pattern){
            ArrayList<Integer> result = findAllOccurances(pattern, text);
            print(result);
        }

        public void print(List<Integer> x) {
            for (int a : x) {
                System.out.print(a + " ");
            }
            System.out.println();
        }

        ArrayList<Integer> findAllOccurances(String pattern, String text){
            String s = pattern + "$" + text;
            int[] prefix = computePrefixFunction(s);
            ArrayList<Integer> result = new ArrayList<Integer>();
            for (int i = pattern.length()+1; i < s.length(); i++){
                if (prefix[i] == pattern.length()){
                    result.add(i - 2*pattern.length());
                }
            }
            return result;
        }

        int[] computePrefixFunction(String pattern){
            int[] s = new int[pattern.length()];
            //init step
            s[0] = 0;
            int border = 0;
            for (int i =1; i < pattern.length(); i++){
                while (border > 0 && pattern.charAt(i) != pattern.charAt(border)){
                    border = s[border-1];
                }
                if (pattern.charAt(i) == pattern.charAt(border)){
                    border = border+1;
                }else{
                    border = 0;
                }
                s[i] = border;
            }
            return s;
        }
    }