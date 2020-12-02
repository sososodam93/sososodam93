import java.util.*;
import java.io.*;

public class BakFile {
    LinkedHashMap < String, Integer > ht = new LinkedHashMap < String, Integer > ();
    Ddaying dd;

    BakFile(Ddaying dd) {
        this.dd = dd;
        bakText();
    }
    void bakText() {
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        try {
            fr = new FileReader("bak.txt");
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line != null) line = line.trim();
                if (line.length() != 0) {
                    int idx1 = line.lastIndexOf("\\");
                    int idx2 = line.indexOf("\\");
                    String mapKey = line.substring(0, idx2);
                    String mapValue1 = line.substring(idx1 + 1);
                    int mapValue2 = Integer.parseInt(mapValue1);
                    ht.put(mapKey, mapValue2);
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println("파일이 존재하지 않습니다.");
        } catch (IOException ie) {} finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ie) {}
        }
    }
    public void save() {
        DHandler dh = new DHandler(this);
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("bak.txt");
            pw = new PrintWriter(fw, true);
            Set < String > keys = ht.keySet();
            for (String key: keys)
                pw.println(key + "\\" + ht.get(key));
            System.out.println("bak.txt에 일정 저장 완료");
        } catch (IOException ie) {} finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ie) {}
        }
    }
    void out() {
        Set < String > keys = ht.keySet();
        for (String key: keys) System.out.println("key: " + key + ", value: " + ht.get(key));
    }
}