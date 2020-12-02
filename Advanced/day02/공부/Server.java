import java.io.*; 
import java.net.*;
import java.util.*;

class Server {
	ServerSocket ss; // 서버니까 서버소켓 만들어줌 
	Socket s; // 연결되면 소켓이 생성되니까 만들어줌
	int port = 3000; // 포트 번호 미리 정해놓음
	Vector<OneClientModul> v = new Vector<OneClientModul>();
	// 클라이언트가 접속할때, 소켓을 만들면서 모듈로 묶어줌 > 
	// 앞으로 있어지는 클라이언트와의 소통을 모듈안에서 하게 됨.
	OneClientModul ocm;
	// 모듈을 안에서 불러야 하니까 객체 만들어줌.

	Server(){ // 생성자 부르면 여기부터 실행됨
		try{
			ss = new ServerSocket(port);
			// 서버 소켓 생성.
			pln(port+"번 포트에서 서버 대기중...");
			while(true){
				// 무한루프 이유 - 계속 돌아야 언젠가 접속이 들어오면
				// 바로 생성할 수 있게 하기 위함.
				s = ss.accept();
				// 접속들어오면 소켓s생성.
				ocm = new OneClientModul(this);
				// 해당 클라이언트와의 소통을 담당할 모듈이 하나 만들어짐.
				v.add(ocm);
				// 이 모듈이 여러개 만들어질 예정이므로, 언제든 갯수가
				// 추가되거나 삭제가 가능한 가변배열에 넣어줌.
				// v안에는 연결된 클라이언트모듈들이 들어갈 예정.
				// 그래야 각모듈별로 io를 주고받을 수있음.
				ocm.start();
				// 그리고 스레드로 해주지 않으면, 한 모듈에 붙잡혀서
				// 동시 진행이 안됨. 
				// 그래서 미리 그 모듈이 스레드로 돌아가는 모듈로 만들어놓고
				// start해서 동시에 스레드가 돌아가게 해줌.
			}
		}catch(IOException ie){
			// 여기서 발생하는 예외는 api에 보니까 서버와 연결이 안되는것!
			// 이유는 포트가 겹치는 경우이므로, 포트가 겹칠경우
			// 밑의 멘트를 출력해줌.
			pln(port+"번 포트는 이미 사용중임");
		}finally{
			// 위의 모든 일이 !!! 끝나면 !!!
            try{
				if(ss != null) ss.close();
				// (더이상 서버소켓이 사용되지 않을때 == 연결안할때
				// 만들었던 서버소켓을 반환시킴. (메모리 줄이게)
			}catch(IOException ie){}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		new Server();
	}
}

