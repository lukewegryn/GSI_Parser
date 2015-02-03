/*
 * Luke Wegryn
 * GSI Parser: Project 1
 * 1/28/2015
 */


import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * This is the command parsing class. It reads in commands from the input file, and generates the next command that the program
 * should perform.
 */
public class commandParser {
	private String fileName; //the fileName of the Command file
	private long offset; //the current offset of the file pointer, (where we are currently in the file)
	private boolean hasNext; //boolean that is true if there is more commands to parse and false if it is the end of the file

	/*
	 * This is the constructor for the class. It sets the filename of the class and the intial offset.
	 */
	public commandParser(String commandFileName){
		fileName = commandFileName;
		offset = 0;
		hasNext = true;
	}

	/*
	 * Returns true if there is more commands to be read from the file. 
	 */
	public boolean hasNext(){
		return hasNext;
	}

	/*
	 * If there is another command in the file, read it and return it.
	 */
	public String[] parseNext(){
		String[] command = {null, null};
		String line;
		try{
				RandomAccessFile raf = new RandomAccessFile(fileName, "r");
				raf.seek(offset);
				if(!(line = raf.readLine()).contains("quit")){
					offset = raf.getFilePointer();
					if(!line.contains(";")){ //if the line isn't a comment
						command = line.split("\t");  //split the line by a tab
						}
					}

				else{
						command[0] = "quit"; //if it is the end of the file, return the "quit" string
						command[1] = "quit";
					}

				raf.close();
			}

		catch(IOException e) {
		}

			return command;
		}
}
