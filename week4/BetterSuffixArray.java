package week4;

import java.util.HashMap;

/**
 * BetterSuffixArray to compute suffixes of array by using only liner memory and O(|S|log|S| + alphabet_size)
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 22nd, 2016
 */
class BetterSuffixArray{
    int[] order; // compute order of the suffixes
    private static HashMap<Character, Integer> charIntMap = getCharIntMap();


    BetterSuffixArray(String s){
        //init step
        order = sortCharacters(s);
        int[] charClasses = computeCharClasses(s, order);
        int L = 1;
        while (L < s.length()){
            order = sortDoubled(s, L, order, charClasses);
            charClasses = updateClasses(order, charClasses, L);
            L = 2 * L;
        }
    }

    /**
     * Populate character Integer hash map
     */
    private static HashMap<Character, Integer> getCharIntMap(){
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();
        characterIntegerHashMap.put('$', 0);
        characterIntegerHashMap.put('A', 1);
        characterIntegerHashMap.put('C', 2);
        characterIntegerHashMap.put('G', 3);
        characterIntegerHashMap.put('T', 4);
        return characterIntegerHashMap;
    }

    /**
     * Sort single characters of String using non comparison based count and sort in time O(|S| + alphabet_size)
     * @param s characters of s to be sorted
     */
    private int[] sortCharacters(String s){
        order = new int[s.length()];
        int[] count = new int[5];
        //counting occurances of each character in the string
        for (int i=0; i < s.length(); i++){
            char c = s.charAt(i);
            int index = charIntMap.get(c);
            count[index] += 1;
        }
        //aggregating count
        for (int i = 1; i < count.length; i++){
            count[i] = count[i] + count[i-1];
        }
        //computing order
        for (int i= s.length()-1; i >=0; i--){
            char c = s.charAt(i);
            int index = charIntMap.get(c);
            count[index] -= 1;
            order[count[index]] = i;
        }
        return order;
    }

    /**
     * Compute char classes for single characters in string
     * Running time is O(|S|)
     * @param s of the String
     * @param order initial arrangement of the characters
     * @return char classes
     */
    private int[] computeCharClasses(String s, int[] order){
        //init step
        int[] charClass = new int[s.length()];
        charClass[order[0]] = 0;

        for (int i =1; i < order.length; i++){
            char currentChar = s.charAt(order[i]);
            char prevChar = s.charAt(order[i-1]);
            if (prevChar != currentChar){
                charClass[order[i]] = charClass[order[i-1]] + 1;
            } else{
                charClass[order[i]] = charClass[order[i-1]];
            }
        }
        return charClass;
    }

    /**
     * Sort characters of String  of Length L using charClass  in time O(|S|)
     * @param s characters of s to be sorted
     * @param L length of characters to be sorted
     * @param order order of the Characters provided
     * @param charClass character class of the String
     * @return new order
     */
    private int[] sortDoubled(String s, int L, int[] order, int[] charClass){
        int[] count = new int[s.length()];
        int[] newOrder = new int[s.length()];
        //performing sort on charClasses
        for (int i=0; i < s.length(); i++){
            count[charClass[i]] += 1;
        }
        for (int i =1; i < s.length(); i++){
            count[i] +=  count[i-1];
        }
        //computing newOrder
        for (int i = s.length()-1; i >=0; i--){
            int start = (order[i] - L + s.length()) % s.length();
            int cl = charClass[start];
            count[cl] -= 1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }

    /**
     * Update the charClass after performing sortDoubles in time O(|S|)
     * @param newOrder the order of the characters in string of size L
     * @param charClasses the charClasses of the characters of size L
     * @param L length of characters
     * @return new character classes
     */
    private int[] updateClasses(int[] newOrder, int[] charClasses, int L){
        int[] newClass = new int[newOrder.length];
        newClass[newOrder[0]] = 0;
        for (int i=1; i <  newOrder.length; i++){
            int current = newOrder[i];
            int prev = newOrder[i-1];
            int mid = current + L;
            int midPrev = (prev + L) % newOrder.length;
            if (charClasses[current] != charClasses[prev] || charClasses[mid] != charClasses[midPrev]){
                newClass[current] = newClass[prev] + 1;
            }else{
                newClass[current] = newClass[prev];
            }
        }
        return newClass;
    }

}
