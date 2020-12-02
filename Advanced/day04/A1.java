import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //�ڹٿ��� gui������ ����ϴ� api���


//https://blog.naver.com/springg92/222056902122 �����α�

//�����̳�(Container):Frame(�տ� j�� ������ Swing��)
//�ٸ���ҵ��� ���� �� �ִ� �׸����� ����
//�ٸ�������Ʈ�� ������ �� �ִ� GUI������Ʈ
//���������� ������ ���� �ְ� �ٸ� �����̳ʿ� ���Ե� ���� ����
//������ ȭ�鿡 �ڱ� �ڽ��� ����ϴ� �����̳ʷδ� JFrame,JDialog,JApplet�� ����

//������Ʈ(Componer):�����̳ʿ� �ö���°͵�,��ɵ��,�����̳ʿ� ���ԵǾ��
//ȭ�鿡 ��µ� �� �ִ� GUI��ü

//-�۾�����
//1.�����̳ʿ� ��ü ����(ū ��ȭ�� ȭ���� ��ٰ� ����)
//2.��ġ ����� �����̳ʿ� ����(���̾ƿ� ����)
//3.������Ʈ ��ü ����(��ȭ���� �ٿ��� ��ҵ�)
//4.������ ��ġ ��Ŀ� ���� �����̳ʿ� ������Ʈ ��ġ(��ҵ��� ��ȭ���� �ٿ��ֱ�)
//5.������Ʈ�� ���콺�� Ű���忡 ���� �̺�Ʈ ó��


class A1 extends JFrame { //Container 
	class A1Handler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//System.out.println("�̺�Ʈ ����");
			b.setText("��ư Ŭ����!!( by ������Ŭ����1 )");

			//JButton bb = (JButton)e.getSource();
			//bb.setText("��ư Ŭ����!!( by ������Ŭ����2 )");
		}
	}

	JButton b; //UI Component
	void init(){
		b = new JButton("�ڹ��� ��ư");
		add(b); //
        ActionListener listener = new A1Handler();
        b.addActionListener(listener);

		setUI();
	}
	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(300, 200); //ȭ�� ������ ����*����
		setVisible(true); //â ǥ�� ����(�ʼ�,������ ȭ�鿡 ��Ÿ�����ʴ´�.
		setLocation(200, 100);//���������� 200�ȼ�,�Ʒ��������� 100�ȼ�
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Xǥ�ø� ���� â�� �ݾƵ� �ܼ��� ���������ʴµ�,�� �ڵ带 �ۼ����ָ� �ֵܼ� ���� ������.
	}
	public static void main(String args[]){
		A1 a1 = new A1();
		a1.init();
	}
}