/*
A trie is the kind of data structure, where the actual searching key is associated with the "path from the root"
to the node, instead of associated with the nodes directly (like binary tree)

The edges are associated with distinct characters, meaning "what is the possible next char"

The key is string or sequence typed
 */

package ood.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
		// represents a empty word in the root
		root.isWord = true;
	}
	
	public boolean search(String word) {
		if (root == null) return false;
		if (word == null) return false;
		
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = cur.children.get(word.charAt(i));
			if (next == null) {
				return false;
			}
			cur = next;
		}
		
		return cur.isWord;
	}
	
	public boolean insert(String word) {
		if (search(word)) {
			return false;
		}
		if (word == null) {
			return false;
		}
		
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = cur.children.get(word.charAt(i));
			if (next == null) {
				next = new TrieNode();
				cur.children.put(word.charAt(i), next);
			}
			cur = next;
			cur.count++;
		}
		// set isWord
		cur.isWord = true;
		return true;
	}
	
	// method 1: add an extra field in TrieNode
	public boolean delete(String word) {
		if (!search(word)) {
			return false;
		}
		
		// word must exist in the Trie
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode next = cur.children.get(word.charAt(i));
			if (next.count == 1) {
				cur.children.remove(word.charAt(i));
				return true;
			}
			next.count--;
			cur = next;
		}
		cur.isWord = false;
		return true;
	}
	
	// method 2: DFS
	// not efficient enough
	public boolean delete2(String word) {
		if (root == null) return false;
		if (word == null) return false;
		
		return delHelper(root, 0, word);
	}
	
	private boolean delHelper(TrieNode root, int index, String word) {
		// base case 1:
		if (root == null) return false;
		
		// base case 2: "word" exists in the Trie
		if (index == word.length()) {
			root.isWord = false;
			return true;
		}
		
		// base case 3: "word" does not exist in the Trie
		if (!root.children.containsKey(word.charAt(index))) {
			return false;
		}
		
		return delHelper(root.children.get(word.charAt(index)), index + 1, word);
	}
	
	public List<String> findAllWPrefix(String prefix) {
		List<String> result = new ArrayList<>();
		if (prefix == null) return result;
		if (root == null) return result;
		
		// step 1: search for the existence of the prefix
		TrieNode matchNode = null;
		TrieNode cur = root;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < prefix.length(); i++) {
			matchNode = cur.children.get(prefix.charAt(i));
			// add the prefix to the StringBuilder
			builder.append(prefix.charAt(i));
			if (matchNode == null) {
				// the prefix does not exist
				return result;
			}
		}
		
		// step 2: find all strings with common prefix using DFS
		findAllByDFS(cur, builder, result);
		
		return result;
	}
	
	private void findAllByDFS(TrieNode cur, StringBuilder builder, List<String> result) {
		if (cur.isWord == true) {
			// add a string to the list
			// not a base case!
			result.add(builder.toString());
		}
		
		// base case is implicit
		// no null TrieNode shoulde be exercised
		
		for (Map.Entry<Character, TrieNode> entry : cur.children.entrySet()) {
			builder.append(entry.getKey());
			findAllByDFS(entry.getValue(), builder, result);
			builder.deleteCharAt(builder.length() - 1);
		}
	}
	
	public List<String> findAllMatchWildCard(String target) {
		List<String> result = new ArrayList<>();
		if (target == null || target.length() == 0) return result;
		
		StringBuilder builder = new StringBuilder();
		findAllWildCardByDFS(root, 0, target, builder, result);
		return result;
	}
	
	private void findAllWildCardByDFS(TrieNode cur, int index, String target, StringBuilder builder, List<String> result) {
		// base case 1
		if (cur == null) {
			return;
		}
		
		// base case 2
		if (index == target.length()) {
			if (cur.isWord == true) {
				result.add(builder.toString());
			}
			return;
		}
		
		// char to match
		char ch = target.charAt(index);
		if (ch == '?') {
			// traverse all chars in the map if ch == '?'
			for (Map.Entry<Character, TrieNode> entry : cur.children.entrySet()) {
				builder.append(entry.getKey());
				findAllWildCardByDFS(entry.getValue(), index + 1, target, builder, result);
				builder.deleteCharAt(builder.length() - 1);
			}
		} else {
			TrieNode temp = cur.children.get(ch);
			if (temp != null) {
				builder.append(ch);
				findAllWildCardByDFS(temp, index + 1, target, builder, result);
				builder.deleteCharAt(builder.length() - 1);
			}
		}
	}
}
