import java.net.*; //넷을 import해줘야 통신가능
import java.io.*;  //io를 해줘야 쓰고 읽기 가능

class UServerClient extends Thread {   //Thread를 해줘야 여러가지일 동시에 수행가능
   
   int port = 5000; //port는 숫자니까 int 타입으로 받는다
   String ip; //ip는 문자형 타입으로 선언
   String ipHeader = "192.168.0."; // ip의 앞자리를 다 같으니까 반복을 지정해준다.
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // 키보드에서 입력하는것을 퀄리티 높게 받아준다

   void init(){ 
      start();  // JVM -> Thread -> run() 시간이 걸릴 수 있기때문에 딜레이를 넣어준다.
      try{
         Thread.sleep(50); //0.05초
      }catch(Exception e){}

      //inputIp();

      DatagramSocket ds = null; //레퍼런스형은null로 초기화해준다 // 메세지함(우체통)
      DatagramPacket dp = null;  // 메세지틀(편지봉투)
      try{
         ds = new DatagramSocket(); // 우체통을 ds에 담아줌
         
         while(true){  //행위가 반복될수있도록 while
            inputIp(); //아이피를 입력해준다
            //p("전달할 메세지: ");
            String msg = br.readLine();  // 상대방에게  줄 메세지를 입력
            if(msg != null) msg = msg.trim();   //메세지가 비어있지않으면 공백을 제거해준다
            byte[] buf = msg.getBytes(); //겟바이트가 내가 쓴 값을 바이트로 가져온다 그래서 바이트타입으로 비유에프 개체에 담음
            InetAddress ia = InetAddress.getByName(ip); //아이넷어드레스가 아이피
            //InetAddress = ip뽑아주는 역할
            // static은 클래스이름으로 접근한다.
            dp = new DatagramPacket(buf, buf.length, ia, port); //내가쓴 메세지,메세지 길이,아이피,포트를 의미한다 그게 전부 dp에 담긴다.
            ds.send(dp); //dp를 보낸다 서버로 그럼 전송완료 문구가 뜨게 된다.,
            pln("전송 완료!!");
         }
      }catch(SocketException se){ // 자식먼저나와야한다. IO의 자식들
         init(); //재귀호출 자기자신을 호출하는거.
      }catch(UnknownHostException ue){ // 인터넷상의 그런 ip를 찾을 수 없음
         pln("네트웍상에 해당서버("+ip+")를 찾을 수 없음");
         init(); //재귀호출 자기자신을 호출하는거.
      }catch(IOException ie){
         init();// 다시 한 번 수행하게 해주게끔 하는 거. 잘못됐으니까 다시 수행 반복.
      }finally{
         ds.close(); //닫아준다
      }
   }
   void inputIp(){ //아이피를 넣어준다
      try{
         p("IP(끝자리): ");
         String ipTail =  br.readLine();   //아이피의 끝자리를 입력
         if(ipTail != null) ipTail = ipTail.trim(); //ip의 공백을 제거해준다
         if(ipTail.length() != 0){ //아이피의 길이가 영이 아니면 즉,값이 잇으면 아이피헤더랑 테일이랑 더해서 아이피가 넣어진다. 
            ip = ipHeader + ipTail;
         }else{
            inputIp(); //그외상황은 다시 메소드로 돌아가서 다시 수행하도록한다
         }
      }catch(IOException ie){
         inputIp();
      }
   }
   public void run(){   //쓰레드를 같이 수행해주는 구간 , 런이 하는게 서버의 역활
      DatagramSocket ds = null; // 메세지함(우체통)
      DatagramPacket dp = null; // 메세지틀(편지봉투)
      try{
         ds = new DatagramSocket(port);
         pln(port+"번에서 UDP서버 대기중..");
         byte buf[] = new byte[2048]; //?????
         dp = new DatagramPacket(buf, buf.length); //런안에 들은게 서버 그래서 받기만 하면된다.// 바이트가 깨지기때문에 스트링으로 변환해

            while(true){ //무한루프
            ds.receive(dp); //편지봉투에 담긴 메세지를 우체통에 넣어준다
            String msg = new String(buf); //내가 입력한 메세지
            msg = msg.trim(); //메세지으; 공백을 제거해준다
            pln("Client>> " + msg); // 뿌려주기. 
            for(int i=0; i<buf.length; i++) buf[i]=0; //삭제안되고 그대로 있는 글자들을 없애주는 역할
            //기본값이 0이기때문에. 배열의
         }
      }catch(SocketException se){
      }catch(IOException ie){
      }finally{
         ds.close();
      }
   }
   void pln(String str){
      System.out.println(str);
   }
   void p(String str){
      System.out.print(str);
   }
   public static void main(String[] args) 
   {
      new UServerClient().init();
   }
}