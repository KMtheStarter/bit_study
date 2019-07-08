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
        // select�� ��� cursor�� ��ȯ
        ResultSet rs = null;
        // SawonDTO�� ���հ�ü ����
        List<SawonDTO> list = new ArrayList<>();
        
        try {
            // 1. connection ����
            con = ConnDriver.getMyConnection();
            System.out.println("���Ἲ��: " + con);
            pstm = con.prepareStatement(sql1);
            pstm.setString(1, "%" + searchValue + "%");
            // sql���� ������ �� Ŀ���� ��ȯ
            rs = pstm.executeQuery();

            while (rs.next()) {
                // ����� ���� �ϳ� �� SawonDTO�� �ϳ��� ����
                SawonDTO vo = new SawonDTO();
                // sabun, saname, deptno, sapay, sahire
                vo.setSabun(rs.getInt("sabun"));
                vo.setSaname(rs.getString("saname"));
                vo.setDeptno(rs.getInt("deptno"));
                vo.setSapay(rs.getInt("sapay"));
                vo.setSahire(rs.getString("sahire"));
                // ������� ������ ����Ʈ�� �߰�
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { // �ڿ�����
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
        // insert into sawon values(sawon_seq.nextVal, '������', 20, '����', 5000, sysdate, '����', 10);
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
