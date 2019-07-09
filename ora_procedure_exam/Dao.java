package ora_procedure;

import conn.ConnUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static Dao dao;

    public static synchronized Dao getDao() {
        if (dao == null) {
            dao = new Dao();
        }
        return dao;
    }

    public List<SawonVO> sawonSearch(int deptno) throws Exception {
        Connection con = null;
        List<SawonVO> list = new ArrayList<SawonVO>();
        // 커넥션 획득
        con = ConnUtil.getConn();
        // 프로시져 호출
        CallableStatement cs = con.prepareCall("begin p_sawon(?,?); end;");
        // 입력 파라미터
        cs.setInt(1, deptno);
        // 출력 파라미터
        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        // 실행
        cs.execute();
        ResultSet rs = (ResultSet) cs.getObject(2);
        while (rs.next()) {
            SawonVO v = new SawonVO();
            v.setSabun(rs.getInt("sabun"));
            v.setSaname(rs.getString("saname"));
//            v.setComm(rs.getInt("comm"));
            v.setDeptno(rs.getInt("deptno"));
            v.setSahire(rs.getString("sahire"));
            v.setSajob(rs.getString("sajob"));
            v.setSapay(rs.getInt("sapay"));
            v.setSasex(rs.getString("sasex"));
            list.add(v);
        }

        if (cs != null) {
            cs.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }

    public void insertDB(SawonVO v) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = ConnUtil.getConn();
            cs = con.prepareCall("begin ps_in(?,?,?,?,?,?); end;");
            cs.setString(1, v.getSaname());
            cs.setInt(2, v.getDeptno());
            cs.setString(3, v.getSajob());
            cs.setInt(4, v.getSapay());
            cs.setString(5, v.getSasex());
            cs.setInt(6, v.getSamgr());
            cs.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (con != null){
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}