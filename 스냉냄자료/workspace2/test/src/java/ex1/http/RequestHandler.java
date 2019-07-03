package ex1.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.text.FieldPosition;

public class RequestHandler extends Thread {
	private Socket socket = null ;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		BufferedReader br = null ;
		OutputStream os = null ;
		try {
		  // ���� ��Ʈ�� ����
			
			// �������Ͽ� ������ Ŭ���̾�Ʈ�� ������ �˾ƿ��� �κ�..
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("[Server] ����� from " + inetSocketAddress.getHostName() + " :" + inetSocketAddress.getPort() + "/"
					+ inetSocketAddress.getHostString());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")) ;
			os = socket.getOutputStream();
			
			//�ݺ��Ͽ� Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ����.
			String line = ""; 	
			String request = null ; 
			while(true) {
				line = br.readLine() ;
				
				if(line ==null || "".equals(line)) {
					break ;
				}
				
				// ù�ٸ� �о�´�.
				if(request == null) {
					request = line ;
				}
			}

			consoleLog(request);

			// ��� ���� �м��Ѵ�.  
			// tokens[0] = "GET"   (�޼ҵ�)
			// tokens[1] = "/"     (URL)
			// tokens[2] = HTTP/1.1(����)
			String [] tokens = request.split(" ") ;
			
			if("GET".equals(tokens[0])) {
				//GET �޼ҵ� ȣ�� �� ���� ó�� 
				responseStaticResource(os, tokens[1], tokens[2]) ;
			}else {
				//POST, PUT ���� HTTP �޼ҵ� ȣ��� ������ ó��
				response400Error(os, tokens[2]);
			}
			
			
		}catch(IOException e) {
			consoleLog("error :" + e);
		}finally {
		//�ڿ��ݳ�	
			try {
			if(br!=null) br.close();
			if(os !=null )os.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	
	}
	
	public void responseStaticResource(OutputStream os, String url, String protocol) 
	throws IOException{
            System.out.println("========================");
            System.out.println("url:"+url);
		if("/".equals(url)) {
			url = "/index.html" ;
		}else if("index_1.html".equals(url)){
                       url = "/index_1.html" ;
                }
		//������ ���� �ý��ۿ��� �ش� ������ �д´�...
                //D:/javabook/workspace2/test/web/
		File file = new File("aaaa/aaaa/aaa"+url) ;
                System.out.println("Path:"+file.getAbsolutePath());
                System.out.println("Test:"+file.exists());
		if(file.exists() == false) {
			//404 ���� �߻� 
			response404Error(os, protocol);
			return ;
		}
		
		//������ ���̳ʸ� �迭�� �о�ɴϴ�.
		byte[] body = Files.readAllBytes(file.toPath()) ;
		String mimeType = Files.probeContentType(file.toPath()) ;
		consoleLog("�ش� �������� mimetype�� :" + mimeType);
		
		//Ŭ���̾�Ʈ �������� ������ ����
		// 1) ��� �ۼ�
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + mimeType+"; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes("UTF-8"));
		// 2) �ٵ� �ۼ�.
		os.write(body);
		os.flush();
	}
	
	
	public void response400Error(OutputStream os, String protocol) 
	throws IOException{
		File file = new File("D:/javabook/workspace2/test/web/error/400.html") ;
		byte[] body = Files.readAllBytes(file.toPath()) ;
		
		os.write((protocol + " 400 Bad Request\r\n").getBytes("UTF-8"));
		os.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
		os.write("\r\n".getBytes("UTF-8"));
		os.write(body);
		os.flush();
	}
	
	public void response404Error(OutputStream os, String protocol) 	
			throws IOException{
		File file = new File("./webapp/error/404.html") ;
		byte[] body = Files.readAllBytes(file.toPath()) ;
		
		os.write((protocol + " 404 File Not Found\r\n").getBytes("UTF-8"));
		os.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
		os.write("\r\n".getBytes("UTF-8"));
		os.write(body);
		os.flush();
	}
	
	
	private void consoleLog(String msg) {
		System.out.println("[Server :" + getId() + "]" + msg);
	}
}
