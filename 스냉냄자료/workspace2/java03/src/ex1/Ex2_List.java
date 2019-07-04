package ex1;

import java.util.ArrayList;
import java.util.List;

/* Ex2_List */
public class Ex2_List {
    private List list;
    public Ex2_List() {
        list = new ArrayList();
        list.add("StringAAA");
        list.add(new Integer(30));
        list.add(new Character('A'));
        list.add(new Float(3.14f));
        list.add("String");
        System.out.println("Size:"+list.size());
        //Collection에 Object Type은 절대 추천하지 않으며 
        //JDK5부터 제네릭으로 사용한다.*****
        
        
        
        
        
        
        for(Object e : list){
            // 예 ) Integer : 30
            //  Character : A ...
           if(e instanceof String){ // 같은 인스턴스 영역인지 아닌지 판별
               System.out.println("String"+e);
           }else if(e instanceof Integer){
               System.out.println("Integer"+e);
           }//...
            String obj = e.getClass().getCanonicalName();
            System.out.print(obj.substring(obj.lastIndexOf(".")+1)+":");
            System.out.println(e);
        }
        System.out.println("--------------------");
    }
    public static void main(String[] args) {
        new Ex2_List();
    }
}
