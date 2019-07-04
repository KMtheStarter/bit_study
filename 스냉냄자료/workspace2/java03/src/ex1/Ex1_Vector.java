package ex1;
import java.util.Iterator;
import java.util.Vector;
/* Ex1_Vector */
public class Ex1_Vector {
    private Vector v; // 벡터를 선언
    public Ex1_Vector() {
        // 생성 
        v = new Vector();
        System.out.println("초기 용량 :" + v.capacity());
        System.out.println("초기 사이즈 :" + v.size());
        // add를 호출해서 Vector에 저장
        v.add("Test1");
        v.add("Test2");
        v.add("Test1"); // 힙영역에 생성된 객체는 몇개인가? 
        System.out.println("초기 용량 :" + v.capacity());
        System.out.println("초기 사이즈 :" + v.size());
        // 추천하지 않음
        for(int i=0; i<v.size(); i++){
            System.out.println(v.get(i));
        }
        System.out.println("-----------------------");
        // 향상된 for문으로 출력이 가능하다.(권장)
        for(Object e : v){
            System.out.println(e);
        }
        System.out.println("-----------------------");
        // 반복자 (추천)
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
