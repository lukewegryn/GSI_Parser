import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class lineWriter {
	
	private long offset;
	private String fileName;
	private File outFile;
	private int curCommandNum;
	
	public lineWriter(){
		fileName = "Result.txt";
		curCommandNum = 0;
		try{
		outFile = new File("Result.txt");
		if(outFile.exists()){
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			raf.seek(0);
			raf.writeBytes("GIS data file contains the following records:\r\n\r\n");
			offset = raf.getFilePointer();
			raf.close();
		}
		}
		
		catch(IOException e){
			
		}
	}
	
	public void writeLineToFile(String dataToWrite){
		try{
			if(outFile.exists()){
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset);
				raf.writeBytes(dataToWrite + "\r\n");
				
				offset = raf.getFilePointer();
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
	}
	
	public void writeCommandToFile(String command){
		try{
			if(outFile.exists()){
				curCommandNum++;
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset);
				raf.writeBytes(curCommandNum + ": " + command + "\r\n");
				
				offset = raf.getFilePointer();
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
		
	}
	
	public void writeResultToFile(String result){
		try{
			if(outFile.exists()){
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				raf.seek(offset);
				raf.writeBytes("\t" + result + "\r\n");
				offset = raf.getFilePointer();
				raf.close();
			}
			}
			
			catch(IOException e){
				
			}
		
	}

}
