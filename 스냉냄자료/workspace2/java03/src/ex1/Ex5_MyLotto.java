package ex1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/* Ex5_MyLotto */
// Scanner �� �Է��� �޾Ƽ� ó�� 
// Math.random() 
// 6������ �ƴ����� �˻�..
public class Ex5_MyLotto implements Ex5_LottoInter{
    //  �ζǱݾ׸�ŭ �����Ѽ��� ������ TreeSet�� ������ ArrayList����
    private ArrayList<TreeSet<Integer>> tlist;
    public Ex5_MyLotto() {     
       // ����
       tlist = new ArrayList<>();
    }
    @Override
    public String getLottoNum() {
        StringBuffer sb = new StringBuffer();
        int i=0;
        for(TreeSet<Integer> e : tlist){ // �ζ��� ���� ���
             sb.append("No[").append(i).append("]:");
            for(Integer f : e){
               sb.append(f).append(" ");
            } sb.append("\n");
            i++;
        } return sb.toString();
    }
     private TreeSet<Integer> execute(){
        TreeSet<Integer> ts = new TreeSet<>();
        while (ts.size() != 6) {            
            int rnum = (int) (Math.random()*45+1);  ts.add(rnum);
        }return ts;
    }
    @Override
    public void setLottoGameNum(int num) {
        // �ݾ׿� ���� ���� Ƚ���� ����
        num = num /1000;
       for(int i=0; i<num; i++){
           // TreeSet�� �ּҸ� ��ȯ �޾Ƽ� ArrayList�� ����
           tlist.add(execute());
       }
    }
   
    public static void main(String[] args) {
        Ex5_MyLotto ref = new Ex5_MyLotto();
        // ���α׷��� �����ϸ� ������ ���� 1 ~ 45����
        // 6���� �ߺ����� ������� ����Ͻÿ�.
        Scanner sc = new Scanner(System.in);
        System.out.print("�ݾ� �Է� :");
        int money = Integer.parseInt(sc.nextLine());
        ref.setLottoGameNum(money);
        System.out.println("---------������ �ζ� ���� ��ȣ --------");
        System.out.println(ref.getLottoNum());
    }
}
