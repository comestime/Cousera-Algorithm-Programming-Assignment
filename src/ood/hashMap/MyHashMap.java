package ood.hashMap;

public class MyHashMap<K, V> {
	public static class Node<K, V> {
		private final K key;
		private V value;
		private Node<K, V> next;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private Node<K, V>[] array;
	private int size;
	private static final int INITIAL_CAPACITY = 18;
	private static final double LOAD_FACTOR = 0.75;
	
	public MyHashMap() {
		array = (Node<K, V>[]) (new Node[INITIAL_CAPACITY]);
		size = 0;
	}
	
	public MyHashMap(int cap) {
		array = (Node<K, V>[]) (new Node[cap]);
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Returns the value to which the specified key is mapped, 
	 * or null if this map contains no mapping for the key.
	 */
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalKey(cur.getKey(), key)) return cur.getValue();
			// update next node
			cur = cur.next;
		}
		return null;
	}
	
	/*
	 * the previous value associated with key, or null if there was no mapping for key. 
	 * A null return can also indicate that the map previously associated null with key.
	 */
	public V put(K key, V value) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalKey(cur.getKey(), key)) {
				V oldValue = cur.getValue();
				cur.setValue(value);
				return oldValue;
			}
			// update next node
			cur = cur.next;
		}
		// if not found, insert the new node at the head of the list
		Node<K, V> node = new Node<K, V>(key, value);
		node.next = array[index];
		array[index] = node;
		size++;
		rehash();
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
			if (equalKey(cur.getKey(), key)) {
				// head of the linked list
				if (prev == null) {
					array[index] = cur.next;
				} else {
					prev.next = cur.next;	
				}
				size--;
				return cur.getValue();
			}
			// update next node
			prev = cur;
			cur = cur.next;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalKey(cur.getKey(), key)) return true;
			// update next node
			cur = cur.next;
		}
		return false;
	}
	
	public boolean containsValue(V value) {
		for (Node<K, V> node : array) {
			while (node != null) {
				if (equalValue(node.getValue(), value)) return true;
				// update next node
				node = node.next;
			}
		}
		return false;
	}
	
	private void rehash() {
		if (size < LOAD_FACTOR * array.length) return;
		Node<K, V>[] newArray = (Node<K, V>[]) (new Node[array.length * 2]);
		for (Node<K, V> node : array) {
			while (node != null) {
				Node<K, V> cur = node;
				node = node.next;
				int newIndex = hash(cur.getKey()) % newArray.length;
				cur.next = newArray[newIndex];
				newArray[newIndex] = cur;
			}
		}
		// update the instance variable
		array = newArray;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff);
	}
	
	private int getIndex(K key) {
		return hash(key) % array.length;
	}
	
	private boolean equalKey(K key1, K key2) {
		return key1 == key2 || key1 != null && key1.equals(key2);
	}
	
	private boolean equalValue(V value1, V value2) {
		return value1 == value2 || value1 != null && value1.equals(value2);
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
