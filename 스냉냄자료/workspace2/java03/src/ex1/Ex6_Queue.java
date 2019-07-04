package ex1;

import java.util.LinkedList;
import java.util.Queue;

public class Ex6_Queue {
//Java�� Queue �������̽��� �ڷᱸ�� ť�� �����Ѵ�.
// �ڷᱸ�� ť�� FIFO(First In First Out, ���Լ���) ���·� �ڷḦ �����ϰ� ������ ����
// Java�� Queue �������̽������� ������ �� offer �޼��带 ���
// ���� ���� ������ �ڷḦ ���� ���� poll �޼��带 ��� 
// ���� ���� ������ �ڷḦ �ܼ� �����ϴ� peek 
// �޼���� ������� �Ǻ��ϴ� empty �޼��带 �����ϰ� �ֽ��ϴ�.
	public static void main(String[] args) {
		Queue<String > q = new LinkedList<>() ;
		q.offer("A") ; //��������
		q.offer("B") ;
		q.offer("C") ;
		System.out.println("size:"+q.size()); // 3
		System.out.println(q.poll());   //���� ���� ������ �� ������ ��ȯ
                System.out.println("size:"+q.size());  // 2
                System.out.println("==================");
                System.out.println(q.peek());//���� ���� ������ �� �ܼ� ����, ������ ����
                System.out.println("size:"+q.size());
                System.out.println("==================");
		System.out.println(q.poll());
		q.add("D") ;
		System.out.println(q.poll());
		System.out.println(q.poll());
               Queue<String > q1 = new LinkedList<>() ;
		q1.offer("�̼���") ; //��������
		q1.offer("����ȫ") ;
		q1.offer("���ӽ�Ŵ") ;
                while(q1.isEmpty()==false){
			System.out.println(q1.poll());	
		}		
		
	}

}
