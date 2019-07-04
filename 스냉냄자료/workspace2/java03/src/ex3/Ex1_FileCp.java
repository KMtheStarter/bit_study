package ex3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex1_FileCp 
특정 파일을 다른 위치에 복사를 하기 위한 기능을 구현
*/
public class Ex1_FileCp {
    public static void main(String[] args) throws IOException, IOException {
        long start = System.currentTimeMillis();
        // 원본파일
        String path1 = "C:\\javabook\\demo\\netbeans.exe";
        String path2 = "C:\\javabook\\demo\\netbeans2.exe";
        // 바이트스트림을 선언
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(path1);
            fos = new FileOutputStream(path2);
            // fis.read() : 1바이트식 읽어 들이는 메서드
            // 마지막 -1 을 반환한다.
            int readV = 0;
            while((readV = fis.read()) != -1){
                // 읽어 들인 내용을 사본파일에 저장 
                fos.write(readV);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally{ // 자원을 해제한다.
           if(fis != null) fis.close();
           if(fos != null) fos.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("복사한 시간 :"+(end - start));
    }
}









