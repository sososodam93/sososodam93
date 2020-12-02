import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //자바에서 gui구현을 담당하는 api모듬


//https://blog.naver.com/springg92/222056902122 참고블로그

//컨테이너(Container):Frame(앞에 j가 붙으면 Swing임)
//다른요소들을 담을 수 있는 그릇같은 존재
//다른컴포넌트를 포함할 수 있는 GUI컴포넌트
//독립적으로 존재할 수도 있고 다른 컨테이너에 포함될 수도 있음
//스스로 화면에 자기 자신을 출력하는 컨테이너로는 JFrame,JDialog,JApplet이 있음

//컴포넌트(Componer):컨테이너에 올라오는것들,기능덩어리,컨테이너에 포함되어야
//화면에 출력될 수 있는 GUI객체

//-작업순서
//1.컨테이너에 객체 생성(큰 도화지 화나를 깐다고 생각)
//2.배치 방식을 컨테이너에 세팅(레이아웃 설정)
//3.컴포넌트 객체 생성(도화지에 붙여줄 요소들)
//4.지정된 배치 방식에 따라 컨테이너에 컴포넌트 배치(요소들을 도화지에 붙여주기)
//5.컴포넌트에 마우스나 키보드에 대한 이벤트 처리


class A1 extends JFrame { //Container 
	class A1Handler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//System.out.println("이벤트 감지");
			b.setText("버튼 클릭됨!!( by 유명내부클래스1 )");

			//JButton bb = (JButton)e.getSource();
			//bb.setText("버튼 클릭됨!!( by 유명내부클래스2 )");
		}
	}

	JButton b; //UI Component
	void init(){
		b = new JButton("자바의 버튼");
		add(b); //
        ActionListener listener = new A1Handler();
        b.addActionListener(listener);

		setUI();
	}
	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(300, 200); //화면 사이즈 가로*세로
		setVisible(true); //창 표시 설정(필수,없으면 화면에 나타나지않는다.
		setLocation(200, 100);//오른쪽으로 200픽셀,아래방향으로 100픽셀
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//X표시를 눌러 창을 닫아도 콘솔은 정지되지않는데,이 코드를 작성해주면 콘솔도 같이 꺼진다.
	}
	public static void main(String args[]){
		A1 a1 = new A1();
		a1.init();
	}
}