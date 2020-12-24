package xyz.chengzi.aeroplanechess.model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Save {
	private String saveName;
	private static int saveCnt = 0;
	private final int saveID;
	private File saveFile;
	private static List<Save> saves = new ArrayList<Save>();
	
	private static File saveListFile;
	public static void main(String[] args) throws IOException {
		saveListFile = new File("src"+File.separator+"saves"+File.separator+"saveList.txt");
		if(!saveListFile.exists()) {
			try {
				saveListFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(saveListFile)));
		String str = null;
		while((str = bfr.readLine())!= null) {
			System.out.println(str);
			saves.add(new Save(str));
		}
	}
	
	
	public Save(String saveName) {
		saveCnt++;
		saveID = saveCnt;
		this.saveFile = new File("src"+File.separator+"saves"+File.separator+saveName+".txt");
		if(!saveFile.exists()) {
			System.out.println("DIDNDSF");
		}
		
	}
}
