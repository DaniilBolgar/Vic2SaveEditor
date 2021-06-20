import java.util.Scanner;

public class EditorRunner {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		SaveFile sf = new SaveFile();
		String path = console.nextLine();
		sf.readFile(path);
		console.close();
	}
}
