package ex1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/* ExamLinkedArrayList */
public class ExamLinkedArrayList {
    public void arrayList(ArrayList<String> ar) {
        long start = System.currentTimeMillis();
        for (String e : ar) {
            String msg = e;
            if (e.equals("Kosta")) {
                //System.out.println("true");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("소요시간 :" + (end - start));
    }
    public void linkedList(LinkedList<String> ar) {
        long start = System.currentTimeMillis();
        for (String e : ar) {
            String msg = e;
            if (e.equals("Kosta")) {
                //System.out.println("true");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("소요시간 :" + (end - start));
    }

    public static void main(String[] args) {
        ExamLinkedArrayList ref = new ExamLinkedArrayList();
        ArrayList<String> ar = new ArrayList<>();
        LinkedList<String> ar1 = new LinkedList<>();
        for (int i = 0; i <= 10000000; i++) {
            if(i % 5 == 0){
                ar.add("Kosta");
                ar1.add("Kosta");
            }else{
                ar.add("Test1"); 
                ar1.add("Test1"); 
            }
        }
        //ref.arrayList(ar);
        //ref.linkedList(ar1);
        ref.allList(ar);
    }
    
    
    
    
    
        //메서드의 인자를 다형성으로 중복정의를 피할 수 있다. 
   public void allList(List<String> ar) {
        long start = System.currentTimeMillis();
        for (String e : ar) {
            String msg = e;
            if (e.equals("Kosta")) {
                //System.out.println("true");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("소요시간 :" + (end - start));
    }
}
