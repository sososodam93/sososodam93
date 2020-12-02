import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//BorderLayout ���������̾ƿ�(��� ������)
class B extends JFrame implements ActionListener { 
	//JFrame���� ���� ��ӹ޾Ƽ� �׼Ǹ����ʸ� �����϶�
	JButton bN, bS, bW, bE, bC; //��ư����
	JButton bPC, bPE, bPW; //b�� ��ư p�� �г�c�� ����
	Container cp; //�����̳�
	JPanel p;

	void init(){
		p = new JPanel(); //�г��� ���Ͱ� �����Ǿ��ִ�.,�г��� ���
		//�� ������ �ϳ� �̻��� ������Ʈ�� �ְ� ������ �г� ���
		p.setLayout(new BorderLayout()); //�г��� ��ġ����� ����
		bPC = new JButton("�г� ����"); //bPC��ư�� ����ϱ����� �������ְ� ��ư�� ��Ÿ���� �۾� ����
		bPE = new JButton("�г� ����");
		bPW = new JButton("�г� ����");
        p.add(bPC, BorderLayout.CENTER);//�гο� ���ϼ� �ְ� ��ġ �����ؼ� �߰����ش�
		p.add(bPE, BorderLayout.EAST);
		p.add(bPW, BorderLayout.WEST);
		bPC.addActionListener(this);//������ �����ϵ��� �׼Ǹ����ʸ� �߰����ش� this�� �־ �ڱ��ڽ��� ��ü ������
		bPE.addActionListener(this);
		bPW.addActionListener(this);

		bN = new JButton("��");
		bS = new JButton("��");
		bW = new JButton("��");
		bE = new JButton("��");
		bC = new JButton("���");
		cp = getContentPane();	//�����̳ʸ� ������
		//�����̳� ���� ���(�г�)�� ��� �� ���� ������Ʈ�� �ø� �� ����. �г����� �ٸ� �г��� �ø��°͵� ������
		//Component���� ���� JFrame�� �����Ұ��� getContentPane()�� �ν��Ͻ� �����Ͽ� ����
		
		cp.add(bN, BorderLayout.NORTH); //���� �뽺�� �̹� �����ִ� ��ǥ
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);
		//cp.add(bC, BorderLayout.CENTER);
		cp.add(p); //�����̳ʿ� ������

		setUI();
	}
	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(500, 200);
		setVisible(true);
		setLocation(200, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		/*��ư���� �̺�Ʈ ��� �ٸ��� �ϰ� ������?	
		  Object obj = e.getSource();�ؼ� obj�� � ��ư���� ã�Ƴ��� ��ư����
			   �ٸ��� �������� �� ����(if�� switch��)
			  if(obj==f1):f1��ư�� ������!�ش�ٵ� ����*/ 
		if(obj == bPC){
			pln("�г� ���� Ŭ��");
		}else if(obj == bPE){
			pln("�г� ���� Ŭ��");
		}else{
			pln("�г� ���� Ŭ��");
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		B b = new B();
		b.init();
	}
}
