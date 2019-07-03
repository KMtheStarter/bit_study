package ex1;

import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/* Ex5_Demo 
Scanner 를 사용하면 연결스트림이 없이도
바로 바이트스트림을 문자 스트림으로 읽어 올 수 있다.
=> nextLine()으로 읽어 내기 위함
반대로 키보드의 값을 한줄단위로 읽어 와야 한다면 ..Scanner
BufferedReader br = new BufferedReader(System.in) 
=> 뭐가 필요할까요? 
*/
public class Ex5_ScannerDemo {
    public static void main(String[] args) throws MalformedURLException {
        String path = "http://www.bitacademy.com/Course/High/Course_High_javaWeb.asp";
        // 해당 URL : 
        // http => 프로토콜,
        // host => www.bitacademy.com:8088(port)
        // path => Course/High/Course_High_javaWeb.asp
        // Query => ?key=value&key=value (파라미터이름/값)
        URL url = new URL(path);
        try(Scanner sc = new Scanner(
                new BufferedInputStream(url.openStream()),"euc-kr")){
            String rdv = null;
            while (sc.hasNext()) {                
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
