package ex1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* Ex1_Map */
public class Ex7_Map {
// Map은 key,value로 저장하는 구조
    public static void main(String[] args) {
        // Map에 저장할 배열값
        String[] msg = {"AA","BB","CC","AA","DD","EE","FF"};
        HashMap<Integer,String> map = new HashMap<>();
        int cnt = 0; // 배열의 시작 index값
        // 배열의 갯수만큼 Map에 저장 
        for(String e : msg){
            map.put(cnt+1, e); // cnt + 1: Map의 키값이 1부터!
            cnt++;
        }
        System.out.println("Map:"+map.size());

        // 출력!
        // 키값을 가진 객체의 주소를 반환한다. => Set으로 추출
        Set<Integer> keys = map.keySet();
        for(Integer e : keys){
            System.out.println(map.get(e));
        }
        // 축약형 
        // Map.Entry<Integer,String>  : key와 value를 받아오기 위한
        // 인터페이스 , 자료형 => e key,value 저장하는 객체 주소
        // entrySet(): key,value를 추출한 후 Map.Entry<Integer,String>
        // 저장해서 반환한다.
        for(Map.Entry<Integer,String> e : map.entrySet()){
            System.out.println("Key:"+e.getKey());
            System.out.println("Valye:"+e.getValue());
        }
    }
}
