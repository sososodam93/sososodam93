import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;


class M extends JFrame implements ActionListene {
	JButton b1, b2, b3, b4;
	JButton bN, bS, bW, bE;
	Container cp;
	ImageIcon �̹���1;
	ImageIcon �̹���2;
	ImageIcon �̹���3;
	ImageIcon �̹���5;
	JPanel p;
	
	void init(){
		loadImg();
		b1 = new JButton(�̹���1);
		b2 = new JButton(�̹���2);
		b3 = new JButton(�̹���3);
		b4 = new JButton(�̹���5);
		p.add(b1); p.add(b2); p.add(b3); p.add(b4);

		bN = new JButton("��");
		bS = new JButton("��");
		bW = new JButton("��");
		bE = new JButton("��");
		cp.add(bN, BorderLayout.NORTH);
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		bN.addActionListener(this);
		bS.addActionListener(this);
		bW.addActionListener(this);
		bE.addActionListener(this);

		setLayout(new GridLayout(2, 2)); //��� ��
		cp = getContentPane();
		p = new JPanel();
		loadImg();
		cp.add(b1);cp.add(b2);cp.add(b3);cp.add(b4);
		cp.add(p);
	

		setUI();
	}

	void loadImg();{
		try{
			File f = new File("imgs/�̹���1.png");
			BufferedImage bi = ImageIO.read(f);
			iiAvocado = new ImageIcon(bi);
			iiBanana = new ImageIcon(ImageIO.read(new File("imgs/011-banana.png")));
			iiKiwi = new ImageIcon(ImageIO.read(new File("imgs/014-kiwi.png")));
			iiWatermelon = new ImageIcon(ImageIO.read(new File("imgs/019-watermelon.png")));
			iiGrapefruit = new ImageIcon(ImageIO.read(new File("imgs/047-grapefruit.png")));
			iiApple = new ImageIcon(ImageIO.read(new File("imgs/001-apple.png")));
			iiStrawberry = new ImageIcon(ImageIO.read(new File("imgs/042-strawberry.png")));
			iiBlueberry = new ImageIcon(ImageIO.read(new File("imgs/034-blueberry.png")));
		}catch(IOException ie){
		}
	}


	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(500, 400);
		setVisible(true);
		setLocation(100, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == b1){ 
			JOptionPane.showMessageDialog(null, "�ȳ�");
		}else if(obj == b2){
			m.b2.setText("Ŭ��2");
		}else if(obj == m.b3){
			m.b3.setText("Ŭ��3");
		}else{ 
			m.b4.setText("Ŭ��4");
		}

		System.out.println(b.getLabel());
	}
}
/*void pln(String str){
		System.out.println(str);
	}*/



public static void main(String[] args) {
		M m = new M();
		m.init();
	}
}