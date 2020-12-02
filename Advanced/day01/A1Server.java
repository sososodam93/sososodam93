import java.io.*;
import java.net. *;

class AServer {
	ServerSocket ss;
	Socket s;
	int port = 2000; //0 ~ 65535 //Well-Known Port : 0~1023
	InputStream is; //Node
	DataInputStream dis; //Filter
	PrintStream ps = System.out; //Node : Monitor

	AServer(){
		try{
			ss = new ServerSocket(port);
			pln("서버가 "+port+"번 포트에서 대기중...");

			s = ss.accept();
			pln("Client("+s.getInetAdress().getHostAddress()+") 연결 성공");

			readyIO();
			listen();
		}catch(IOException ie){
			pln("ie: " + ie);
		}
    }
	void readyIO(){ 
		try{
			is = s.getInputStream();
			dis = new DataInputStream(is);
		}catch(IOException ie){}
	}
	void listen(){ //Socket - > Monitor
		try{
			String line = dis.readUTF();
			ps.println("클라이언드>> " + line);
		}catch(IOException ie){
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new AServer();
	}
}