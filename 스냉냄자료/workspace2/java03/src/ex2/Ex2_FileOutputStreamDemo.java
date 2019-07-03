package ex2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex2_FileOutputStreamDemo */
public class Ex2_FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //파일이 존재하지 않으면 
        String path = "d:\\javabook\\demo\\message.txt";
        FileOutputStream fos = null;
        try {
            // 파일을 생성한다.
            // 두번째 인자에 , 데이터를 append (true)
            fos = new FileOutputStream(path);
            fos.write(66);
            fos.write(65);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally{
            if(fos  != null) fos.close();
        }
        
    }
}
