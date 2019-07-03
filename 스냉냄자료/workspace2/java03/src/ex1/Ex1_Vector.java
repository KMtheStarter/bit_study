package ex1;
import java.util.Iterator;
import java.util.Vector;
/* Ex1_Vector */
public class Ex1_Vector {
    private Vector v; // ���͸� ����
    public Ex1_Vector() {
        // ���� 
        v = new Vector();
        System.out.println("�ʱ� �뷮 :" + v.capacity());
        System.out.println("�ʱ� ������ :" + v.size());
        // add�� ȣ���ؼ� Vector�� ����
        v.add("Test1");
        v.add("Test2");
        v.add("Test1"); // �������� ������ ��ü�� ��ΰ�? 
        System.out.println("�ʱ� �뷮 :" + v.capacity());
        System.out.println("�ʱ� ������ :" + v.size());
        // ��õ���� ����
        for(int i=0; i<v.size(); i++){
            System.out.println(v.get(i));
        }
        System.out.println("-----------------------");
        // ���� for������ ����� �����ϴ�.(����)
        for(Object e : v){
            System.out.println(e);
        }
        System.out.println("-----------------------");
        // �ݺ��� (��õ)
        Iterator it = v.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            System.out.println(o);
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        new Ex1_Vector();
    }
}
