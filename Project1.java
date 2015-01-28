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
			lineWriter lw = new lineWriter();
			lw.writeLineToFile(("\t" + offsetList.get(0) + "\t" + myLineHandler.getFid()));
			for(int i = 1; i < offsetList.size(); i++)
			{
				myLineHandler.setLineHandler(myParser.seekLine(offsetList.get(i)));
				lw.writeLineToFile(("\t" + offsetList.get(i) + "\t" + myLineHandler.getFid()));
				
			}
			lw.writeLineToFile("\r\n");
			
			long lastOffset = offsetList.get(offsetList.size()-1);
			commandParser cp = new commandParser(args[1]);
			String[] currentCommand = {null, null};
			boolean quit = false;
			while(!quit){
				currentCommand = cp.parseNext();
				if(currentCommand[0] == "quit"){
					quit = true;
				}
				
				else if(currentCommand[0] != null){
					
					lw.writeCommandToFile(currentCommand[0] + "\t" + currentCommand[1]);
					
					long commandOffset = Integer.parseInt(currentCommand[1]);
					if( commandOffset > lastOffset){
						lw.writeResultToFile("Offset too large");
					}
					
					else if(commandOffset < 0){
						lw.writeResultToFile("Offset is not positive");
					}
					
					else if(myParser.isBeginningOfLine(commandOffset) != 1){
						lw.writeResultToFile("Unaligned offset");
					}
					
					else if(myParser.isBeginningOfLine(commandOffset) == 1){
						myLineHandler.setLineHandler(myParser.seekLine(commandOffset));
						lw.writeResultToFile(myLineHandler.commandResult(currentCommand[0]));
					}
				}
			}

			}
			
		
	}
}
