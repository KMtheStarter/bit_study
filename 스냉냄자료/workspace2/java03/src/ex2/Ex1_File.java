package ex2;
/* Ex1_File */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex1_File {
    public static void main(String[] args) {
        //ù��° ���
        String path = "d:\\javabook\\demo\\demo.txt";
        //���� ��ü�� ����
        File f = new File(path);
        //�ֿ� �޼ҵ� ����
        System.out.println("demo1.txt�� �����ϴ°�?"+f.exists());
        if(f.exists()){
            System.out.println("����");
        }
        System.out.println("demo1.txt�� �����ΰ�?"+f.isFile());
        System.out.println("������ �����Ѱ�?"+f.canExecute());
        System.out.println("�ۼ��� �����Ѱ�?"+f.canWrite());
        System.out.println("������"+f.getAbsolutePath());
        System.out.println("���� �̸�"+f.getName());
        System.out.println("���丮��?"+f.isDirectory());
        System.out.println("������ ����"+f.length());
        
        //�ι�° ���
        //��� �������� �ʾƵ� �����ȳ���.
        File f2 = new File("d:\\javabook\\demo\\aa2.txt");
        if(!f2.exists()){ 
            try {
            // ������ �������� ���� ��
            // 0����Ʈ¥�� �� ������ �����ϰڴ�.
            f2.createNewFile();
            // IOException ? ����� ����
            } catch (IOException ex) {
                // ���� ���ÿ��� ������ �޼����� ���!
                ex.printStackTrace();
            }
        }else{ 
            System.out.println("�̹����� �մϴ�.");
        }
        //f3�̶� ���ϰ�ü��
        //C:\\kosta188\\demo\\works\\work1������ �ѹ��� �����ʹ�.
        //mkdirs - �߻����� ���� ���丮���� ����
        File f3 = new File("d:\\javabook\\demo\\works\\work1");
        if(!f3.exists()){
            f3.mkdirs();
            System.out.println("Path :"+f3.getPath());
        }else{
            System.out.println("�̹� ���� �մϴ�."+f3.getAbsolutePath());
        }
        
    }
}
