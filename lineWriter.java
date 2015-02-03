/*
 * Luke Wegryn
 * GSI Parser: Project 1
 * 1/28/2015
 */
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * The main purpose of this class is to write information to an output file. You can pass it different types
 * of data and it will format it according to what the Specification required. 
 */
public class lineWriter {
	
	private long offset; //the current output offset, where the program will write out next
	private String fileName; //the output fileName that the output data will be written to
	private File outFile; //a file object that is initialized with the file name
	private int curCommandNum; //the current command value (increases by 1 each time a command is ouput)
	
	/*
	 * Class constructor method. Sets the output file and outputs the first line of text.
	 */
	public lineWriter(){
		fileName = "Result.txt"; //the filename is always Result.txt in this case
		curCommandNum = 0; //the command counter will be incremented to 1 before the first output line.
		try{
		outFile = new File("Result.txt");
		if(!outFile.exists()){ //if the output file doesn't already exist, create one
			outFile.createNewFile();
		}
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			raf.seek(0);
			raf.writeBytes("GIS data file contains the following records:\r\n\r\n"); //this is the initial line at the top of the file
			offset = raf.getFilePointer();
			raf.close();
		}
		
		catch(IOException e){
			
		}
	}
	
	/*
	 * Writes an input string to the output file with a newline character added.
	 */
	public void writeLineToFile(String dataToWrite){
		try{
			if(outFile.exists()){
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset);
				raf.writeBytes(dataToWrite + "\r\n"); //write the data that is passed in to the file, and put a new line
				
				offset = raf.getFilePointer(); //store the current file offset
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
	}
	
	/*
	 * Writes a command to the output file, and automatically keeps track of the numbering.
	 */
	public void writeCommandToFile(String command){
		try{
			if(outFile.exists()){
				curCommandNum++; //each time a command is output, increase the current command number by 1
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset);
				raf.writeBytes(curCommandNum + ": " + command + "\r\n"); //write the commands out to the file in the correct format
				offset = raf.getFilePointer(); //get the current offset that we are at
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
		
	}
	
	/*
	 * Writes a result to a file. Automatically takes care of the tabbing and the newlines.
	 */
	public void writeResultToFile(String result){
		try{
			if(outFile.exists()){ //ensure that an output file exists
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset); //move to the current offset
				raf.writeBytes("\t" + result + "\r\n"); //write the line with the correct output
				offset = raf.getFilePointer();
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
		
	}

}
