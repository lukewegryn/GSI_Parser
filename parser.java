/*
 * Luke Wegryn
 * GSI Parser: Project 1
 * 1/28/2015
 */
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is responsible for parsing the input data by seeking to a line, and returning a String
 * with the lines data. Most of the methods in this class are used to seek to specific lines, and do
 * something with that lines data.
 */
public class parser {
	private String fileName; //input GIS data file name
	private boolean isValidFile; //true if the input file is valid and accessible

	/*
	 * Inputs: (String) Filename, Output: Void, Purpose: Constructor.
	 */
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
	
	/*
	 * Sets the current filename that the parser is using.
	 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	/*
	 * Returns the fileName that is currently being used by the parser class.
	 */
	
	public String getFileName(String fileName){
		return this.fileName;
	}
	
	/*
	 * Returns true if the file is valid, false if the file is invalid.
	 */
	
	public boolean isValidFile(){
		return isValidFile;
	}
	
	/*
	 * Determines if the input offset is the beginning of a line.
	 * Returns 1 if beginning of line, 0 if not beginning of line, -1 if negative offset.
	 */
	public int isBeginningOfLine(long offset){ 
		int isBeginning = 0;
		try {
				RandomAccessFile raf = new RandomAccessFile(fileName, "r");
				if(offset < 0) //if the offset is negative there is no way it is valid
					isBeginning = -1;
				else{
					raf.seek(0); //go to the beginning of the file
					raf.seek(offset-1); //go to the passed offset and then go back one
					char checkChar = (char)raf.readByte(); //read in that character
					if(checkChar == '\n'){ //if it is a newline character then you know you have found the beginning of a line
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
	
	/*
	 * Finds the line closest to the offset and returns a String corresponding to that line.
	 */
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
	
	/*
	 * Gets the line corresponding to the offset (offset here refers to each line as one offset)
	 */
	
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
			
		} catch(IOException e) {
			
		}
		
		return line;
	}
	
	/*
	 * This function returns a Long List of all of the offsets in the file that correspond to the beginning of a line
	 */
	public List<Long> findOffsets()
	{
		List<Long> offsetList = new ArrayList<Long>(); //initialize a list of offset values
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			while(raf.readLine() != null){
				offsetList.add(raf.getFilePointer()); //get the current offset and add it to the list
			}
			
			offsetList.remove(offsetList.size()-1); // remove the very last offset
			raf.close();
		}
		catch(IOException e) {
		}
		
		return(offsetList);

	}
}

