package BOJ_11286;

import java.io.*;

public class Main {
	
	static int[] heap = new int[100001]; // 최소 절댓값 루트
	static int size = 0;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		for (int i = 0; i < num; i++) {
			int command = Integer.parseInt(br.readLine());
			if (command == 0) bw.write(T.erase() + "\n");
			else T.insert(command);
		}
		
		br.close();
		bw.close();
	}
	
	public void insert(int num) {
		heap[++size] = num; // 사이즈 키우고 맨 끝에 넣음
		int parent = size / 2;
		int child = size;
		// 힙이 될 때까지 parent랑 교체
		while (parent > 0 && !isHeap(parent, child)) {
			int temp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = temp;
			child = parent;
			parent /= 2;
		}
	}
	
	public int erase() {
		if (size == 0) return 0;
		int min = heap[1];
		heap[1] = heap[size--]; // 가장 끝의 숫자 루트에 넣고 사이즈 줄임
		int parent = 1;
		int leftChild = 2;
		int rightChild = 3;
		// 힙이 될 때까지 child랑 교체
		while (leftChild <= size || rightChild <= size) {
			if (isHeap(parent, leftChild) && isHeap(parent, rightChild)) break;
			if (isHeap(leftChild, rightChild)) { // 왼쪽 자식이 부모가 될 때 힙 가능
				int temp = heap[parent];
				heap[parent] = heap[leftChild];
				heap[leftChild] = temp;
				parent = leftChild;
			} else {
				int temp = heap[parent];
				heap[parent] = heap[rightChild];
				heap[rightChild] = temp;
				parent = rightChild;
			}
			leftChild = parent * 2;
			rightChild = parent * 2 + 1;
		}
		return min;
	}
	
	public boolean isHeap(int parent, int child) {
		// 부모의 절댓값이 작고 절댓값이 같을 경우 부모의 값이 더 작다
		if (Math.abs(heap[parent]) < Math.abs(heap[child])
				    || (Math.abs(heap[parent]) == Math.abs(heap[child]) && heap[parent] < heap[child])) return true;
		else return false;
	}
}
