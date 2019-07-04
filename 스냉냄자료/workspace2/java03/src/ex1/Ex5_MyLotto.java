package ex1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/* Ex5_MyLotto */
// Scanner 로 입력을 받아서 처리 
// Math.random() 
// 6개인지 아닌지를 검사..
public class Ex5_MyLotto implements Ex5_LottoInter{
    //  로또금액만큼 랜덤한수를 저장한 TreeSet을 저장할 ArrayList선언
    private ArrayList<TreeSet<Integer>> tlist;
    public Ex5_MyLotto() {     
       // 생성
       tlist = new ArrayList<>();
    }
    @Override
    public String getLottoNum() {
        StringBuffer sb = new StringBuffer();
        int i=0;
        for(TreeSet<Integer> e : tlist){ // 로또의 값을 출력
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
        // 금액에 따른 게임 횟수를 연산
        num = num /1000;
       for(int i=0; i<num; i++){
           // TreeSet을 주소를 반환 받아서 ArrayList에 저장
           tlist.add(execute());
       }
    }
   
    public static void main(String[] args) {
        Ex5_MyLotto ref = new Ex5_MyLotto();
        // 프로그램을 실행하면 랜덤한 숫자 1 ~ 45까지
        // 6개를 중복없이 순서대로 출력하시오.
        Scanner sc = new Scanner(System.in);
        System.out.print("금액 입력 :");
        int money = Integer.parseInt(sc.nextLine());
        ref.setLottoGameNum(money);
        System.out.println("---------금주의 로또 예상 번호 --------");
        System.out.println(ref.getLottoNum());
    }
}
