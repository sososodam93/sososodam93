import java.io.*;
import java.net.*;

class AClient extends Thread {  // socket + ip + port
   Socket s;
   String ip = "192.168.0.125"; //  "localhost" / ipconfig 확인
   int port = 2020; // (아파트 동)
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
   InputStream is;
   OutputStream os; // Node
   DataOutputStream dos; // Filter
   DataInputStream dis;
   PrintStream ps = System.out;
   
   AClient() {
      try{
         s = new Socket(ip, port);
         pln("서버와 연결 성공");
         readyIO();
         start();
         speak();
      }catch(UnknownHostException ne){
      }catch(IOException ie){
         pln("서버("+ip+")를 네트워크 상에서 찾을 수 없음");
      }
   }
   void readyIO(){
      try{
         os = s.getOutputStream();
         is = s.getInputStream();
         dos = new DataOutputStream(os);
         dis = new DataInputStream(is);
      }catch(IOException ie){}
   }
   void speak(){ // Keyboard -> Socket
      try{
         String line = null;
         while((line = br.readLine()) != null){
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
            ps.println("Server>> " + line);
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
      listen();
   }
   public static void main(String[] args) 
   {
      new AClient();
   }
}