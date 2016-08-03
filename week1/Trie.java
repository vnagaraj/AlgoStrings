package week1;
import java.util.*;

/**
 * Trie class - implement the Trie data structure to store String pattern
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 2nd, 2016
 */
class Trie{

    TrieNode root;
    private int counter;

    Trie(String[] patterns){
        root = new TrieNode(null);
        buildTrie(patterns);
    }

    /**
     * build a trie from a pattern of Strings
     * @param patterns
     */
    private void buildTrie(String[] patterns) {
        for (String pattern: patterns){
            TrieNode trieNode = root;
            for (int char_index=0; char_index < pattern.length(); char_index++){
                Character c = pattern.charAt(char_index);
                trieNode = insertTrie(c, trieNode);
                if (char_index == pattern.length() -1) {
                    trieNode.pattern = true;
                }
            }
        }
    }

    /**
     * print out contents of trie
     */
    void printoutTrie(){
        printoutTrie(root);
    }

    /**
     * Recursive helper to print out trie contents
     * @param trieNode TrieNode
     */
    private void printoutTrie(TrieNode trieNode){
        if (trieNode == null){
            return;
        }
        for (Map.Entry<Character, TrieNode> child : trieNode.children.entrySet()) {
                System.out.println(trieNode.val + "->" + child.getValue().val + ":" + child.getKey());
                printoutTrie(child.getValue());
        }
    }

    /**
     * Insert character into trie node
     * @param c character
     * @param trieNode TrieNode
     * @return TrieNode at which character was inserted
     */
    private TrieNode insertTrie(Character c, TrieNode trieNode){
        if (trieNode.children.containsKey(c)){
            return trieNode.children.get(c);
        }
        TrieNode newTrieNode = new TrieNode(c);
        trieNode.children.put(c, newTrieNode);
        return newTrieNode;
    }

    class TrieNode {
        Character c;
        int val;
        HashMap<Character, TrieNode> children;
        boolean pattern;//to check if path already formed at node during pattern match

        TrieNode(Character c){
            this.c = c;
            children = new HashMap<Character, TrieNode>();
            val = counter++;
        }

        public boolean equals( Object obj )
        {
            if (this == obj){
                return true;
            }
            if (this.getClass() != obj.getClass()){
                return false;
            }
            boolean flag = false;
            TrieNode trieNode = (TrieNode)obj;
            if( trieNode.c.equals(c))
                flag = true;
            return flag;
        }
    }
}