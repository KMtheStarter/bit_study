package ex1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex4_InputStreamReader 
    바이트스트림 -> 문자스트림으로 연결하기 위한 스트림
*/
public class Ex4_InputStreamReader {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            String path = "C:\\kosta188\\demo\\memo.txt";
            // InputStreamReader : 바이트를 문자열로 연결해주는 스트림
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path))
            );
            String str = null;
            while ((str = br.readLine()) != null) {                
                System.out.println(str);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
            }
        }
    }
}
