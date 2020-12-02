import java.io.*;
import java.net.*;

class Client extends Thread {
	// Ŭ���̾�Ʈ�� �����忩�� ��(I/O�� �޾Ƶ鿩�� �ϱ� ������)
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// Ű���尪 �޾ƿ��� ���۸��� �غ�
	Socket s;
	// ���� 
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId;

	Client(){
		connect();
	}
	void connect(){ //������ ����
		try{
			p("����IP(�⺻:127.0.0.1): ");
			String ip = br.readLine();
			//ó���� �ߴ� ��Ʈ : ������ �Է�                //�Ҵ�����:��������� ���� ���°� �ƴ϶� �о���°� �ƴѰ����?
			// ip�� ���� �Է��� �����ǰ� ����. 
			if(ip != null) ip = ip.trim();
			if(ip.length() == 0) ip = "127.0.0.1";
			// �����ǿ� ���� ���ٸ�, �� ���͸� �׳� �ƴٸ�                //�Ҵ�����:trim�� �����ΰ���,���� ���� �����ΰ���?
			// ip�� �� ���� ����. 
			
			p("PORT(�⺻:3000): ");                                      //�Ҵ�����:��Ʈ�� ���ʿ� �����ε� String���� �޾Ѵ������� �����ΰ���?
			String portStr = br.readLine();
			// ���� �Է��϶�� â�� ���.
			if(portStr != null) portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "3000";
			// ���� �Է����� �ʰ� ���͸� ģ�ٸ�, �� ���� �׳� ��Ʈ�� ����.
			int port = Integer.parseInt(portStr);
			// ���� ���� �Է��ϸ�, �� ���� ��Ʈȭ �ؼ� port�� �־��ش�.
			if(port<0 || port>65535){
				// �ٵ� ���̳ʽ����̰ų�, ��Ʈ �ִ밪�� �Ѿ��
				pln("������ ��ȿ���� ���� ��Ʈ��");
				// �� ��Ʈ�� �߰� �����
				connect();
				// �׸��� �ٽ� ���� �õ�
				return;
				// �׸��� �� �κ� ��������, �ٽ� ���� ���ϰ�
			}

			s = new Socket(ip, port);
			// �Է��� ip�� port���� ������ ������ �������.
			pln("������ ���� ����");
			// ���� �� �� ��Ʈ ���
			is = s.getInputStream();
			// ���Ͽ� �ִ� �� (������ ���´ٸ�) �� ��ǲ��Ʈ������ �����ͼ� is�� ����.
			os = s.getOutputStream();
			// os�� ��� ���� ���Ͽ� ������.
			dis = new DataInputStream(is);
			// is���� datainputStream������ �ٲ���.
			dos = new DataOutputStream(os);
			// os���� dataoutputStream������ �ٲ���.
			start();
			// ���⼭ run �޼ҵ� �ҷ��ͼ� ���ÿ� ������� �����ֱ� ������.
			// run�޼ҵ�� - �޼����� ���� ����Ϳ� ����ִ� ����.
			// ������� ���ư��� ������ �޼����� �͵� ���� �� ����
             
            inputChatId();
			// �� ���̵� �Է��ϰ���.
		}catch(IOException ie){}
	}
	public void run(){ //listen ( socket -> monitor )
		try{
			while(true){
				// ���� �޼����� ���� �𸣴ϱ� ���ѷ����� ��� ������.
				String msg = dis.readUTF();
				// ���࿡, ���Ͽ��� �޼����� ������, UTF�� ��ȯ�ؼ� msg�� ����
				pln(msg);
				// msg ��� 
			}
		}catch(IOException ie){
			pln("������ �ٿ��.. 2�� �Ŀ� ����˴ϴ�.");
			try{
				Thread.sleep(2000);
				// 2�� ����ٰ�
				System.exit(0);
				// ������ ��.
			}catch(InterruptedException iee){}
			// ������ ���õ� ���ܻ�Ȳ, �� ������ ����ߵȴٴϱ� �غ���.
		}finally{
			closeAll();
			// ���ѷ����� ������?? �����ٴ� �ǹ�!
			// ���� ����ߴ��� �� ��ȯ��������.
		}
	}
    void inputChatId(){ //ä��ID�� �Է�
		p("ä��ID(�⺻:GUEST): ");
		try{
			chatId = br.readLine();
			// ���̵� Ű���忡�� �޾Ƽ� chatId�� �־��ش�.
			if(chatId  != null) chatId = chatId.trim();
			if(chatId.length() == 0) chatId = "GUEST";
			// �Է°����� ����ġ��, GUEST�� �������ش�.
			dos.writeUTF(chatId);
			// ���� �Է��� ���̵�, output ��Ű���� dataoutputstream�� �־��ְ�
			dos.flush();                                                       //�Ҵ�����:flush�� �߼����� ���� �Ǵ°ǰ���?
			// �߼��Ѵ�.

			inputMsg();
			// ���� �޼��� ������ �޼ҵ带 �����Ѵ�.
		}catch(IOException ie){
		}
	}
    void inputMsg(){ //speak ( key -> socket )
		String msg = "";
		try{
			while(true){
				// ���ѷ���, ���� �Է°� ���� �Է����� �𸣴ϱ� �����
				msg = br.readLine();
				// ���� Ű���忡�� �Է¹������� msg�� �����.
				dos.writeUTF(chatId + ">> " + msg);
				// �߼��ϱ��Ұǵ�, ��µǴ� ������ ���̵� >> �޼����� �ǰ� �������ְ�
				dos.flush();
				// �߼��Ѵ�.
			}
		}catch(IOException ie){
		}finally{
			// ���ѷ���������? ������!
			closeAll();
			// �� ��ȯ
		}
	}
	void closeAll(){ //���ᰴü�� �ݱ�
		// �׳ɵθ� �޸� ����Ǵϱ�.. �������÷��Ͱ� ��������� ������.
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
