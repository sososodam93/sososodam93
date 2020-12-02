import java.io.*;
import java.net.*;

// Socket -> Monitor 
class AServer extends Thread {
   ServerSocket ss;
   Socket s;
   int port = 2020; // 0 ~ 65535 ��Ʈ����
   // Well-Known Port 0 ~ 1023 �Ϲ��� ( �����ֱ� ) 
   InputStream is; // Node
   OutputStream os;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //Filter ( Buffered .. ����)
   DataInputStream dis; // ( � ���ڵ��� ������ �ʴ´�. ) // Filter
   DataOutputStream dos;
   PrintStream ps = System.out; // Node
   
   AServer(){
      try{
         ss = new ServerSocket(port);  // Port 
         pln("������ "+port+"�� ��Ʈ���� �����..");
         //while(true){ // ���ѷ����� ������ ������ ���� �� �� �ִ�.
         s = ss.accept(); // accept ��ü,  Socket ź��
         pln("Client("+s.getInetAddress().getHostAddress()+") ���� ���� !");
         //s.close(); // ���� ��.
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
         is = s.getInputStream(); // �̾Ƴ���
         dis = new DataInputStream(is); // ������ API
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
   void listen(){ // Socket -> Monitor ��� �ͱ��� �Ѱ�. Ŭ���̾�Ʈ�� ���ؾ��Ѵ�.
      try{
         String line = null;
         while((line = dis.readUTF()) != null){
            ps.println("Client>> " + line);
         }
      }catch(IOException ie){
         try{
            ps.println("Ŭ���̾�Ʈ ����! 2�� �Ŀ� ����");
            Thread.sleep(2000); // 2��
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