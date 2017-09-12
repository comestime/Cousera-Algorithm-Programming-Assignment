/*
Implement a least recently used cache
It should provide set(), get() operations.
If not exists, return null (Java), false (C++).
 */

package ood.lruCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
	static class Node<K, V> {
		Node<K, V> prev;
		Node<K, V> next;
		K key;
		V value;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private final int limit;
	// a double linked list to maintain LRU mechanism
	// head is most recent used
	private Node<K, V> head;
	// tail is least recent used
	private Node<K, V> tail;
	// a hash map to maintain the relationship of key and its corresponding node in the double linked list
	private Map<K, Node<K, V>> map;
	
	public LRUCache(int limit) {
		this.limit = limit;
		this.map = new HashMap<K, Node<K, V>>();
	}
	
	public void set(K key, V value) {
		Node<K, V> node = map.get(key);
		// case 1: if key already in the cache, update the value and double linked list
		if (node != null) {
			node.value = value;
			remove(node);
		} else if (map.size() < limit) {
		// case 2: if key is not in the cache and we still have space
			node = new Node<K, V>(key, value);
		} else {
		// case 3: if key is not in the cache and no more space available
			node = tail;
			remove(tail);
			node.update(key, value);
		}
		
		append(node);
	}
	
	public V get(K key) {
		Node<K, V> node = map.get(key);
		if (node == null) {
			return null;
		}
		
		// update the double linked list to maintain LRU
		remove(node);
		append(node);
		return node.value;
	}
	
	private Node<K, V> remove(Node<K, V> node) {
		if (node == null) {
			return null;
		}
		
		map.remove(node.key);
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		if (node == head) {
			head = head.next;
		}
		if (node == tail) {
			tail = tail.prev;
		}
		node.next = node.prev = null;
		return node;
	}
	
	private Node<K, V> append(Node<K, V> node) {
		map.put(node.key, node);
		if (head == null) {
			head = tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
		
		return node;
	}
}
