/*
 * My implementation of Hash Map
 */

package hashMap;

import java.util.Arrays;

public class MyHashMap<K, V> {
	public static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private static final int INITIAL_CAPACITY = 17;
	private static final double LOAD_FACTOR = 0.7;
	private int size;
	private Node<K, V>[] array;
	
	public MyHashMap() {
		this.array = (Node<K, V>[]) (new Node[INITIAL_CAPACITY]);
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}
	
	/*
	 * the previous value associated with key, or null if there was no mapping for key. 
	 * A null return can also indicate that the map previously associated null with key.
	 */
	public V put(K key, V value) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				V returnValue = node.getValue();
				node.setValue(value);
				return returnValue;
			}
			node = node.next;
		}
		// if no key found, insert the <K, V> pair in the head of the linked list
		Node<K, V> newEntry = new Node<K, V>(key, value);
		newEntry.next = array[index];
		array[index] = newEntry;
		size++;
		// if hit load factor, rehash
		reHash();
		return null;
	}
	
	/*
	 * Returns the value to which the specified key is mapped, 
	 * or null if this map contains no mapping for the key.
	 */
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return node.getValue();
			}
			node = node.next;
		}
		return null;
	}
	
	/*
	 * the previous value associated with key, or null if there was no mapping for key.
	 * A null return can also indicate that the map previously associated null with key.
	 */
	public V remove(K key) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		Node<K, V> prev = null;
		while (cur != null) {
			if (equalsKey(cur.getKey(), key)) {
				// delete linked list head
				if (prev == null) {
					array[index] = cur.next;
				} else {
					prev.next = cur.next;
				}
				size--;
				return cur.getValue();
			}
			prev = cur;
			cur = cur.next;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(cur.getKey(), key)) {
				return true;
			}
			// update node
			cur = cur.next;
		}
		return false;
	}
	
	public boolean containsValue(V value) {
		if (isEmpty()) return false;
		for (Node<K, V> node : array) {
			while (node != null) {
				if (equalsValue(node.getValue(), value)) {
					return true;
				}
				// update node
				node = node.next;
			}
		}
		return false;
 	}
	
	private int getIndex(K key) {
		if (key == null) return 0;
		// make sure the return value is always non-negative
		return (key.hashCode() & 0x7fffffff) % array.length;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff);
	}
	
	private boolean equalsKey(K key1, K key2) {
		// corner case: key1 == null
		return key1 == key2 || key1 != null && key1.equals(key2);
	}
	
	private boolean equalsValue(V value1, V value2) {
		// corner case: value1 == null
		return value1 == value2 || value1 != null && value1.equals(value2);
	}
	
	private void reHash() {
		if (size > LOAD_FACTOR * array.length) {
			Node<K, V>[] newArray = (Node<K, V>[])(new Node[array.length * 2]);
			for (Node<K, V> cur : array) {
				while (cur != null) {
					Node<K, V> node = cur;
					cur = cur.next;
					int newIndex = hash(node.getKey()) % newArray.length;
					node.next = newArray[newIndex];
					newArray[newIndex] = node;
				}
			}
			
			array = newArray;
		}
	}
	
	public static void main(String[] args) {
		MyHashMap<String, Integer> map = new MyHashMap<>();
		System.out.println("1  " + map.size());
		
		map.put("A", 1);
		System.out.println("2.1  " + map.size());
		System.out.println("2.2  " + map.containsKey("A"));
		System.out.println("2.3  " + map.containsKey("B"));
		System.out.println("2.4  " + map.get("A"));
		System.out.println("2.5  " + map.get("B"));
		
		map.put("B", 2);
		System.out.println("3.1  " + map.size());
		System.out.println("3.2  " + map.containsKey("A"));
		System.out.println("3.3  " + map.containsKey("B"));
		System.out.println("3.4  " + map.get("A"));
		System.out.println("3.5  " + map.get("B"));
		
		map.remove("A");
		System.out.println("4.1  " + map.size());
		System.out.println("4.2  " + map.containsKey("A"));
		System.out.println("4.3  " + map.containsKey("B"));
		System.out.println("4.4  " + map.get("A"));
		System.out.println("4.5  " + map.get("B"));
		
		map.put("B", 3);
		System.out.println("5.1  " + map.size());
		System.out.println("5.2  " + map.containsKey("A"));
		System.out.println("5.3  " + map.containsKey("B"));
		System.out.println("5.4  " + map.get("A"));
		System.out.println("5.5  " + map.get("B"));
		
		map.put("C", 4);
		map.put("D", 4);
		map.put("E", 4);
		map.put("F", 4);
		map.put("G", 4);
		map.put("H", 4);
		map.put("I", 4);
		System.out.println("6.1  " + map.size());
		System.out.println("6.2  " + map.containsKey("A"));
		System.out.println("6.3  " + map.containsKey("B"));
		System.out.println("6.4  " + map.get("A"));
		System.out.println("6.5  " + map.get("B"));
		
	}
}
