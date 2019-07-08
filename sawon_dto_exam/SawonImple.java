/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle2;

import conn.ConnDriver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bit
 */
public class SawonImple implements SawonInter {

    private static SawonImple sawondao;
    public synchronized static SawonImple getSawondao(){
        if (sawondao == null){
            sawondao = new SawonImple();
        }
        return sawondao;
    }            
    
    @Override
    public List<SawonDTO> getListSawon(String searchValue) {

        String sql1 = "select sabun, saname, deptno, sapay, sahire from sawon where saname like ? order by 1 desc";
        Connection con = null;
        PreparedStatement pstm = null;
        // select일 경우 cursor를 반환
        ResultSet rs = null;
        // SawonDTO의 집합객체 생성
        List<SawonDTO> list = new ArrayList<>();
        
        try {
            // 1. connection 연결
            con = ConnDriver.getMyConnection();
            System.out.println("연결성공: " + con);
            pstm = con.prepareStatement(sql1);
            pstm.setString(1, "%" + searchValue + "%");
            // sql문을 전송한 후 커서를 반환
            rs = pstm.executeQuery();

            while (rs.next()) {
                // 사원의 정보 하나 당 SawonDTO가 하나씩 생성
                SawonDTO vo = new SawonDTO();
                // sabun, saname, deptno, sapay, sahire
                vo.setSabun(rs.getInt("sabun"));
                vo.setSaname(rs.getString("saname"));
                vo.setDeptno(rs.getInt("deptno"));
                vo.setSapay(rs.getInt("sapay"));
                vo.setSahire(rs.getString("sahire"));
                // 사원들의 정보를 리스트에 추가
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { // 자원해제
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void addSawon(SawonDTO dto) {
        Connection con = null;
        PreparedStatement pstm = null;
        // insert into sawon values(sawon_seq.nextVal, '박점심', 20, '과장', 5000, sysdate, '남자', 10);
        String sql = "insert into sawon values(sawon_seq.nextVal, ?, ?, ?, ?, sysdate, ?, ?)";
        pstm = con.prepareStatement(sql);
        pstm.setString(1, "%" + searchValue + "%");
        pstm.setString(2, "%" + searchValue + "%");
        pstm.setString(3, "%" + searchValue + "%");
        pstm.setString(4, "%" + searchValue + "%");
        pstm.setString(5, "%" + searchValue + "%");
        pstm.setString(6, "%" + searchValue + "%");
    }

}
