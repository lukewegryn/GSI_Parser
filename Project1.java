import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Project1 {
	public static void main(String[] args) {
		parser myParser = new parser(args[0]);
		long offset = 5178;
		lineHandler myLineHandler;
		List<Long> offsetList;
		if(myParser.isValidFile()){
			offsetList = myParser.findOffsets();
			for(int i = 0; i < offsetList.size(); i++)
			{
				System.out.println(offsetList.get(i));
			}
		}
		if(myParser.isBeginningOfLine(offset) == 1){
			myLineHandler = new lineHandler(myParser.seekLine(offset));
			//myLineHandler.lineHandlerPrint();
		}
	}
}
