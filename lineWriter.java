import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class lineWriter {
	
	private long offset;
	private String fileName;
	private File outFile;
	
	public lineWriter(){
		fileName = "Result.txt";
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

}
