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
			//System.out.print(myParser.seekLine(265));
			myLineHandler = new lineHandler(myParser.seekLine(offsetList.get(0)));
			System.out.println(offsetList.get(0) + "\t" + myLineHandler.getFid());
			for(int i = 1; i < offsetList.size(); i++)
			{
				myLineHandler.setLineHandler(myParser.seekLine(offsetList.get(i)));
				System.out.println(offsetList.get(i) + "\t" + myLineHandler.getFid());
				
			}
		}
		if(myParser.isBeginningOfLine(offset) == 1){
			myLineHandler = new lineHandler(myParser.seekLine(offset));
			//myLineHandler.lineHandlerPrint();
		}
	}
}
