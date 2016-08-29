package week4;
import java.util.HashMap;
/**
 * SuffixTree class -  faster implement the SuffixTree data structure using the suffix array to store suffixes of the text
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 28th, 2016
 */

class SuffixTree {
    public int counter = 0;
    String text = null;
    SuffixTreeNode root;
    private static HashMap<Character, Integer> charIntMap = getCharIntMap();

    /**
     * Populate character Integer hash map
     */
    private static HashMap<Character, Integer> getCharIntMap() {
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();
        characterIntegerHashMap.put('$', 0);
        characterIntegerHashMap.put('A', 1);
        characterIntegerHashMap.put('C', 2);
        characterIntegerHashMap.put('G', 3);
        characterIntegerHashMap.put('T', 4);
        return characterIntegerHashMap;
    }

    private class SuffixTreeNode {
        SuffixTreeNode parent; //pointer to parent
        SuffixTreeNode[] children;
        int stringDepth = 0;
        int start = -1;
        int end = -1;

        SuffixTreeNode(int start, int end, int stringDepth) {
            this.start = start;
            this.end = end;
            this.stringDepth = stringDepth;
            children = new SuffixTreeNode[5];
        }

        public String toString() {
            if (start == -1 || end == -1) {
                return "UNDEFINED";
            }
            return text.substring(start, end + 1);
        }
    }

    SuffixTree(String text, int[] order, int[] lcp) {
        this.text = text;
        root = run(text, order, lcp);
        printNodes();

    }

    SuffixTreeNode run(String text, int[] order, int[] lcp) {
        root = new SuffixTreeNode(-1, -1, 0);
        int lcpPrev = 0;
        SuffixTreeNode currentNode = root;
        for (int i = 0; i < text.length(); i++) {
            //String suffix = text.substring(order[i]);
            int suffixPos = order[i];
            while (currentNode.stringDepth > lcpPrev) {
                currentNode = currentNode.parent;
            }
            if (currentNode.stringDepth == lcpPrev){
                currentNode = createLeafNode(currentNode, text, suffixPos);
            } else{
                int start =  order[i-1] +  currentNode.stringDepth;
                int offset =  lcpPrev - currentNode.stringDepth;
                SuffixTreeNode midNode = splitNode(currentNode,text, start, offset);
                currentNode = createLeafNode(midNode, text, suffixPos);
            }
            if (i < text.length() - 1) {
                lcpPrev = lcp[i];
            }
        }
        return root;

    }

    private SuffixTreeNode createLeafNode(SuffixTreeNode node, String text, int suffixPos) {
        int start = suffixPos + node.stringDepth;
        int end = text.length() - 1;
        int stringDepth = text.length() - suffixPos;
        SuffixTreeNode leaf = new SuffixTreeNode(start, end, stringDepth);
        leaf.parent = node;
        node.children[charIntMap.get(text.charAt(start))] = leaf;
        return leaf;
    }

    private SuffixTreeNode splitNode(SuffixTreeNode node, String text, int start, int offset) {
        char startChar = text.charAt(start);
        char midChar = text.charAt(start + offset);
        SuffixTreeNode midNode = new SuffixTreeNode(-1, -1, node.stringDepth + offset);
        midNode.children[charIntMap.get(midChar)] = node.children[charIntMap.get(startChar)];
        midNode.start = node.children[charIntMap.get(startChar)].start;
        midNode.end = midNode.start + offset -1;
        midNode.children[charIntMap.get(midChar)].start = midNode.start  + offset;
        node.children[charIntMap.get(startChar)].parent = midNode;
        node.children[charIntMap.get(startChar)] = midNode;
        midNode.parent = node;

        return midNode;

    }

    public void printNodes() {
        Stack<SuffixTreeNode> stack = new Stack<SuffixTreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            SuffixTreeNode currentNode = stack.pop();
            if (currentNode != root) {
                System.out.println(currentNode.start + " " + (currentNode.end+1));
                //counter += 1;
            }
            for (int i = currentNode.children.length - 1; i >= 0; i--) {
                SuffixTreeNode childNode = currentNode.children[i];
                if (childNode != null) {
                    stack.push(childNode);
                }
            }
        }

    }

}