import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class parser {
	private String fileName;
	private boolean isValidFile;

	public parser(String fileName){
		this.fileName = fileName;
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");

			raf.close();	
			}
	catch(IOException e) {
		isValidFile = false;
	}
		isValidFile = true;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(String fileName){
		return this.fileName;
	}
	
	public boolean isValidFile(){
		return isValidFile;
	}
	
	public int isBeginningOfLine(long offset){ //returns 1 if beginning of line, 0 if not beginning of line, -1 if negative offset
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
	
	public String seekLine(long offset){
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
	
	public String getLine(long offset){
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
			//System.out.print("IOException: getLine");
		}
		
		return line;
	}
	
	public List<Long> findOffsets()
	{
		List<Long> offsetList = new ArrayList<Long>();
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			while(raf.readLine() != null){
				offsetList.add(raf.getFilePointer());
			}
			
			offsetList.remove(offsetList.size()-1);
			raf.close();
		}
		catch(IOException e) {
		}
		
		return(offsetList);

	}
}

