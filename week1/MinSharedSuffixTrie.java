package week1;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * MinSharedSuffixTrie class - implement the minShared subString between two strings
 * DID NOT PASS GRADER
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 August 7nd, 2016
 */

class MinSharedSuffixTrie {
	private String text;
	SuffixTrieNode root;
	private static int A = 0;
	private static int C = 1;
	private static int T = 2;
	private static int G = 3;
	private static int X = 4; //ending of 2nd text
	private static int Y = 5; //end of 1st text
	HashMap<Character, Integer> characterIntegerHashMap;
	String minShared ;

	MinSharedSuffixTrie(String text){
		this.text = text;
		characterIntegerHashMap = new HashMap<>();
		popCharIntegerHashMap();
		root = new SuffixTrieNode(-1, -1);
		buildSuffixTrie();
		updateNodeTypes();
		minShared = this.text;
		pathsSuffixTree();

	}


	/**
	 * Populate character Integer hash map
	 */
	private void popCharIntegerHashMap(){
		characterIntegerHashMap.put('A', A);
		characterIntegerHashMap.put('C', C);
		characterIntegerHashMap.put('T', T);
		characterIntegerHashMap.put('G', G);
		characterIntegerHashMap.put('X', X);
		characterIntegerHashMap.put('Y', Y);
	}

	private void buildSuffixTrie(){
		for (int i=0; i < text.length(); i++){
			String suffix = text.substring(i);
			insertSuffix(suffix, i);
		}
	}

	private void insertSuffix(String suffix, int startIndex){
		SuffixTrieNode suffixTrieNode = root;
		for (int i=0; i < suffix.length(); i++){
			Character c = suffix.charAt(i);
			suffixTrieNode = insertCharacter(c, suffixTrieNode, startIndex);
			startIndex++;
		}
		root.singleChild = null;
	}

	private SuffixTrieNode insertCharacter(Character c, SuffixTrieNode suffixTrieNode, int startIndex){
		int index = characterIntegerHashMap.get(c);
		if (suffixTrieNode.nodes[index] == null){
			SuffixTrieNode newNode = new SuffixTrieNode(startIndex, 1);
			if (text.charAt(startIndex) == 'X'){
				newNode.nodeType = "lNode";
			}
			suffixTrieNode.nodes[index] = newNode;
			suffixTrieNode.singleChild = newNode;
			suffixTrieNode.childrenSize +=1;
			if (suffixTrieNode.childrenSize != 1){
				suffixTrieNode.singleChild = null;
			}
			return newNode;
		} else {
			SuffixTrieNode node = suffixTrieNode.nodes[index];
			return node;
		}
	}

	/**
	 * print out contents of suffixTree
	 */
	void updateNodeTypes(){
		updateNodeTypes(root);
	}

	/**
	 * Recursive helper to update nodetypes
	 * @param suffixTrieNode SuffixTrieNode
	 */
	private void updateNodeTypes(SuffixTrieNode suffixTrieNode){
		int lLnodeCount = 0;
		for (SuffixTrieNode node: suffixTrieNode.nodes){
			if (node != null){
				updateNodeTypes(node);
				if (node.nodeType.equals("lNode")){
					lLnodeCount ++;
				}
			}
		}
		int startIndex = suffixTrieNode.startIndex;
		int length = startIndex + suffixTrieNode.length;
		if (startIndex == -1){
			suffixTrieNode.nodeType = "Root";
			return;
		}
		String value = text.substring(startIndex, length);

		if (value.equals("Y")){ //incase of leaves
			suffixTrieNode.nodeType = "rNode";
		}
		else if (value.equals("X")){ //incase of leaves
			suffixTrieNode.nodeType = "lNode";
		}
		else if (lLnodeCount == suffixTrieNode.childrenSize && suffixTrieNode.childrenSize != 0){ //leaves have alredy been set during flattening step
			suffixTrieNode.nodeType = "lNode";
		}else{
			suffixTrieNode.nodeType = "rNode";
		}
	}


	/**
	 * paths of suffixTree
	 */
	void pathsSuffixTree(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("Root");
		pathsSuffixTree(root, result);
	}

	String createString(ArrayList<String> rs){
		StringBuffer result = new StringBuffer();
		for (String rsString: rs){
			if (!rsString.equals("Root"))
				//result += rsString;
				result.append(rsString);
		}
		return  result.toString();
	}

	/**
	 * Recursive helper to print out trie contents
	 * @param suffixTrieNode SuffixTrieNode
	 */
	private void pathsSuffixTree(SuffixTrieNode suffixTrieNode, ArrayList<String> result){
		for (SuffixTrieNode node: suffixTrieNode.nodes){
			if (node != null ) {
				String val = text.substring(node.startIndex, node.startIndex + node.length);
				if (val.equals("Y") || val.equals("X")){
					//ignore
					break;
				}
				else if (node.nodeType.equals("rNode") ) {
					//node shared between left and right texts, so include it part of result and recurse
					result.add(val);
					pathsSuffixTree(node, result);
				} else if (node.nodeType.equals("lNode") && !val.equals("X")) {
					//first lNode not equal to X
					result.add(val);
					String result_String = createString(result);
					if (result_String.length() != 0 && result_String.length() < minShared.length()) {
						minShared = result_String;
					}
					result.remove(result.size()-1);
				}

			}
		}
		if (suffixTrieNode.nodeType.equals("rNode")){
			result.remove(result.size()-1); //back tracking move
		}
		if (suffixTrieNode.startIndex == -1){
			result.remove(result.size()-1); //root node
		}
	}

	class SuffixTrieNode {
		SuffixTrieNode singleChild = null;//used while constructing suffix trie else set to null
		String nodeType = "undefined";
		int startIndex = -1;
		int length = -1;
		SuffixTrieNode[] nodes = new SuffixTrieNode[6];
		int childrenSize = 0;

		SuffixTrieNode(int startIndex, int length){
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