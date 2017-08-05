/*
 * A min heap implementation
 */

package ood.heap;

public class MyHeap {
	private int[] array;
	private int size;
	private final static int INITIAL_CAPACITY = 16;
	
	public MyHeap() {
		array = new int[INITIAL_CAPACITY];
	}
	
	public MyHeap(int k) {
		array = new int[k];
	}
	
	// initialize from an external array
	public MyHeap(int[] arr) {
		if (arr == null) throw new NullPointerException();
		array = arr;
		size = arr.length;
		heapify(array);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void offer(int i) {
		resize();
		// add new element at the end of the array
		array[size] = i;
		percolateUp(array, size++);
	}
	
	private void resize() {
		if (size == array.length) {
			int[] newArray = new int[array.length * 2];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
	}
	
	public Integer poll() {
		if (isEmpty()) return null;
		int returnValue = array[0];
		swap(array, 0, --size);
		percolateDown(array, size, 0);
		return returnValue;
	}
	
	public Integer peek() {
		if (isEmpty()) return null;
		return array[0];
	}
	
	public static void heapSort(int[] array) {
		// step 1: heapify
		heapify(array);
		
		// step 2: keep moving the minimum entry to the end of the array
		// the sorted array will be in descending order
		for (int i = array.length - 1; i > 0; i--) {
			swap(array, 0, i);
			percolateDown(array, i, 0);
		}
	}
	
	public static void heapify(int[] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			percolateDown(array, array.length, i);
		}
	}
	
	private static void percolateUp(int[] array, int i) {
		int j = (i - 1) / 2;
		while (j >= 0 && array[j] > array[i]) {
			swap(array, i, j);
			i = j;
			j = (i - 1) / 2;
		}
	}
	
	private static void percolateDown(int[] array, int n, int i) {
		while (2 * i + 1 < n) {
			int j = 2 * i + 1;
			if (j + 1 < n && array[j + 1] < array[j]) j++;
			if (array[i] <= array[j]) break;
			swap(array, i, j);
			i = j;
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		// 1. test the heap sort method
		int[] testArray = new int[] {5, 2, 9, 4, 7, 3, 1, 6};
		MyHeap.heapSort(testArray);
		System.out.println(testArray.toString());
		
		// 2. test the constructor which initializes heap from array
		// 2.1 non empty array
		testArray = new int[] {5, 9, 4, 2, 8, 7};
		MyHeap heap = new MyHeap(testArray);
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
		
		// 2.2 empty array
		testArray = new int[] {};
		heap = new MyHeap(testArray);
		System.out.println(heap.poll());
		
		// 2.3 null
		/*
		testArray = null;
		heap = new MyHeap(testArray);
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
		*/
		
		// 3. test the heap by adding/deleting elements one by one
		heap = new MyHeap();
		heap.offer(5);
		heap.offer(9);
		heap.offer(2);
		heap.offer(6);
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
		System.out.println(heap.poll());
	}
 }
