import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//BorderLayout ���������̾ƿ�(��� ������)
class T extends JFrame implements ActionListener { 
	//JFrame���� ���� ��ӹ޾Ƽ� �׼Ǹ����ʸ� �����϶�
	JButton bN, bS, bW, bE, bC; //��ư����
	JButton bP1, bP2, bP3, bP4; //b�� ��ư p�� �г�c�� ����
	Container cp; //�����̳�
	JPanel p;

	void init(){
		p = new JPanel(); //�г��� ���Ͱ� �����Ǿ��ִ�.,�г��� ���
		//�� ������ �ϳ� �̻��� ������Ʈ�� �ְ� ������ �г� ���
		p.setLayout(new BorderLayout()); //�г��� ��ġ����� ����
		bP1 = new JButton("�г� ����"); //bPC��ư�� ����ϱ����� �������ְ� ��ư�� ��Ÿ���� �۾� ����
		bP2 = new JButton("�г� ����");
		bP3 = new JButton("�г� ����");
		bP4= new JButton("�г� 4��");

        p.add(bP1, BorderLayout.CENTER);//�гο� ���ϼ� �ְ� ��ġ �����ؼ� �߰����ش�
		p.add(bP2, BorderLayout.EAST);
		p.add(bP3, BorderLayout.WEST);
		p.add(bP4, BorderLayout.NORTH);
		bP1.addActionListener(this);//������ �����ϵ��� �׼Ǹ����ʸ� �߰����ش� this>>???( )
		bP2.addActionListener(this);
		bP3.addActionListener(this);
		bP4.addActionListener(this);


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
		if(obj == bP1){
			pln("�г� ���� Ŭ��");
		}else if(obj == bP2){
			pln("�г� ���� Ŭ��");
		}else{
			pln("�г� ���� Ŭ��");
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		T t = new T();
		t.init();
	}
}
