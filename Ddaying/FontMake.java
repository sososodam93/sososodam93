import java.awt.*;
import java.io.*;

public class FontMake 
{
	public void fontChange(GraphicsEnvironment ge){
		String path = "Font/GmarketSansTTF/GmarketSansTTFMedium.ttf";
		String path1 = "Font/Jalpullineun-oneul/잘풀리는오늘 Medium.ttf";
		String path2 = "Font/MapoGoldenPier/MapoGoldenPier.ttf";
		String path3 = "Font/Wemakeprice_TTF/Wemakeprice-Bold.ttf";
		try {
		Font recipe = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(12f);
		Font recipe1 = Font.createFont(Font.TRUETYPE_FONT, new File(path1)).deriveFont(12f);
		Font recipe2 = Font.createFont(Font.TRUETYPE_FONT, new File(path2)).deriveFont(12f);
		Font recipe3 = Font.createFont(Font.TRUETYPE_FONT, new File(path3)).deriveFont(12f);

		ge.registerFont(recipe);
		ge.registerFont(recipe1);
		ge.registerFont(recipe2);
		ge.registerFont(recipe3);

//위에서 생성한 폰트를 집어 넣기

		}catch(FontFormatException e){
		e.printStackTrace();
		}catch(IOException e){
		e.printStackTrace();
		}
	}
}
