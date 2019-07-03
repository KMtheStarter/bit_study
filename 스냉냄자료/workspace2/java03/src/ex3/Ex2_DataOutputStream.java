package ex1;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

/* Ex2_DataOutputStream 
   데이터의 자료형 까지 함께 저장
   저장된 순서대로 InputStream에 읽어 들여야 한다.
*/
public class Ex2_DataOutputStream {
    private String path;
    public Ex2_DataOutputStream() {
        path ="D:\\javabook\\demo\\ex2_data.txt";
    }
    // DataType과 함께 입력을 처리하는 메서드
   public void dataWrite(){
       // 자동으로 close 수행한다.! jdk 7 버전주의!!!!!
       try(DataOutputStream dos = 
               new DataOutputStream(new FileOutputStream(path))){
           // 입력 순서가 매우 중요하다.!
           dos.writeInt(10);
           dos.writeBoolean(true);
           dos.writeChar('A');
           dos.writeFloat(10.5f);
           dos.writeUTF("MyTest");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
    public static void main(String[] args) {
        new Ex2_DataOutputStream().dataWrite();
    }
}
