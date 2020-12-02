import java.io.*;
import java.net.*;

class Client extends Thread {
	// 클라이언트도 스레드여야 함(I/O를 받아들여야 하기 때문에)
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 키보드값 받아오는 버퍼리더 준비
	Socket s;
	// 소켓 
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId;

	Client(){
		connect();
	}
	void connect(){ //서버와 접속
		try{
			p("서버IP(기본:127.0.0.1): ");
			String ip = br.readLine();
			//처음에 뜨는 멘트 : 아이피 입력                //소담질문:리드라인은 내가 적는게 아니라 읽어오는게 아닌가요ㅕ?
			// ip에 내가 입력한 아이피가 들어간다. 
			if(ip != null) ip = ip.trim();
			if(ip.length() == 0) ip = "127.0.0.1";
			// 아이피에 값이 없다면, 즉 엔터를 그냥 쳤다면                //소담질문:trim이 무엇인가요,렝스 영은 무엇인가요?
			// ip에 이 값이 들어간다. 
			
			p("PORT(기본:3000): ");                                      //소담질문:포트는 애초에 숫자인데 String으로 받앗던이유는 무엇인가요?
			String portStr = br.readLine();
			// 포토 입력하라고 창에 뜬다.
			if(portStr != null) portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "3000";
			// 만약 입력하지 않고 엔터를 친다면, 이 값이 그냥 포트에 들어간다.
			int port = Integer.parseInt(portStr);
			// 만약 값을 입력하면, 그 값을 인트화 해서 port에 넣어준다.
			if(port<0 || port>65535){
				// 근데 마이너스값이거나, 포트 최대값을 넘어가면
				pln("범위가 유효하지 않은 포트임");
				// 이 멘트를 뜨게 해줘라
				connect();
				// 그리고 다시 연결 시도
				return;
				// 그리고 이 부분 나가버림, 다시 실행 못하게
			}

			s = new Socket(ip, port);
			// 입력한 ip와 port값을 가지는 소켓을 만들어줌.
			pln("서버와 연결 성공");
			// 만든 후 이 멘트 출력
			is = s.getInputStream();
			// 소켓에 있는 값 (서버가 보냈다면) 을 인풋스트림으로 가져와서 is에 넣음.
			os = s.getOutputStream();
			// os에 담긴 값을 소켓에 보낸다.
			dis = new DataInputStream(is);
			// is값을 datainputStream형으로 바꿔줌.
			dos = new DataOutputStream(os);
			// os값을 dataoutputStream형으로 바꿔줌.
			start();
			// 여기서 run 메소드 불러와서 동시에 스레드로 돌려주기 시작함.
			// run메소드는 - 메세지가 오면 모니터에 띄워주는 역할.
			// 스레드로 돌아가야 언제든 메세지가 와도 받을 수 있음
             
            inputChatId();
			// 내 아이디를 입력하게함.
		}catch(IOException ie){}
	}
	public void run(){ //listen ( socket -> monitor )
		try{
			while(true){
				// 언제 메세지가 올지 모르니까 무한루프로 계속 돌려줌.
				String msg = dis.readUTF();
				// 만약에, 소켓에서 메세지가 들어오면, UTF로 변환해서 msg에 담음
				pln(msg);
				// msg 출력 
			}
		}catch(IOException ie){
			pln("서버가 다운됨.. 2초 후에 종료됩니다.");
			try{
				Thread.sleep(2000);
				// 2초 멈췄다가
				System.exit(0);
				// 끝나게 함.
			}catch(InterruptedException iee){}
			// 스레드 관련된 예외상황, 잘 모르지만 해줘야된다니까 해보자.
		}finally{
			closeAll();
			// 무한루프가 끝났다?? 꺼졌다는 의미!
			// 이제 사용했던거 다 반환시켜주자.
		}
	}
    void inputChatId(){ //채팅ID를 입력
		p("채팅ID(기본:GUEST): ");
		try{
			chatId = br.readLine();
			// 아이디를 키보드에서 받아서 chatId에 넣어준다.
			if(chatId  != null) chatId = chatId.trim();
			if(chatId.length() == 0) chatId = "GUEST";
			// 입력값없이 엔터치면, GUEST로 설정해준다.
			dos.writeUTF(chatId);
			// 내가 입력한 아이디를, output 시키려고 dataoutputstream에 넣어주고
			dos.flush();                                                       //소담질문:flush를 발송으로 보면 되는건가요?
			// 발송한다.

			inputMsg();
			// 이제 메세지 보내는 메소드를 실행한다.
		}catch(IOException ie){
		}
	}
    void inputMsg(){ //speak ( key -> socket )
		String msg = "";
		try{
			while(true){
				// 무한루프, 내가 입력값 언제 입력할지 모르니까 대기중
				msg = br.readLine();
				// 내가 키보드에서 입력받은값을 msg에 담아줌.
				dos.writeUTF(chatId + ">> " + msg);
				// 발송하긴할건데, 출력되는 순서가 아이디 >> 메세지가 되게 설정해주고
				dos.flush();
				// 발송한다.
			}
		}catch(IOException ie){
		}finally{
			// 무한루프끝낫다? 꺼졌다!
			closeAll();
			// 다 반환
		}
	}
	void closeAll(){ //연결객체들 닫기
		// 그냥두면 메모리 낭비되니까.. 가비지컬렉터가 가져가라고 보내줌.
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		new Client();
	}
}
