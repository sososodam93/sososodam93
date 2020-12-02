import java.net.*; //���� import����� ��Ű���
import java.io.*;  //io�� ����� ���� �б� ����

class UServerClient extends Thread {   //Thread�� ����� ���������� ���ÿ� ���డ��
   
   int port = 5000; //port�� ���ڴϱ� int Ÿ������ �޴´�
   String ip; //ip�� ������ Ÿ������ ����
   String ipHeader = "192.168.0."; // ip�� ���ڸ��� �� �����ϱ� �ݺ��� �������ش�.
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // Ű���忡�� �Է��ϴ°��� ����Ƽ ���� �޾��ش�

   void init(){ 
      start();  // JVM -> Thread -> run() �ð��� �ɸ� �� �ֱ⶧���� �����̸� �־��ش�.
      try{
         Thread.sleep(50); //0.05��
      }catch(Exception e){}

      //inputIp();

      DatagramSocket ds = null; //���۷�������null�� �ʱ�ȭ���ش� // �޼�����(��ü��)
      DatagramPacket dp = null;  // �޼���Ʋ(��������)
      try{
         ds = new DatagramSocket(); // ��ü���� ds�� �����
         
         while(true){  //������ �ݺ��ɼ��ֵ��� while
            inputIp(); //�����Ǹ� �Է����ش�
            //p("������ �޼���: ");
            String msg = br.readLine();  // ���濡��  �� �޼����� �Է�
            if(msg != null) msg = msg.trim();   //�޼����� ������������� ������ �������ش�
            byte[] buf = msg.getBytes(); //�ٹ���Ʈ�� ���� �� ���� ����Ʈ�� �����´� �׷��� ����ƮŸ������ �������� ��ü�� ����
            InetAddress ia = InetAddress.getByName(ip); //���̳ݾ�巹���� ������
            //InetAddress = ip�̾��ִ� ����
            // static�� Ŭ�����̸����� �����Ѵ�.
            dp = new DatagramPacket(buf, buf.length, ia, port); //������ �޼���,�޼��� ����,������,��Ʈ�� �ǹ��Ѵ� �װ� ���� dp�� ����.
            ds.send(dp); //dp�� ������ ������ �׷� ���ۿϷ� ������ �߰� �ȴ�.,
            pln("���� �Ϸ�!!");
         }
      }catch(SocketException se){ // �ڽĸ������;��Ѵ�. IO�� �ڽĵ�
         init(); //���ȣ�� �ڱ��ڽ��� ȣ���ϴ°�.
      }catch(UnknownHostException ue){ // ���ͳݻ��� �׷� ip�� ã�� �� ����
         pln("��Ʈ���� �ش缭��("+ip+")�� ã�� �� ����");
         init(); //���ȣ�� �ڱ��ڽ��� ȣ���ϴ°�.
      }catch(IOException ie){
         init();// �ٽ� �� �� �����ϰ� ���ְԲ� �ϴ� ��. �߸������ϱ� �ٽ� ���� �ݺ�.
      }finally{
         ds.close(); //�ݾ��ش�
      }
   }
   void inputIp(){ //�����Ǹ� �־��ش�
      try{
         p("IP(���ڸ�): ");
         String ipTail =  br.readLine();   //�������� ���ڸ��� �Է�
         if(ipTail != null) ipTail = ipTail.trim(); //ip�� ������ �������ش�
         if(ipTail.length() != 0){ //�������� ���̰� ���� �ƴϸ� ��,���� ������ ����������� �����̶� ���ؼ� �����ǰ� �־�����. 
            ip = ipHeader + ipTail;
         }else{
            inputIp(); //�׿ܻ�Ȳ�� �ٽ� �޼ҵ�� ���ư��� �ٽ� �����ϵ����Ѵ�
         }
      }catch(IOException ie){
         inputIp();
      }
   }
   public void run(){   //�����带 ���� �������ִ� ���� , ���� �ϴ°� ������ ��Ȱ
      DatagramSocket ds = null; // �޼�����(��ü��)
      DatagramPacket dp = null; // �޼���Ʋ(��������)
      try{
         ds = new DatagramSocket(port);
         pln(port+"������ UDP���� �����..");
         byte buf[] = new byte[2048]; //?????
         dp = new DatagramPacket(buf, buf.length); //���ȿ� ������ ���� �׷��� �ޱ⸸ �ϸ�ȴ�.// ����Ʈ�� �����⶧���� ��Ʈ������ ��ȯ��

            while(true){ //���ѷ���
            ds.receive(dp); //���������� ��� �޼����� ��ü�뿡 �־��ش�
            String msg = new String(buf); //���� �Է��� �޼���
            msg = msg.trim(); //�޼�����; ������ �������ش�
            pln("Client>> " + msg); // �ѷ��ֱ�. 
            for(int i=0; i<buf.length; i++) buf[i]=0; //�����ȵǰ� �״�� �ִ� ���ڵ��� �����ִ� ����
            //�⺻���� 0�̱⶧����. �迭��
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