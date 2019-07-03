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
		  // 연결 스트림 생성
			
			// 서버소켓에 접속한 클라이언트의 정보를 알아오는 부분..
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("[Server] 연결됨 from " + inetSocketAddress.getHostName() + " :" + inetSocketAddress.getPort() + "/"
					+ inetSocketAddress.getHostString());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")) ;
			os = socket.getOutputStream();
			
			//반복하여 클라이언트의 요청을 처리하기 위함.
			String line = ""; 	
			String request = null ; 
			while(true) {
				line = br.readLine() ;
				
				if(line ==null || "".equals(line)) {
					break ;
				}
				
				// 첫줄만 읽어온다.
				if(request == null) {
					request = line ;
				}
			}

			consoleLog(request);

			// 헤더 값을 분석한다.  
			// tokens[0] = "GET"   (메소드)
			// tokens[1] = "/"     (URL)
			// tokens[2] = HTTP/1.1(버전)
			String [] tokens = request.split(" ") ;
			
			if("GET".equals(tokens[0])) {
				//GET 메소드 호출 시 정상 처리 
				responseStaticResource(os, tokens[1], tokens[2]) ;
			}else {
				//POST, PUT 등의 HTTP 메소드 호출시 오류로 처리
				response400Error(os, tokens[2]);
			}
			
			
		}catch(IOException e) {
			consoleLog("error :" + e);
		}finally {
		//자원반납	
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
		//서버의 파일 시스템에서 해당 파일을 읽는다...
                //D:/javabook/workspace2/test/web/
		File file = new File("aaaa/aaaa/aaa"+url) ;
                System.out.println("Path:"+file.getAbsolutePath());
                System.out.println("Test:"+file.exists());
		if(file.exists() == false) {
			//404 오류 발생 
			response404Error(os, protocol);
			return ;
		}
		
		//파일을 바이너리 배열로 읽어옵니다.
		byte[] body = Files.readAllBytes(file.toPath()) ;
		String mimeType = Files.probeContentType(file.toPath()) ;
		consoleLog("해당 페이지의 mimetype은 :" + mimeType);
		
		//클라이언트 브라우저로 데이터 전송
		// 1) 헤더 작성
		os.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + mimeType+"; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write("\r\n".getBytes("UTF-8"));
		// 2) 바디 작성.
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
