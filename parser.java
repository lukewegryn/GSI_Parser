import java.io.IOException;
import java.io.RandomAccessFile;

public class parser {
	private String fileName;

	public parser(String fileName){
		this.fileName = fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(String fileName){
		return this.fileName;
	}
	
	private int isBeginningOfLine(long offset){ //returns 1 if beginning of line, 0 if not beginning of line, -1 if negative offset
		int isBeginning = 0;
		try {
				RandomAccessFile raf = new RandomAccessFile(fileName, "r");
				if(offset < 0)
					isBeginning = -1;
				else{
					raf.seek(0);
					raf.seek(offset-1);
					char checkChar = (char)raf.readByte();
					if(checkChar == '\n'){
						isBeginning = 1;
					}
				}
				raf.close();
					
			
		}
		catch(IOException e) {
			isBeginning = 0;
		}
		
		return isBeginning;
	}
	
	public String seekLine(int offset){
		String line;
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			if(isBeginningOfLine(offset) == 1)
			{
				raf.seek(offset);
				line = raf.readLine();
			}
			
			else if(isBeginningOfLine(offset) == 2){
				line = "Offset not valid";
			}
			
			else if(isBeginningOfLine(offset) == -1){
				line = "Offset is negative";
			}
			
			else
				line = "Offset not valid";
			
		} catch(IOException e) {
			line = "IOException: seekLine";
		}
		
		return line;
	}
	
	public String getLine(int offset){
		String line = null;
		String testLine = null;
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			if(offset >= 0)
			{
				for(int i = 0; i < offset; i = i+1)
					raf.readLine();
				if((testLine = raf.readLine()) != null)
					line = testLine;
				else
					line = "Offset not valid";
				raf.close();
			}
			
			else
				line = "Offset not valid";
			//System.out.println(line);
		} catch(IOException e) {
			System.out.print("IOException: getLine");
		}
		
		return line;
	}

}

