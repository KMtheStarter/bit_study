package ex3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex1_FileCp 
Ư�� ������ �ٸ� ��ġ�� ���縦 �ϱ� ���� ����� ����
*/
public class Ex1_FileCp {
    public static void main(String[] args) throws IOException, IOException {
        long start = System.currentTimeMillis();
        // ��������
        String path1 = "C:\\javabook\\demo\\netbeans.exe";
        String path2 = "C:\\javabook\\demo\\netbeans2.exe";
        // ����Ʈ��Ʈ���� ����
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(path1);
            fos = new FileOutputStream(path2);
            // fis.read() : 1����Ʈ�� �о� ���̴� �޼���
            // ������ -1 �� ��ȯ�Ѵ�.
            int readV = 0;
            while((readV = fis.read()) != -1){
                // �о� ���� ������ �纻���Ͽ� ���� 
                fos.write(readV);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally{ // �ڿ��� �����Ѵ�.
           if(fis != null) fis.close();
           if(fos != null) fos.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("������ �ð� :"+(end - start));
    }
}









