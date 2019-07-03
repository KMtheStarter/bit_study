package ex1;
/* TestDemo */
public class TestDemo {
    public static void main(String[] args) {
            String path ="java.lang.Math";
            String path1 ="javax.lang.Math";
            // substring:특정 문자열을 잘라내기 위한 메서드
            // lastIndexOf, indexOf : 찾을 문자열의 위치
            System.out.println("substring :"+
                    path1.substring(path1.lastIndexOf(".")+1));
            System.out.println("substring :"+
                    path.substring(0,4));
    }
}
