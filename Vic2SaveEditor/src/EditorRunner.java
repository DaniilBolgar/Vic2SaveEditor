import java.util.Scanner;

import savefile.SaveFile;

public class EditorRunner {
	public static void main(String[] args) {
		String param;
		if (args.length == 0) {
			param = System.in.toString();
		} else {
			param = args[0];
		}
		Scanner console = new Scanner(param);
		SaveFile sf = new SaveFile();
		String path = console.nextLine();
		sf.readFile(path);
		console.close();
	}
}
