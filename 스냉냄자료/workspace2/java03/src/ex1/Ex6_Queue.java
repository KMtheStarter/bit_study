package ex1;

import java.util.LinkedList;
import java.util.Queue;

public class Ex6_Queue {
//Java의 Queue 인터페이스는 자료구조 큐를 제공한다.
// 자료구조 큐는 FIFO(First In First Out, 선입선출) 형태로 자료를 보관하고 꺼내는 버퍼
// Java의 Queue 인터페이스에서는 보관할 때 offer 메서드를 사용
// 가장 먼저 보관한 자료를 꺼낼 때는 poll 메서드를 사용 
// 가장 먼저 보관한 자료를 단순 참조하는 peek 
// 메서드와 비었는지 판별하는 empty 메서드를 제공하고 있습니다.
	public static void main(String[] args) {
		Queue<String > q = new LinkedList<>() ;
		q.offer("A") ; //순차보관
		q.offer("B") ;
		q.offer("C") ;
		System.out.println("size:"+q.size()); // 3
		System.out.println(q.poll());   //가장 먼저 보관한 값 꺼내고 반환
                System.out.println("size:"+q.size());  // 2
                System.out.println("==================");
                System.out.println(q.peek());//가장 먼저 보관한 값 단순 참조, 꺼내지 않음
                System.out.println("size:"+q.size());
                System.out.println("==================");
		System.out.println(q.poll());
		q.add("D") ;
		System.out.println(q.poll());
		System.out.println(q.poll());
               Queue<String > q1 = new LinkedList<>() ;
		q1.offer("이순일") ; //순차보관
		q1.offer("박차홍") ;
		q1.offer("제임스킴") ;
                while(q1.isEmpty()==false){
			System.out.println(q1.poll());	
		}		
		
	}

}
