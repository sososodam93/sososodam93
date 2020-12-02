import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;

class Ddaying extends JFrame {
    JButton b1, b2, b3, b33, b44, b55, b66, b77, b88, b99, b1010, b1111, b1212, b1313, b1414, b1515, b1616, b1717, b1818;
    JButton l1, cp1;
    JButton t1, title, title1;
    JButton ab1, ab2, ab3, memob, dateb;
    Container cp;
    JLayeredPane p3;
    JPanel p, p1, p2, p4;
    JScrollPane sp;
    BufferedImage pic, pic2, pic3, pic4;
    ImageIcon imgI1, q1, q2;
    ImageIcon i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, ii1, ii2, ii3;
    Color pink = new Color(255, 91, 162);
    Color white = new Color(255, 255, 255);
    Color black = new Color(0, 0, 0);
    Color lpink = new Color(232, 193, 191);
    Color testC = new Color(232, 170, 178);
    RoundedButton rb[] = new RoundedButton[10];
    Vector < String > v = new Vector < String > ();
    TextArea memo = new TextArea("일정내용 입력창", 2, 35);
    TextField date = new TextField("YYYY.MM.DD", 35);
    Border NORMAL_BORDER = BorderFactory.createLineBorder(Color.BLACK, 2);
    FontMake fm = new FontMake();
    int i;
    int j;

    Font TitleF = new Font("위메프 Bold", Font.BOLD, 25);
    Font f1 = new Font("맑은 고딕", Font.BOLD, 14);

    Ddaying() {
        changeArray();

        mkImg();
        p1 = new JPanel();

        b1 = new JButton();
        b2 = new JButton();
        ab1 = new JButton(ii1); //디데이입력창
        ab2 = new JButton(ii2); //디데이입력창
        ab3 = new JButton(ii3); //디데이입력창
        memob = new JButton("클릭 후 일정입력"); //스케줄 입력창
        dateb = new JButton("클릭 후 날짜입력 ex)YYYYMMDD"); //날짜 입력창
        b33 = new JButton(i1); //제일작은텍스트크기버튼
        b44 = new JButton(i2); //중간텍스트크기버튼
        b55 = new JButton(i3); //제일큰텍스트크기버튼
        b66 = new JButton(i8); //텍스트 종류
        b77 = new JButton(i9); //텍스트 종류
        b88 = new JButton(i10); //텍스트 종류
        b99 = new JButton(i7); //설정창 세이브버튼
        b1010 = new JButton(i12); //텍스트 컬러
        b1111 = new JButton(i11);
        b1212 = new JButton(i5);
        b1313 = new JButton(i5); //디데이컬러
        b1414 = new JButton(i4);
        b1515 = new JButton(i13);
        b1616 = new JButton(i11); // 배경색상
        b1717 = new JButton(i12);
        b1818 = new JButton(i14);

        init();
    }
    void init() {
        //container와 panel 선언
        cp = getContentPane();
        p = new JPanel();
        p2 = new JPanel();
        p3 = new JLayeredPane();
        sp = new JScrollPane(p3);
        p4 = new JPanel();
        p.setLayout(null);
        p3.setLayout(new GridLayout(20, 1, 0, 3));

        l1 = new JButton(imgI1);
        make();
        DHandler dh = new DHandler(this);
        l1.addActionListener(dh);

        //container에 panel 추가
        p1.add(l1);
        cp.add(p1);

        setUI();
    }
    void changeArray() {
        BakFile bf = new BakFile(this);
        Dday dd = new Dday(this);
        Set < String > keys = bf.ht.keySet();
        v.clear();
        for (String key: keys) {
            String dday = String.valueOf(bf.ht.get(key));
            String year = dday.substring(0, 4);
            String month = dday.substring(4, 6);
            String day = dday.substring(6, 8);
            int _year = Integer.parseInt(year);
            int _month = Integer.parseInt(month);
            int _day = Integer.parseInt(day);
            if (dd.calcdate(_year, _month, _day) == 0) {
                v.add(key + " D-Day ");
            } else if (dd.calcdate(_year, _month, _day) > 0) {
                int y = dd.calcdate(_year, _month, _day) + 1;
                v.add(key + " D+" + y);
            } else {
                v.add(key + " D" + dd.calcdate(_year, _month, _day));
            }
        }
    }
    void mkButton1(Font f) {
        title = new JButton("Ddaying");
        title.setBackground(testC);
        title.setFont(TitleF);
        title.setFocusPainted(false);
        title.setBorderPainted(false);
        title.setContentAreaFilled(false);
        title.setOpaque(false);
        p3.add(title);
        for (int i = 0; i < v.size(); i++) {
            rb[i] = new RoundedButton(v.get(i));
            rb[i].setPreferredSize(new Dimension(370, 80));
            rb[i].setBackground(testC);
            rb[i].setFont(f);
            rb[i].setHorizontalAlignment(RoundedButton.RIGHT);
            rb[i].setFocusPainted(false);
            rb[i].setContentAreaFilled(true);
            rb[i].setBorder(NORMAL_BORDER);
            rb[i].setOpaque(false);

            p3.add(rb[i]);
        }
    }
    void mkButton2(Font f, Color c, Color b) {
        title = new JButton("Ddaying");
        title.setBackground(testC);
        title.setFont(TitleF);
        title.setFocusPainted(false);
        title.setBorderPainted(false);
        title.setContentAreaFilled(false);
        title.setOpaque(false);
        p3.add(title);
        for (int i = 0; i < v.size(); i++) {
            rb[i] = new RoundedButton(v.get(i));
            rb[i].setPreferredSize(new Dimension(370, 80));
            rb[i].setBackground(b);
            rb[i].setForeground(c);
            rb[i].setFont(f);
            rb[i].setHorizontalAlignment(RoundedButton.RIGHT);
            rb[i].setFocusPainted(false);
            rb[i].setContentAreaFilled(true);
            rb[i].setBorder(NORMAL_BORDER);
            rb[i].setOpaque(false);

            p3.add(rb[i]);
            p3.setVisible(true);
        }
    }
    void mkImg() {
        i1 = new ImageIcon(getClass().getResource("imgs/1.png"));
        i2 = new ImageIcon(getClass().getResource("imgs/2.png"));
        i3 = new ImageIcon(getClass().getResource("imgs/3.png"));
        i4 = new ImageIcon(getClass().getResource("imgs/gray1.png"));
        i5 = new ImageIcon(getClass().getResource("imgs/gray2.png"));
        i6 = new ImageIcon(getClass().getResource("imgs/gray3.png"));
        i7 = new ImageIcon(getClass().getResource("imgs/save.png"));
        i8 = new ImageIcon(getClass().getResource("imgs/지마켓산스.png"));
        i9 = new ImageIcon(getClass().getResource("imgs/마포금빛나루.png"));
        i10 = new ImageIcon(getClass().getResource("imgs/위메프.png"));
        i11 = new ImageIcon(getClass().getResource("imgs/블랙.png"));
        i12 = new ImageIcon(getClass().getResource("imgs/흰색.png"));
        i13 = new ImageIcon(getClass().getResource("imgs/코랄디데이.png"));
        i14 = new ImageIcon(getClass().getResource("imgs/코랄배경.png"));
        ii1 = new ImageIcon(getClass().getResource("imgs/addDelete.png"));
        ii2 = new ImageIcon(getClass().getResource("imgs/addSave.png"));
        ii3 = new ImageIcon(getClass().getResource("imgs/addCancel.png"));
    }
    void scene3() {
        Image image;
        Color b = new Color(0xFFFFFF);
        JButton b11, b22, b1919, b2020, b2121, JB;
        p4.setLayout(null);

        JB = new JButton();
        JB.setHorizontalAlignment(JButton.LEFT);

        p4.setVisible(true);
        p.setVisible(false);
        sp.setVisible(false);
        p2.setVisible(false);

        b11 = new JButton("Text Size");
        b22 = new JButton("Text Style");
        b1919 = new JButton("Text Color");
        b2020 = new JButton("D-day Color");
        b2121 = new JButton("BackGround Color");

        b11.setBorderPainted(false);
        b22.setBorderPainted(false);
        b99.setBorderPainted(false);
        b1010.setBorderPainted(true);
        b1111.setBorderPainted(true);
        b1212.setBorderPainted(true);
        b1313.setBorderPainted(true);
        b1414.setBorderPainted(true);
        b1515.setBorderPainted(true);
        b1616.setBorderPainted(true);
        b1717.setBorderPainted(true);
        b1818.setBorderPainted(true);
        b1919.setBorderPainted(false);
        b2020.setBorderPainted(false);
        b2121.setBorderPainted(false);

        b11.setFocusPainted(false);
        b22.setFocusPainted(false);
        b33.setFocusPainted(false);
        b44.setFocusPainted(false);
        b55.setFocusPainted(false);
        b66.setFocusPainted(false);
        b77.setFocusPainted(false);
        b88.setFocusPainted(false);

        b99.setFocusPainted(false);
        b1010.setFocusPainted(true);
        b1111.setFocusPainted(true);
        b1212.setFocusPainted(true);
        b1313.setFocusPainted(true);
        b1414.setFocusPainted(true);
        b1515.setFocusPainted(true);
        b1616.setFocusPainted(true);
        b1717.setFocusPainted(true);
        b1818.setFocusPainted(true);
        b1919.setFocusPainted(false);
        b2020.setFocusPainted(false);
        b2121.setFocusPainted(false);

        setBackground(new Color(255, 255, 255));
        b11.setBackground(Color.white);
        b22.setBackground(Color.white);
        b33.setBackground(Color.white);
        b44.setBackground(Color.white);
        b55.setBackground(Color.white);
        b66.setBackground(Color.white);
        b77.setBackground(Color.white);
        b88.setBackground(Color.white);
        b99.setBackground(Color.white);
        b1010.setBackground(Color.white);
        b1111.setBackground(Color.white);
        b1212.setBackground(Color.white);
        b1313.setBackground(Color.white);
        b1414.setBackground(Color.white);
        b1515.setBackground(Color.white);
        b1616.setBackground(Color.white);
        b1717.setBackground(Color.white);
        b1818.setBackground(Color.white);
        b1919.setBackground(Color.white);
        b2020.setBackground(Color.white);
        b2121.setBackground(Color.white);

        b11.setOpaque(false);
        b22.setOpaque(false);
        b33.setOpaque(false);
        b44.setOpaque(false);
        b55.setOpaque(false);
        b66.setOpaque(false);
        b77.setOpaque(false);
        b88.setOpaque(false);
        b99.setOpaque(false);
        b1010.setOpaque(false);
        b1111.setOpaque(false);
        b1212.setOpaque(false);
        b1313.setOpaque(false);
        b1414.setOpaque(false);
        b1515.setOpaque(false);
        b1616.setOpaque(false);
        b1717.setOpaque(false);
        b1818.setOpaque(false);
        b1919.setOpaque(false);
        b2020.setOpaque(false);
        b2121.setOpaque(false);

        b11.setBounds(0, 20, 137, 20); //설정창 텍스트 사이즈 명칭
        b22.setBounds(0, 140, 140, 20); //설정창 텍스트 스타일 명칭
        b33.setBounds(40, 50, 70, 70); //텍스트1크기
        b44.setBounds(162, 50, 70, 70); //텍스트2크기
        b55.setBounds(285, 50, 70, 70); //텍스트3크기
        b66.setBounds(40, 170, 70, 70); //글씨체1
        b77.setBounds(162, 170, 70, 70); //글씨체2
        b88.setBounds(285, 170, 70, 70); //글씨체3
        b99.setBounds(160, 650, 70, 30); //저장버튼
        b1010.setBounds(40, 300, 70, 70); //폰트컬러1
        b1111.setBounds(162, 300, 70, 70); //폰트컬러2
        b1212.setBounds(285, 300, 70, 70); //폰트컬러3
        b1313.setBounds(40, 430, 70, 70); //디데이창 색상1
        b1414.setBounds(162, 430, 70, 70); //디데이창 색상2
        b1515.setBounds(285, 430, 70, 70); //디데이창 색상3
        b1616.setBounds(40, 560, 70, 70); //배경 색상1
        b1717.setBounds(162, 560, 70, 70); //배경 색상2
        b1818.setBounds(285, 560, 70, 70); //배경 색상3
        b1919.setBounds(0, 270, 140, 20); //설정창 텍스트 컬러 명칭 
        b2020.setBounds(0, 400, 145, 20); //설정창 디데이 컬러 명칭  
        b2121.setBounds(0, 530, 185, 20); //설정창 백그라운드컬러 명칭 

        p4.add(b11);
        p4.add(b22);
        p4.add(b33);
        p4.add(b44);
        p4.add(b55);
        p4.add(b66);
        p4.add(b77);
        p4.add(b88);
        p4.add(b99);
        p4.add(b1010);
        p4.add(b1111);
        p4.add(b1212);
        p4.add(b1313);
        p4.add(b1414);
        p4.add(b1515);
        p4.add(b1616);
        p4.add(b1717);
        p4.add(b1818);
        p4.add(b1919);
        p4.add(b2020);
        p4.add(b2121);
        cp.add(p4);

        p4.setVisible(true);
        p.setVisible(false);
        p2.setVisible(false);
    }
    void scene2() {
        title1 = new JButton("Ddaying");
        title1.setBackground(testC);
        title1.setFont(TitleF);
        title1.setFocusPainted(false);
        title1.setBorderPainted(false);
        title1.setContentAreaFilled(false);
        title1.setOpaque(false);

        JLabel ml = new JLabel("Schedule");
        JLabel dl = new JLabel("Date");
        p.setVisible(false);
        p4.setVisible(false);
        sp.setVisible(false);
        p2.setVisible(true);

        p2.setLayout(null);
        title1.setBounds(97, 20, 200, 40);
        ml.setBounds(170, 88, 137, 16);
        memo.setBounds(10, 112, 375, 59);
        memob.setBounds(10, 112, 375, 59);
        dl.setBounds(183, 178, 137, 16);
        date.setBounds(10, 194, 375, 23);
        dateb.setBounds(10, 194, 375, 23);
        ab1.setBounds(8, 647, 121, 30);
        ab2.setBounds(145, 647, 121, 30);
        ab3.setBounds(282, 647, 121, 30);

        p2.add(title1);
        p2.add(ml);
        p2.add(memob);
        p2.add(memo);
        p2.add(dl);
        p2.add(dateb);
        p2.add(date);

        p2.add(ab1);
        p2.add(ab2);
        p2.add(ab3);
        cp.add(p2);

        ab1.setBorderPainted(false);
        ab2.setBorderPainted(false);
        ab3.setBorderPainted(false);

        ab1.setFocusPainted(false);
        ab2.setFocusPainted(false);
        ab3.setFocusPainted(false);
        memob.setFocusPainted(false);
        dateb.setFocusPainted(false);

        ab1.setBackground(Color.white);
        ab2.setBackground(Color.white);
        ab3.setBackground(Color.white);
        memob.setBackground(Color.white);
        dateb.setBackground(Color.white);

        ab1.setOpaque(false);
        ab2.setOpaque(false);
        ab3.setOpaque(false);
    }
    void scene1() {
        p1.setVisible(false);

        //button 선언
        b1 = new JButton(q1);
        b2 = new JButton(q2);
        mkButton1(f1);

        p.add(b1);
        p.add(b2);

        cp.add(sp);
        cp.add(p);

        //JButton 외곽선 제거
        b1.setBorderPainted(false);
        b2.setBorderPainted(false);

        //클릭시 생기는 테두리 제거
        b1.setFocusPainted(false);
        b2.setFocusPainted(false);

        //bg컬러 설정
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);

        //투명하게
        b1.setOpaque(false);
        b2.setOpaque(false);

        //위치 지정(임시)
        b1.setBounds(0, 620, 120, 80);
        b2.setBounds(280, 620, 120, 80);

    }
    void make() {
        imgI1 = new ImageIcon(getClass().getResource("imgs/intro.gif"));
        q1 = new ImageIcon(getClass().getResource("imgs/settings.png"));
        q2 = new ImageIcon(getClass().getResource("imgs/add.png"));
        l1.setIcon(imgI1);
    }
    void setUI() {
        p3.setSize(411, 701);
        p2.setSize(411, 731);
        p.setSize(411, 731);
        sp.setSize(411, 620);
        sp.getViewport().setBackground(null); //JScrollPane 배경색 처리
        p.setBackground(lpink);
        sp.getViewport().setBackground(lpink);
        sp.setBorder(BorderFactory.createEmptyBorder()); //JScrollPane 외곽선 제거
        getContentPane().setBackground(pink);
        l1.setSize(411, 731); // 배경
        l1.setLocation(0, 0);
        setTitle("D-Daying 때잉"); //타이틀
        setSize(411, 731); //화면 사이즈
        setVisible(true); //보여지는 여부
        setLocationRelativeTo(null); //항상 가운데 위치
        setResizable(false); //resizable 여부
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //클릭시 창닫기

        //폰트 변경
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fm.fontChange(ge);
    }
    public static void main(String args[]) {
        Ddaying d = new Ddaying();
    }
}