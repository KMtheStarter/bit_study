package ex2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex1_FileInputStreamDemo */
// FileInputStream : 파일(장치) 에서 직접 연결해서 사용하는 Stream
// 1차 스트림 : 장치 에서 직접 연결해서 사용하는 Stream
public class Ex1_FileInputStreamDemo {
    public static void main(String[] args) {
        String path = "d:\\javabook\\demo\\message.txt";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            int rdv = 0;
            //파일의 시작점에서 부터 EOF(파일의 끝)까지 읽어들이는
            //반복문 , read(): 1byte씩 읽어오는 메서드 ,끝 -1을 반환
            while((rdv = fis.read()) != -1){
                System.out.print((char)rdv);
            }
          // 파일이 없으면 발생되는 예외이다.
        } catch (FileNotFoundException ex) {
            System.out.println("파일이 존재하지 않을 경우!");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("읽어 들이는 오류");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
