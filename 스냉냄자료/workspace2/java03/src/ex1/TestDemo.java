package ex1;
/* TestDemo */
public class TestDemo {
    public static void main(String[] args) {
            String path ="java.lang.Math";
            String path1 ="javax.lang.Math";
            // substring:Ư�� ���ڿ��� �߶󳻱� ���� �޼���
            // lastIndexOf, indexOf : ã�� ���ڿ��� ��ġ
            System.out.println("substring :"+
                    path1.substring(path1.lastIndexOf(".")+1));
            System.out.println("substring :"+
                    path.substring(0,4));
    }
}
