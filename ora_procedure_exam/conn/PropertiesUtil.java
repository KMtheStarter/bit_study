package conn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

    private String protocol;
    private String userid;
    private String pass;
    private String f;

    public PropertiesUtil(String f) throws Exception {
    // 우리가 생성 시킬때 현재 설정 파일의 경로
    // dbconn.properties파일의 경로
        this.f = f;
        propertiesValue();
    }

    public void propertiesValue()
            throws FileNotFoundException, IOException {
    // java.util.Properties객체를 생성
        Properties pt = new Properties();
    // 현재 설정파일을 스트림으로 얻은 값을 로드 해온다.
        pt.load(new FileInputStream(f));
    // 키와 값을 받을 수 있는 컬렉션을 생성 해둔다.
        HashMap<String, String> map = new HashMap<String, String>();
    // 키와 값을 분리하여 map에 저장하여 둔다.
        for (Map.Entry<Object, Object> entry : pt.entrySet()) {
            map.put(entry.getKey().toString(),
                    entry.getValue().toString());
        }
        protocol = map.get("protocol");
        userid = map.get("user");
        pass = map.get("pass");
    }
    // 아이디/ 비밀번호 / 프로토콜 등을 얻을 수 있는 getter

    public String getPass() {
        return pass;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUserid() {
        return userid;
    }
}
