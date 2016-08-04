package week1;
import java.util.HashMap;
/**
 * SuffixTree class - implement the SuffixTree data structure to store suffixes of the text
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 4th, 2016
 */
class SuffixTree {
    private String text;
    private SuffixTreeNode root;
    private static int A = 0;
    private static int C = 1;
    private static int T = 2;
    private static int G = 3;
    private static int $ = 4;
    HashMap<Character, Integer> characterIntegerHashMap;

    SuffixTree(String text){
        this.text = text;
        characterIntegerHashMap = new HashMap<>();
        popCharIntegerHashMap();
        root = new SuffixTreeNode(-1, -1);
        buildSuffixTree();
        flattenSuffixTree();

    }

    /**
     * Populate character Integer hash map
     */
    private void popCharIntegerHashMap(){
        characterIntegerHashMap.put('A', A);
        characterIntegerHashMap.put('C', C);
        characterIntegerHashMap.put('T', T);
        characterIntegerHashMap.put('G', G);
        characterIntegerHashMap.put('$', $);
    }

    /**
     * Build suffix Tree
     */
    private void buildSuffixTree(){
        for (int i=0; i < text.length(); i++){
            String suffix = text.substring(i);
            insertSuffix(suffix, i);
        }
    }

    /**
     * Insert suffix into suffix Tree
     * @param suffix string
     * @param startIndex of text
     */
    private void insertSuffix(String suffix, int startIndex){
        SuffixTreeNode suffixTreeNode = root;
        SuffixTreeNode prev = null;
        int length = -1;
        for (int i=0; i < suffix.length(); i++){
            length = text.length() - startIndex;
            Character c = suffix.charAt(i);
            prev = suffixTreeNode;
            suffixTreeNode = insertCharacter(c, startIndex, length, suffixTreeNode);
            if (suffixTreeNode == prev){
                return;
            }
            startIndex++;
        }
    }

    /**
     * Insert character of suffix into suffix tree node
     * @param c character of suffix
     * @param startIndex of text
     * @param length of suffix
     * @param suffixTreeNode
     * @return
     */
    private SuffixTreeNode insertCharacter(Character c, int startIndex, int length, SuffixTreeNode suffixTreeNode){
        int index = characterIntegerHashMap.get(c);
        if (suffixTreeNode.nodes[index] == null){
            SuffixTreeNode newNode = new SuffixTreeNode(startIndex, length);
            suffixTreeNode.nodes[index] = newNode;
            suffixTreeNode.childrenSize += 1;
            return suffixTreeNode;
        } else {
            SuffixTreeNode node = suffixTreeNode.nodes[index];
            if (node.length == 1){
                return node;
            }
            //split children node
            SuffixTreeNode newNode = new SuffixTreeNode(node.startIndex+1, node.length-1);
            node.length = 1;
            c = text.charAt(node.startIndex+1);
            index = characterIntegerHashMap.get(c);
            node.nodes[index] = newNode;
            node.childrenSize +=1;
            return node;
        }
    }

    /**
     * Merge suffix tree nodes
     * @param suffixTreeNode node of suffixTree to be merged
     */
    void mergeSuffixNode(SuffixTreeNode suffixTreeNode){
        while (suffixTreeNode.childrenSize == 1){
            SuffixTreeNode childNode = getNotNullChildNode(suffixTreeNode);
            //merge step
            suffixTreeNode.length += childNode.length;
            suffixTreeNode.childrenSize = childNode.childrenSize;
            suffixTreeNode.nodes = childNode.nodes;
        }
    }

    /**
     * get not null child node for suffix nodes with 1 child
     * @param suffixTreeNode
     * @return
     */
    private SuffixTreeNode getNotNullChildNode(SuffixTreeNode suffixTreeNode){
        SuffixTreeNode childNode = null;
        for (SuffixTreeNode n : suffixTreeNode.nodes){
            if (n != null){
                childNode = n;
                break;
            }

        }
        return childNode;
    }

    /**
     * flatten out contents of suffixTree
     */
    void flattenSuffixTree(){
        flattenSuffixTree(root);
    }

    /**
     * Recursive helper to flatten out tree contents
     * @param suffixTreeNode SuffixTreeNode
     */
    private void flattenSuffixTree(SuffixTreeNode suffixTreeNode){
        if (suffixTreeNode == null){
            return;
        }
        for (SuffixTreeNode node: suffixTreeNode.nodes){
            if (node != null){
                mergeSuffixNode(node);
                flattenSuffixTree(node);
            }
        }
    }

    /**
     * print out contents of suffixTree
     */
    void printoutSuffixTree(){
        printoutSuffixTree(root);
    }

    /**
     * Recursive helper to print out trie contents
     * @param suffixTreeNode SuffixTreeNode
     */
    private void printoutSuffixTree(SuffixTreeNode suffixTreeNode){
        if (suffixTreeNode == null){
            return;
        }
        for (SuffixTreeNode node: suffixTreeNode.nodes){
            if (node != null){
               String val = text.substring(node.startIndex, node.startIndex+node.length);
               System.out.println(val) ;
               printoutSuffixTree(node);
            }
        }
    }

    class SuffixTreeNode {
        int startIndex = -1;
        int length = -1;
        SuffixTreeNode[] nodes = new SuffixTreeNode[5];
        int childrenSize = 0;

        SuffixTreeNode(int startIndex, int length){
            this.startIndex = startIndex;
            this.length = length;
        }

        public String toString(){
            if (startIndex == -1 || length == -1){
                return "Root";
            }
            return text.substring(startIndex, startIndex+length);
        }
    }

}