import java.io.*;
import java.net.*;

//�ϳ��� Ŭ���̾�Ʈ�� ���(Ư��Ŭ���� �� ������Ŭ�鿡�� �ѷ���)�ϴ� ���� ��� Ŭ����
class OneClientModul extends Thread 
{
	//�� Ŭ���̾�Ʈ���� ����� �ϳ��� ������ ��.
	// �� ����� ���ؼ� ������ Ŭ���̾�Ʈ�� ������ ��Ȱ�� �� �� �ְ� ��!
	// ���ü���Ǿ�� �ϹǷ� ������ ��ӹ޾���.

	Server server;
	// ���� ���� ���� ������ �ٸ� Ŭ�������� �����;� �ϴϱ� �����ҷ���.
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId = "GUEST";

	OneClientModul(Server server){
		// ������ �����ؼ� ����ҰŴϱ�, �Ű������� ������ ������.
		this.server = server; // �̰Ŵ� ����
		this.s = server.s; // �̰Ŵ� ����
		try{
			is = s.getInputStream();
			// ���Ͽ��� ��ǲ�� �޾Ƽ� is�� ����
			os = s.getOutputStream();
			// os�� �ִ°žƿ�ǲ������
			dis= new DataInputStream(is);
			// is�� �ִ°� datainpustream���� �ٲ���
			dos = new DataOutputStream(os);
			// os�� �ִ°� dataoutputStream���� �ٲ���
		}catch(IOException ie){}
	}
	public void run(){ // listen -> broadcasting 
		listen();
		// start�� �����ϸ�, �����޼ҵ带 ������� ������.
	}
	void listen(){
		String msg = "";
		try{
			chatId = dis.readUTF();
			// ���Ͽ��� ���� ������, ���̵� ��������.
			String inMsg = chatId +"�� ����!! (���ο�:" + server.v.size()+"��)";
			// ���� ������ �� �޼����� inmsg�� �־���.
			broadcast(inMsg);
			// �̸� �����ص� inmsg�� broadcast��Ŵ(���ӵǾ ����� ������ ��ο��� �޼����߼�
			server.pln(inMsg);
			// �̸� �����ص� inmsg�� broadcast��Ŵ
			while(true){
				// ���ѷ���! �̰Ŵ� ��� �����ϴ°�
				msg = dis.readUTF();
				// ���Ͽ� �޼��� �����ϸ� (������ �����ų� ������ ������) �� ������ is>dis>msg�� ��Ƽ�
				broadcast(msg);
				// msg ��ο��� ����
				server.pln(msg);
				// msg �������Ե� ����
			}
		}catch(IOException ie){
			server.v.remove(this);
			//������ ������� (����), �� ��� ��ü�� �� ����� ����̴ϱ� �� ����� ������Ŵ
			String outMsg = chatId+"�� ����!!(���ο�:" + server.v.size()+"��)";
			//�׸��� outmsg�� �̸� �޼����� �����صΰ�
			broadcast(outMsg);
			//��ο��� �߼�
			server.pln(outMsg);
			//�������Ե� �߼�
		}finally{
			closeAll();
			// ���ѷ����� �ٵ��� (����) 
			// �ڿ��� �� �ݳ���Ű��. br �̷���..
		}
	}
	void closeAll(){ //���ᰴü�� �ݱ�
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
		//��ο��� �߼��� ��� �ϳĸ�, ���� ���ϴ� msg�� ����
		try{
			for(OneClientModul ocm: server.v){
				//���� v�ȿ� �ִ� ��� ����(�� ������ �����߱⶧���� ������鿡��)
				ocm.dos.writeUTF(msg);
				//���� �� �޼����� output��Ŵ
				ocm.dos.flush();
				//������ ������
			}
		}catch(IOException ie){}
	}
}
