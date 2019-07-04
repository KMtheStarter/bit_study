package ex1;

import java.util.ArrayList;
import java.util.Iterator;

/* Ex3_ArrayList */
public class Ex3_ArrayList {
    // 문자열 객체만 담을 수 있는 ArrayList
    private ArrayList<String> list;
    private ArrayList<Integer> nList;
    public Ex3_ArrayList() {
        list = new ArrayList<>();
        list.add("Test1");
        list.add("10");
        list.add("3.14");
        nList = new ArrayList<>();
        nList.add(1000);
        nList.add(2000);
        // for 문으로 출력해보기
        
         for (String e : list) {
            System.out.println(e);
        }
        for (Integer e : nList) {
            System.out.println(e);
        }
        Iterator<String> it1 = list.iterator();
        Iterator<Integer> it2 = nList.iterator();
        while (it1.hasNext()) {
            String e = it1.next();
            System.out.println(e);
        }
        while (it2.hasNext()) {
            int e = it2.next();
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Ex3_ArrayList();
    }
}
