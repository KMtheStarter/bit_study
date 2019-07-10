package conn;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnDriver {

	private static DataSource ds;
	// static �ʱ�ȭ: main �޼��庸�� ����Ǿ ����
    static{
    	// context.xml
        try{
        	InitialContext ctx = new InitialContext();
        	// java:comp/env/[name] ����صα�!
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/javabook");
        } catch(NamingException ex){
            ex.printStackTrace();
        }
    }
    
    // JNDI�� DataSource�� ����ؼ� Connection�� ��ȯ�ϴ� �޽��带 ����
    public static Connection getMyConnection() throws SQLException{
        return ds.getConnection();
    }
}
