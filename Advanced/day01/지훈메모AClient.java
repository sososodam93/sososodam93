import java.io.*;
import java.net.*;

class AClient extends Thread {  // socket + ip + port
   Socket s;
   String ip = "192.168.0.125"; //  "localhost" / ipconfig Ȯ��
   int port = 2020; // (����Ʈ ��)
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
   InputStream is;
   OutputStream os; // Node
   DataOutputStream dos; // Filter
   DataInputStream dis;
   PrintStream ps = System.out;
   
   AClient() {
      try{
         s = new Socket(ip, port);
         pln("������ ���� ����");
         readyIO();
         start();
         speak();
      }catch(UnknownHostException ne){
      }catch(IOException ie){
         pln("����("+ip+")�� ��Ʈ��ũ �󿡼� ã�� �� ����");
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
   void listen(){ // Socket -> Monitor ��� �ͱ��� �Ѱ�. Ŭ���̾�Ʈ�� ���ؾ��Ѵ�.
      try{
         String line = null;
         while((line = dis.readUTF()) != null){
            ps.println("Server>> " + line);
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
      listen();
   }
   public static void main(String[] args) 
   {
      new AClient();
   }
}