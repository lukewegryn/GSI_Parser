import java.io.IOException;
import java.io.RandomAccessFile;

public class commandParser {
	private String fileName;
	private long offset;
	private boolean hasNext;
	
	public commandParser(String commandFileName){
		fileName = commandFileName;
		offset = 0;
		hasNext = true;
	}
	
	public boolean hasNext(){
		return hasNext;
	}
	
	public String[] parseNext(){
		String[] command = {null, null};
		String line;
		try{
				RandomAccessFile raf = new RandomAccessFile(fileName, "r");
				raf.seek(offset);
				if(!(line = raf.readLine()).contains("quit")){
					offset = raf.getFilePointer();
					if(!line.contains(";")){
						command = line.split("\t");
						}
					}
					
				else{
						//System.out.println(";");
						command[0] = "quit";
						command[1] = "quit";
					}
				
				raf.close();
			}
		
		catch(IOException e) {
		}
		
			return command;
		}
}
