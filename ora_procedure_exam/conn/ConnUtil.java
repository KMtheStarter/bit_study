package conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnUtil {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConn() throws Exception {
        PropertiesUtil pv = new PropertiesUtil("src/conn/dbconn.properties");
        return DriverManager.
                getConnection(pv.getProtocol(), pv.getUserid(), pv.getPass());
    }
}
