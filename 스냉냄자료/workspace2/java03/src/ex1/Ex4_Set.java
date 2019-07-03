package ex1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/* Ex4_Set */
public class Ex4_Set {
    public static void main(String[] args) {
        //Set인터페이스 -  정렬이 보장되지 않는 중복값을 방지하는 컬렉션
        // 중복을 제거하면서 반복자, 향상된 for문 등 지원
        //HashSet<String> h1 = new HashSet<>();
        //TreeSet<String> h1 = new TreeSet<>(); // 오름차순 
        LinkedHashSet<String> h1 = new LinkedHashSet<>(); // 입력순
        h1.add("ZTest");
        h1.add("BTest");
        h1.add("ATest"); 
        h1.add("CTest");
        h1.add("BTest");
        System.out.println("크기 :"+h1.size());
        
        
        
        
        
        
        
        for(String e : h1){
            System.out.println(e);
        }
        System.out.println("-----------------------------");
        Iterator<String> it = h1.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println(next);
        }
    }
}
