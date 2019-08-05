package a4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class HeapTest {
	
	public class myC implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1==o2)
				return 0;
			return o1>o2?1:-1;
		}
	}
	
	@Test
	void test() {
		//test for Constructor.
		Heap<String, Integer> testHeap= new Heap<String, Integer>(new myC());
		testHeap.add("Hello10", 10);
		testHeap.add("Hello9", 9);
		testHeap.add("Hello8", 8);
		testHeap.add("Hello7", 7);
		testHeap.add("Hello6", 6);
		testHeap.add("Hello5", 5);
		testHeap.add("Hello4", 4);
		testHeap.add("Hello3", 3);
		testHeap.changePriority("Hello7", 3);
		testHeap.changePriority("Hello4", 6);
		System.out.println(testHeap.peek());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		System.out.println(testHeap.poll());
		
	}

}
