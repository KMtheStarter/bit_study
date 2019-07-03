package ex1;

import java.util.TreeSet;

/* Ex5_LottoDemo */
public class Ex5_LottoDemo implements Ex5_LottoInter{
    private TreeSet<Integer> ts; // 3. ����
    public Ex5_LottoDemo() { // ������ ts�� �ش� ��ü�� ����
        ts = new TreeSet<>();
        // execute() �ʱ�ȭ ����
        execute();
    }
    private void execute(){
        while (ts.size() != 6) {   // 6���� ������ ts�� ����         
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
        //execute(); �Ź� ȣ��� �� ��밡��
    }
    // 1 ������ ���ÿ��� ȣ��
    public static void main(String[] args) {
        // 2. Ex5_LottoDemo() �⺻ ������ ȣ��
        Ex5_LottoDemo ref = new Ex5_LottoDemo();
        System.out.println("������ ��ȣ");
        System.out.println(ref.getLottoNum());
    }
}
