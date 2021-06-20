package savefile;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SaveFile {
	ArrayList<String> transcribedSaveFile;
	
	public SaveFile() {
	
	}
	
	public void readFile(String path) {
		transcribedSaveFile = new ArrayList<String>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new FileReader(path)); 				//specify the path to save file
			String line = bfr.readLine();
			while (line != null) {
				transcribedSaveFile.add(line);
				System.out.println(line);
				line = bfr.readLine();
			}
			bfr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTranscribedSaveFile() {
		return transcribedSaveFile;
	}
}

