import java.io.IOException;
import java.io.RandomAccessFile;

public class Project1 {
	public static void main(String[] args) {
		parser myParser = new parser(args[0]);
		System.out.print(myParser.seekLine(5178));
	}
}
