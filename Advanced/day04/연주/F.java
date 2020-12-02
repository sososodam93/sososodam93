import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

class F extends JFrame implements ActionListener 
{
	Container cp;
	JButton bN, bS, bW, bE;
	JButton bPF, bPS, bPT, bPFT;
	ImageIcon bg, mt, s, ss;
	JPanel p;

	void init(){
		cp = getContentPane();
		p = new JPanel();
		loadImg();
		p.setLayout(new GridLayout(2,2));
		bPF = new JButton(bg);
		bPS = new JButton(mt);
		bPT = new JButton(s);
		bPFT = new JButton(ss);
		p.add(bPF); p.add(bPS); p.add(bPT); p.add(bPFT);
		bPF.addActionListener(this);
		bPS.addActionListener(this);
		bPT.addActionListener(this);
		bPFT.addActionListener(this);
		bN = new JButton("��");
		bS = new JButton("��");
		bW = new JButton("��");
		bE = new JButton("��");
		cp.add(bN, BorderLayout.NORTH);
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);
		bN.addActionListener(this);
		bS.addActionListener(this);
		bW.addActionListener(this);
		bE.addActionListener(this);

		cp.add(p);
		setUI();
	}
	void setUI(){
		setTitle("������ ������");
		pack();
		setSize(500,300);
		setVisible(true);
		setLocation(200,100);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void loadImg(){
		try{
			bg = new ImageIcon(ImageIO.read(new File("imgs/bridge.png")));
			mt = new ImageIcon(ImageIO.read(new File("imgs/mountain.png")));
			s = new ImageIcon(ImageIO.read(new File("imgs/sea.png")));
			ss = new ImageIcon(ImageIO.read(new File("imgs/skyscrapers.png")));
		}catch(IOException ie){}
	}

			
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bPF){
			JOptionPane.showMessageDialog(null, "������ ������");
		}else if(obj == bPS){
			JOptionPane.showMessageDialog(null, "������ ������");
		}else if(obj == bPT){
			JOptionPane.showMessageDialog(null, "�ٴٷ� ������");
		}else if(obj == bPFT){
			JOptionPane.showMessageDialog(null, "���÷� ������");
		}else if(obj == bN){
			JOptionPane.showMessageDialog(null, "������ ������");
		}else if(obj == bS){
			JOptionPane.showMessageDialog(null, "������ ������");

		}else if(obj == bW){
			String pcs[] = {"��","��","�ٴ�","����"};
			Object answer = JOptionPane.showInputDialog(null,"�������� �����ϼ���", "������", JOptionPane.QUESTION_MESSAGE, null, pcs, pcs[0]);
			if((String)answer == null){
				JOptionPane.showMessageDialog(null, "���þ���");
			}else{
				JOptionPane.showMessageDialog(null,answer);
			}
		}else if(obj == bE){
			int answer = JOptionPane.showConfirmDialog(null, "�����ðڽ��ϱ�?", "����", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.QUESTION_MESSAGE, 
			null);
			if(answer == JOptionPane.YES_OPTION){
				System.exit(-1);
			}else{
				JOptionPane.showMessageDialog(null, "");
			}
		}
	}

	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args) 
	{
		F f = new F();
		f.init();
	}
}
