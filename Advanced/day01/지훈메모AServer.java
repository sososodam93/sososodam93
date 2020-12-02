import java.io.*;
import java.net.*;

// Socket -> Monitor 
class AServer extends Thread {
   ServerSocket ss;
   Socket s;
   int port = 2020; // 0 ~ 65535 포트범위
   // Well-Known Port 0 ~ 1023 일반적 ( 피해주기 ) 
   InputStream is; // Node
   OutputStream os;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //Filter ( Buffered .. 가능)
   DataInputStream dis; // ( 어떤 문자든지 깨지지 않는다. ) // Filter
   DataOutputStream dos;
   PrintStream ps = System.out; // Node
   
   AServer(){
      try{
         ss = new ServerSocket(port);  // Port 
         pln("서버가 "+port+"번 포트에서 대기중..");
         //while(true){ // 무한루프를 돌리면 여러명 접속 할 수 있다.
         s = ss.accept(); // accept 객체,  Socket 탄생
         pln("Client("+s.getInetAddress().getHostAddress()+") 연결 성공 !");
         //s.close(); // 끊는 것.
         readyIO();
         start();
         listen();
      }catch(IOException ie){
         pln("ie: " + ie);
      }
   }
   void readyIO(){
      try{
         os = s.getOutputStream();
         is = s.getInputStream(); // 뽑아내기
         dis = new DataInputStream(is); // 생성자 API
         dos = new DataOutputStream(os);
      }catch(IOException ie){}
   }
   void speak(){ // Keyboard -> Socket
      try{
         String line = null;
         while((line=br.readLine()) != null){
            dos.writeUTF(line);
            dos.flush();
         }
      }catch(IOException ie){
      }finally{
         try{
            if(dos != null) dos.close();
            if(os != null) os.close();
            if(br != null) br.close();
            if(s != null) s.close();
         }catch(IOException ie){}
      }
   }
   void listen(){ // Socket -> Monitor 듣는 것까지 한것. 클라이언트는 말해야한다.
      try{
         String line = null;
         while((line = dis.readUTF()) != null){
            ps.println("Client>> " + line);
         }
      }catch(IOException ie){
         try{
            ps.println("클라이언트 퇴장! 2초 후에 종료");
            Thread.sleep(2000); // 2초
            System.exit(-1);
         }catch(InterruptedException iie){}
      }finally{
         try{
            if(dis != null) dis.close();
            if(is != null) is.close();
            if(ps != null) ps.close();
            if(s != null) s.close();
         }catch(IOException ie){}
   }
}
   void pln(String str){
      System.out.println(str);
   }
   
   public void run(){
      speak();
   }
   public static void main(String[] args) 
   {
      new AServer();
   }
}