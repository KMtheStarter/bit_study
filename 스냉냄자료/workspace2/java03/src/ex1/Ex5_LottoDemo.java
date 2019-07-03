package ex1;

import java.util.TreeSet;

/* Ex5_LottoDemo */
public class Ex5_LottoDemo implements Ex5_LottoInter{
    private TreeSet<Integer> ts; // 3. 선언
    public Ex5_LottoDemo() { // 선언한 ts에 해당 객체를 생성
        ts = new TreeSet<>();
        // execute() 초기화 목적
        execute();
    }
    private void execute(){
        while (ts.size() != 6) {   // 6개의 난수를 ts에 저장         
            int rnum = (int) (Math.random() * 45 +1);// 1 ~ 45
            ts.add(rnum);
        }
    }
    @Override
    public String getLottoNum() {
        StringBuffer sb = new StringBuffer();
        for(Integer e : ts){
            sb.append(e).append(" ");
        }
        return sb.toString();
    }
    @Override
    public void setLottoGameNum(int num) {
        //execute(); 매번 호출될 때 사용가능
    }
    // 1 메인이 스택에서 호출
    public static void main(String[] args) {
        // 2. Ex5_LottoDemo() 기본 생성자 호출
        Ex5_LottoDemo ref = new Ex5_LottoDemo();
        System.out.println("금주의 번호");
        System.out.println(ref.getLottoNum());
    }
}
