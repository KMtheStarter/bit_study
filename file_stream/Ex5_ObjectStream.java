package ex2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Ex5_ObjectStream {

	public static void main(String[] args) {
		String path = "D:\\javabook\\demo\\ex5_data.txt";
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
			Ex5_Member v = new Ex5_Member();
			v.setId("���̵�~");
			v.setName("�̸�~");
			v.setPwd("��й�ȣ~");
			v.setAge(20);
			v.setPay(500);
			oos.writeObject(v);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
