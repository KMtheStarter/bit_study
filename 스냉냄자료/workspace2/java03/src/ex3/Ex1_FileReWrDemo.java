package ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Ex1_FileReWrDemo 
    문자 전용 스트림! 2byte
 */
/**
 *
 * @author kosta188기에서 배포하는 자바 코드입니다.
 */
public class Ex1_FileReWrDemo {

    private String path;

    /**
     * @see 기본 생성자, 저장경로는 window의 c...
     */
    public Ex1_FileReWrDemo() {
        path = "C:\\kosta188\\demo\\memo.txt";
    }

    /**
     *
     * @param msg 는 memo.txt에 BufferedWriter를 사용해서 문자열로 저장되기 위한 스트림입니다.
     */
    public void writeMemo(String msg) {
        //버퍼 기능을 가진 문자 스트림 (2차 스트림)
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path, true));
            //문자 스트림을 사용해서 해당 경로의 문서에 작성
            bw.write(msg);
            bw.newLine(); // 개행
            bw.flush(); // 버퍼를 비워주는 기능
        } catch (IOException ex) {
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                }
            }
        }
    }    
        /**
         *
         * @return ArrayList<String>
         * 메모장의 내용을 FileReader로 읽어와서 문자열의 집합으로 반환 합니다.
         */
    public ArrayList<String> getMemo() throws FileNotFoundException, IOException {
        ArrayList<String> ar = new ArrayList<>();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(path));
        String rdv = null;
        // readLine() 은 스트림을 통해서 문자열을 한줄 단위로 읽어온다.
        // 마지막은  null 
        while((rdv = br.readLine()) != null){
            //읽어온 문자열을 한 줄 씩 ArrayList에 저장
            ar.add(rdv);
        }
        br.close();
        return ar;
    }
}
