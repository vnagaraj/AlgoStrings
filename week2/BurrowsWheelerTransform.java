package week2;

import java.util.Arrays;

/**
 * BurrowsWheelerTransform class -
 * Given a string Text, form all possible cyclic rotations of Text; a cyclic rotation is defined by chopping off
 a suffix from the end of Text and appending this suffix to the beginning of Text. Next — similarly to suffix
 arrays — order all the cyclic rotations of Text lexicographically to form a |Text| × |Text| matrix of symbols
 that we call the Burrows–Wheeler matrix and denote by 𝑀(Text).
 Note that the first column of 𝑀(Text) contains the symbols of Text ordered lexicographically. In turn,
 the second column of 𝑀(Text) contains the second symbols of all cyclic rotations of Text, and so it too
 represents a (different) rearrangement of symbols from Text. The same reasoning applies to show that any
 column of 𝑀(Text) is some rearrangement of the symbols of Text. We are interested in the last column of
 𝑀(Text), called the Burrows–Wheeler transform of Text, or BWT(Text).
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 9th, 2016
 */
class BurrowsWheelerTransform{
    private String[] cylicText;
    String finalCol;

    BurrowsWheelerTransform(String text){
        cylicText = new String[text.length()];
        createCyclicTexts(text);
        Arrays.sort(cylicText);
        finalCol = getFinalCol();

    }

    private void createCyclicTexts(String text){
        int length = text.length();
        for (int i=0; i < length; i++){
            char[] chars = new char[text.length()];
            int counter = 0;
            int index = i;
            while (counter < text.length()){
                char c = text.charAt(index++%length);
                chars[counter++] = c;
            }
            cylicText[i] = new String(chars);
        }
    }

    private String getFinalCol(){
        char[] chars = new char[cylicText.length];
        for (int i=0; i < cylicText.length; i++){
            char c = cylicText[i].charAt(cylicText.length-1);
            chars[i] = c;
        }
        return new String(chars);
    }
}
