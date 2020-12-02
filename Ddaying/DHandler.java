import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;

public class DHandler implements ActionListener {
    Ddaying d;
    BakFile bf;
    static int fontSize = 14;
    static String fontStyle = "맑은 고딕";
    static Color c;
    static Color b = new Color(232, 170, 178);

    DHandler(BakFile bf) {
        this.bf = bf;
    }
    DHandler(Ddaying d) {
        this.d = d;
    }
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        BufferedImage pic = null;
        BufferedImage pic2 = null;
        BufferedImage pic3 = null;
        BufferedImage pic4 = null;
        String imgFile, imgFile2, imgFile3, imgFile4 = "";
        Font f1 = null;

        if (obj == d.l1) {
            d.scene1();
            d.b1.addActionListener(this);
            d.b2.addActionListener(this);
            d.ab1.addActionListener(this);
            d.ab2.addActionListener(this);
            d.ab3.addActionListener(this);
            d.memob.addActionListener(this);
            d.dateb.addActionListener(this);
            d.b99.addActionListener(this);
            d.b33.addActionListener(this);
            d.b44.addActionListener(this);
            d.b55.addActionListener(this);
            d.b66.addActionListener(this);
            d.b66.addActionListener(this);
            d.b77.addActionListener(this);
            d.b88.addActionListener(this);
            d.b1010.addActionListener(this);
            d.b1111.addActionListener(this);
            d.b1212.addActionListener(this);
            d.b1313.addActionListener(this);
            d.b1414.addActionListener(this);
            d.b1515.addActionListener(this);
            d.b1616.addActionListener(this);
            d.b1717.addActionListener(this);
            d.b1818.addActionListener(this);
        } else if (obj == d.b1) {
            if (d.i == 0) {
                d.scene3();
                d.i++;
            } else {
                d.p4.setVisible(true);
                d.p.setVisible(false);
                d.p3.setVisible(false);
                d.sp.setVisible(false);
                d.p2.setVisible(false);
            }
        } else if (obj == d.b2) {
            if (d.j == 0) {
                d.scene2();
                d.j++;
            } else {
                d.memob.setVisible(true);
                d.dateb.setVisible(true);
                d.memo.setText("클릭 후 일정입력");
                d.date.setText("클릭 후 날짜입력 ex)YYYYMMDD");
                d.p2.setVisible(true);
                d.p4.setVisible(false);
                d.sp.setVisible(false);
                d.p.setVisible(false);
            }
        } else if (obj == d.ab1) { // 삭제하기
            BakFile bf = new BakFile(d);
            String del[] = new String[10];
            for (int i = 0; i < d.v.size(); i++) {
                del[i] = d.v.get(i);
            }
            try {
                Object sel1 = JOptionPane.showInputDialog(null, "삭제할 일정을 골라주세요.", "삭제", JOptionPane.QUESTION_MESSAGE, null, del, del[0]);
                String sel2 = (String) sel1;
                System.out.println(sel2);
                if (sel2 == null) {} else {
                    if (sel2.contains(" D+") == true) {
                        int idx = sel2.indexOf(" D+");
                        String mapKey = sel2.substring(0, idx);
                        System.out.println(mapKey);
                        bf.ht.remove(mapKey);
                        bf.save();
                        f1 = new Font(fontStyle, Font.BOLD, fontSize);
                        for (int i = 0; i < d.v.size(); i++)
                            d.rb[i].setVisible(false);
                        d.changeArray();
                        d.p3.removeAll();
                        d.mkButton2(f1, c, b);
                    } else {
                        int idx = sel2.indexOf(" D-");
                        String mapKey = sel2.substring(0, idx);
                        System.out.println(mapKey);
                        bf.ht.remove(mapKey);
                        bf.save();
                        f1 = new Font(fontStyle, Font.BOLD, fontSize);
                        for (int i = 0; i < d.v.size(); i++)
                            d.rb[i].setVisible(false);
                        d.changeArray();
                        d.p3.removeAll();
                        d.mkButton2(f1, c, b);
                    }
                }
                d.p2.setVisible(false);
                d.p4.setVisible(false);
                d.sp.setVisible(true);
                d.p.setVisible(true);
            } catch (HeadlessException he) {}
        } else if (obj == d.ab2) {
            try {
                String k = d.memo.getText();
                Object kk = (Object) k;
                int v = Integer.parseInt(d.date.getText());
                if (d.date.getText().length() == 8) {
                    d.memo.setText("일정내용 입력창");
                    d.date.setText("YYYY.MM.DD");
                    BakFile bf = new BakFile(d);
                    bf.ht.put(k, v);
                    bf.save();
                    f1 = new Font(fontStyle, Font.BOLD, fontSize);
                    for (int q = 0; q < d.v.size(); q++)
                        d.rb[q].setVisible(false);
                    d.changeArray();
                    d.p3.removeAll();
                    d.mkButton2(f1, c, b);
                    d.p2.setVisible(false);
                    d.p4.setVisible(false);
                    d.sp.setVisible(true);
                    d.p.setVisible(true);
                } else if (bf.ht.containsKey(k) == true) {
                    JOptionPane.showMessageDialog(null, "일정 내용이 이미 존재합니다.");
                } else if (d.date.getText().length() > 8 || d.date.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "날짜 양식이 맞지 않아요.");
                }
            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "날짜에 숫자만 입력해주세요.");
            }
        } else if (obj == d.ab3) { //add .취소 버튼
            d.p2.setVisible(false);
            d.p4.setVisible(false);
            d.sp.setVisible(true);
            d.p.setVisible(true);
        } else if (obj == d.memob) {
            System.out.println("일정입력창 클릭");
            d.memob.setVisible(false);
            d.memo.setText("");
        } else if (obj == d.dateb) {
            System.out.println("날짜입력창 클릭");
            d.dateb.setVisible(false);
            d.date.setText("");
        } else if (obj == d.b99) {
            d.p4.setVisible(false);
            d.p2.setVisible(false);
            d.sp.setVisible(true);
            d.p3.setVisible(true);
            d.p.setVisible(true);
        } else if (obj == d.b33) {
            fontSize = 14;
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b44) {
            fontSize = 20;
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b55) {
            System.out.println(fontSize);
            fontSize = 30;
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b66) { //글씨체1 - 지마켓산스
            fontStyle = "G마켓 산스 TTF Medium";
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b77) { //글씨체2 - 마포금빛나루
            fontStyle = "Mapo금빛나루";
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b88) { //글씨체3 - 위메프
            fontStyle = "위메프 Bold";
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            for (int i = 0; i < d.v.size(); i++)
                d.rb[i].setVisible(false);
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b1010) { //텍스트 색상 변경
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            c = Color.white;
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b1111) { //텍스트 색상 변경
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            c = Color.BLACK;
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b1212) { //텍스트 색상 변경
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            c = new Color(41, 41, 41);
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            d.mkButton2(f1, c, b);
        } else if (obj == d.b1313) { //Dday창 색상 변경
            b = new Color(41, 41, 41);
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            d.mkButton2(f1, c, b);
            d.p3.setVisible(true);
        } else if (obj == d.b1414) { //Dday창 색상 변경
            b = new Color(206, 206, 206);
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            d.mkButton2(f1, c, b);
            d.p3.setVisible(true);
        } else if (obj == d.b1515) { //Dday창 색상 변경
            b = new Color(232, 170, 178);
            for (int i = 0; i < d.v.size(); i++) {
                d.rb[i].setVisible(false);
            }
            d.p3.removeAll();
            f1 = new Font(fontStyle, Font.BOLD, fontSize);
            d.mkButton2(f1, c, b);
            d.p3.setVisible(true);
        } else if (obj == d.b1616) { //배경색상
            d.getContentPane().setBackground(new Color(0, 0, 0));
            d.p.setBackground(new Color(0, 0, 0));
            d.sp.getViewport().setBackground(new Color(0, 0, 0));
			d.title.setForeground(Color.WHITE);
            imgFile3 = "imgs/setting-white.png";
            imgFile4 = "imgs/add-white.png";
            try {
                pic3 = ImageIO.read(new File(imgFile3));
                pic4 = ImageIO.read(new File(imgFile4));
            } catch (IOException e1) {
                System.out.println("이미지 없음!");
            }
            d.q1.setImage(pic3);
            d.q2.setImage(pic4);
            d.p3.setVisible(true);
        } else if (obj == d.b1717) { //배경색상
            d.getContentPane().setBackground(new Color(255, 255, 255));
            d.p.setBackground(new Color(255, 255, 255));
            d.sp.getViewport().setBackground(new Color(255, 255, 255));
			d.title.setForeground(new Color(26,26,26));
            imgFile = "imgs/settings.png";
            imgFile2 = "imgs/add.png";
            try {
                pic = ImageIO.read(new File(imgFile));
                pic2 = ImageIO.read(new File(imgFile2));
            } catch (IOException e1) {
                System.out.println("이미지 없음!");
            }
            d.q1.setImage(pic);
            d.q2.setImage(pic2);
            d.p3.setVisible(true);
        } else if (obj == d.b1818) { //배경색상
            d.getContentPane().setBackground(new Color(255, 179, 177));
            d.p.setBackground(new Color(255, 179, 177));
            d.sp.getViewport().setBackground(new Color(255, 179, 177));
			d.title.setForeground(new Color(26,26,26));
            d.p3.setVisible(true);
            imgFile = "imgs/settings.png";
            imgFile2 = "imgs/add.png";
            try {
                pic = ImageIO.read(new File(imgFile));
                pic2 = ImageIO.read(new File(imgFile2));
            } catch (IOException e1) {
                System.out.println("이미지 없음!");
            }
            d.q1.setImage(pic);
            d.q2.setImage(pic2);
            d.p3.setVisible(true);
        }
    }
}