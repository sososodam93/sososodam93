import java.io.*;
import java.net.*;

//하나의 클라이언트와 통신(특정클에서 들어서 나머지클들에게 뿌려줌)하는 서버 모듈 클래스
class OneClientModul extends Thread 
{
	//각 클라이언트마다 모듈을 하나씩 가지게 됨.
	// 이 모듈을 통해서 서버와 클라이언트가 소통을 원활히 할 수 있게 함!
	// 동시수행되어야 하므로 스레드 상속받아줌.

	Server server;
	// 먼저 내가 만든 서버를 다른 클래스에서 가져와야 하니까 서버불러줌.
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId = "GUEST";

	OneClientModul(Server server){
		// 서버랑 연결해서 사용할거니까, 매개변수로 서버를 가져옴.
		this.server = server; // 이거는 서버
		this.s = server.s; // 이거는 소켓
		try{
			is = s.getInputStream();
			// 소켓에서 인풋값 받아서 is에 저장
			os = s.getOutputStream();
			// os에 있는거아웃풋시켜줌
			dis= new DataInputStream(is);
			// is에 있는거 datainpustream으로 바꿔줌
			dos = new DataOutputStream(os);
			// os에 있는거 dataoutputStream으로 바꿔줌
		}catch(IOException ie){}
	}
	public void run(){ // listen -> broadcasting 
		listen();
		// start를 선언하면, 리슨메소드를 스레드로 돌려줌.
	}
	void listen(){
		String msg = "";
		try{
			chatId = dis.readUTF();
			// 소켓에서 받은 값으로, 아이디 설정해줌.
			String inMsg = chatId +"님 입장!! (총인원:" + server.v.size()+"명)";
			// 누가 들어오면 뜰 메세지를 inmsg에 넣어줌.
			broadcast(inMsg);
			// 미리 설정해둔 inmsg를 broadcast시킴(접속되어서 모듈이 생성된 모두에게 메세지발송
			server.pln(inMsg);
			// 미리 설정해둔 inmsg를 broadcast시킴
			while(true){
				// 무한루프! 이거는 계속 실행하는거
				msg = dis.readUTF();
				// 소켓에 메세지 도착하면 (누군가 보내거나 서버가 보내면) 그 내용을 is>dis>msg에 담아서
				broadcast(msg);
				// msg 모두에게 보냄
				server.pln(msg);
				// msg 서버에게도 보냄
			}
		}catch(IOException ie){
			server.v.remove(this);
			//누군가 사라지면 (끄면), 이 모듈 자체가 그 사람의 모듈이니까 이 모듈을 삭제시킴
			String outMsg = chatId+"님 퇴장!!(총인원:" + server.v.size()+"명)";
			//그리고 outmsg에 미리 메세지를 설정해두고
			broadcast(outMsg);
			//모두에게 발송
			server.pln(outMsg);
			//서버에게도 발송
		}finally{
			closeAll();
			// 무한루프가 다돌면 (끄면) 
			// 자원들 다 반납시키기. br 이런거..
		}
	}
	void closeAll(){ //연결객체들 닫기
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){
		}
	}
	void broadcast(String msg){
		//모두에게 발송은 어떻게 하냐면, 내가 원하는 msg를 만들어서
		try{
			for(OneClientModul ocm: server.v){
				//벡터 v안에 있는 모든 모듈들(즉 누군가 접속했기때문에 생긴모듈들에게)
				ocm.dos.writeUTF(msg);
				//내가 쓴 메세지를 output시킴
				ocm.dos.flush();
				//끝가지 보내줌
			}
		}catch(IOException ie){}
	}
}
