package ex1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex5_Demo */
public class Ex5_Demo {
    public static void main(String[] args) throws MalformedURLException {
        BufferedReader br =null;
        try {
            String path ="http://edu2.kosta.or.kr/lectures/search?searchText=Mybatis";
            URL url = new URL(path);
            // 어쩔수 없이 바이트스트림으로 받아온 객체를
            // 문자스트림으로 변경하려고 할때가 있다.
            // InputStreamReader(inputstream,인코딩)
            // => Scanner로 대체 가능하다. (InputStreamReader x)
            // 연습문제 : Scanner의 API 참고해서 
            // 똑같은 기능으로 Ex5_ScannerDemo에서 구현하시오.
            br = new BufferedReader(
                    new InputStreamReader(url.openStream(),"utf-8"));
             String str = null;
            while ((str = br.readLine()) != null) {                
                System.out.println(str);
            }
        } catch (IOException ex) {
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Ex5_Demo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
