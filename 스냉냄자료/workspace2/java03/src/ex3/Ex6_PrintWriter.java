package ex1;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex6_PrintWriter
명답) OutputWriter =>연결스트림인데 .....
내부적으로 OutputWriter, BufferedWriter 기능을 내장하고 있다.******
*/
public class Ex6_PrintWriter {
    public static void main(String[] args) {
        String path = "D:\\javabook\\demo\\memo3.txt";      
     // 두번째 인자 : autoFlush (자동으로 버프를 비워준다.)
        try (PrintWriter pw = new PrintWriter(
                    new FileOutputStream(path),true);){
            pw.println("하이 반가워요!2");
            //pw.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
}
