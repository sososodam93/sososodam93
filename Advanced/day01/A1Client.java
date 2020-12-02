import java.io.*;
import java.net.*;

class AClient {
	Socket s;
	String ip = "192.168.0.125";
	int port = 2020;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
	OutputStream os; //Node
	DataOutputStream dos; //Filter

	AClient(){
		try{
			s = new Socket(ip, port);
			pln("서버와 연결 성공");

			readyIO();
			speak();
		}catch(UnknownHostException ne){
		}catch(IOException ie){
			pln("서버("+ip+")를 네트워크에서 찾을 수 없음");
		}
	}
	void readyIO(){
		try{
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
		}catch(IOException ie){
		}
	}
	void speak(){ //Keyboard -> Socket
		try{
			String line = br.readLine();
			dos.writeUTF(line);
			dos.flush();
			pln("서버에 메세지 전송 완료");
		}catch(IOException ie){
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new AClient();
	}
}
	
