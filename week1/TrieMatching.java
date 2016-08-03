package week1;
/**
 * ExtendedTrieMatching class - uses Trie to store patterns and matches the Text with the Trie data structure
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 August 2nd, 2016
 */

class TrieMatching{

	private Trie trie;

	TrieMatching(String text, String[] patterns){
		trie = new Trie(patterns);
		triMatching(text);
	}

	/**
	 * Perform preFixTriMatching
	 * @param text
	 * @param counter index of text in case of match
     */
	private void prefixTriMatching(String text, int counter){
		int i = 0;
		Trie.TrieNode trieNode = trie.root;
		while (true){
			if (trieNode.children.size() == 0) { //check for leaf
				//pattern matched
				System.out.print(counter + " ");
				return;
			}else {
				if (i >= text.length()){
					//exceeded text but non leaf node in trie
					//no match
					return;
				}
				char symbol = text.charAt(i);
				if(trieNode.children.containsKey(symbol)) {
					//next trieNode in tree with symbol matched
					trieNode = trieNode.children.get(symbol);
					//next symbol in text
					i++;

				}else{
					//symbol mismatch
					return;
				}
			}
		}
	}

	/**
	 * Perfrom triMatching on text, iteratively calls prefixTriMatching
	 * @param text
     */
	private void triMatching(String text){
		int counter = 0;
		while (text.length() != 0) {
			prefixTriMatching(text, counter++);
			// remove first char from string
			text = text.substring(1);
		}

	}

}