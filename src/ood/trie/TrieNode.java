package ood.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	Map<Character, TrieNode> children;
	boolean isWord;
	int count;
	
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}
}
