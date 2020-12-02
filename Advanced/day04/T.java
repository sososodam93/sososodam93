import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//BorderLayout 보더레이이아웃(어디에 놓을지)
class T extends JFrame implements ActionListener { 
	//JFrame으로 부터 상속받아서 액션리스너를 시행하라
	JButton bN, bS, bW, bE, bC; //버튼지정
	JButton bP1, bP2, bP3, bP4; //b는 버튼 p는 패널c는 센터
	Container cp; //컨테이너
	JPanel p;

	void init(){
		p = new JPanel(); //패널은 센터가 생략되어있다.,패널은 쟁반
		//한 영역에 하나 이상의 컴포넌트를 넣고 싶을때 패널 사용
		p.setLayout(new BorderLayout()); //패널의 배치방식을 설정
		bP1 = new JButton("패널 센터"); //bPC버튼을 사용하기위해 생성해주고 버튼안 나타내줄 글씨 설정
		bP2 = new JButton("패널 동쪽");
		bP3 = new JButton("패널 서쪽");
		bP4= new JButton("패널 4쪽");

        p.add(bP1, BorderLayout.CENTER);//패널에 보일수 있게 위치 지정해서 추가해준다
		p.add(bP2, BorderLayout.EAST);
		p.add(bP3, BorderLayout.WEST);
		p.add(bP4, BorderLayout.NORTH);
		bP1.addActionListener(this);//동작을 시행하도록 액션리스너를 추가해준다 this>>???( )
		bP2.addActionListener(this);
		bP3.addActionListener(this);
		bP4.addActionListener(this);


		bN = new JButton("북");
		bS = new JButton("남");
		bW = new JButton("서");
		bE = new JButton("동");
		bC = new JButton("가운데");
		cp = getContentPane();	//컨테이너를 리턴함
		//컨테이너 위에 쟁반(패널)을 얹고 그 위에 컴포넌트를 올릴 수 있음. 패널위에 다른 패널을 올리는것도 가능함
		//Component들을 직접 JFrame에 부착불가라 getContentPane()로 인스턴스 생성하여 부착
		
		cp.add(bN, BorderLayout.NORTH); //보더 노스는 이미 잡혀있는 좌표
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);
		//cp.add(bC, BorderLayout.CENTER);
		cp.add(p); //컨테이너에 더해줌

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
		/*버튼별로 이벤트 결과 다르게 하고 싶을때?	
		  Object obj = e.getSource();해서 obj가 어떤 버튼인지 찾아내고 버튼마다
			   다르게 설정해줄 수 있음(if나 switch로)
			  if(obj==f1):f1버튼을 누르면!해당바디를 실행*/ 
		if(obj == bP1){
			pln("패널 센터 클릭");
		}else if(obj == bP2){
			pln("패널 동쪽 클릭");
		}else{
			pln("패널 서쪽 클릭");
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
