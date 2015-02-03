/*
 * Luke Wegryn
 * GSI Parser: Project 1
 * 1/28/2015
 */

/*
 * The main purpose of the lineHandler class is to split and store all of the information in a single line of
 * the input file. Once passed into the class, all of the information about a single line of the input
 * file is available.
 */
public class lineHandler {
		private int fid, stateNumCode, countyNumCode, featureElevationMeters, featureElevationFeet; //these variables represent all of the different fields from the GIS file
		private String featureName, featureClass, stateAlphaCode, countyName, primaryLatitudeDMS, primaryLongitudeDMS, sourceLatitudeDMS, sourceLongitudeDMS, mapName, dateCreated, dateEdited;
		private float primaryLatitudeDeg, primaryLongitudeDeg, sourceLatitudeDeg, sourceLongitudeDeg;
		
		/*
		 * This method is the Constructor. Takes a line from the input file and stores the data in the instance of the class
		 */
		public lineHandler(String line){
			setLineHandler(line); //set the values of all of the data members using the helper function
		}
		
		/*
		 * Helper function for the Constructor. Can also be used to reset the values in the instance of the class.
		 */
		public void setLineHandler(String line){
		try{
			String[] splitArray = line.split("\\|"); //split the array up by splitting at the pipe symbol using a regex
			fid = Integer.parseInt(splitArray[0]); //convert all of the data to the correct type
			featureName = splitArray[1];
			featureClass = splitArray[2];
			stateAlphaCode = splitArray[3];
			stateNumCode = Integer.parseInt(splitArray[4]);
			countyName = splitArray[5];
			countyNumCode = Integer.parseInt(splitArray[6]);
			primaryLatitudeDMS = splitArray[7];
			primaryLongitudeDMS = splitArray[8];
			primaryLatitudeDeg = Float.parseFloat(splitArray[9]);
			primaryLongitudeDeg = Float.parseFloat(splitArray[10]);
			sourceLatitudeDMS = splitArray[11];
			sourceLongitudeDMS = splitArray[12];
			sourceLatitudeDeg = Float.parseFloat(splitArray[13]);
			sourceLongitudeDeg = Float.parseFloat(splitArray[14]);
			featureElevationMeters = Integer.parseInt(splitArray[15]);
			featureElevationFeet = Integer.parseInt(splitArray[16]);
			mapName = splitArray[17];
			dateCreated = splitArray[18];
			if(splitArray.length == 20)
				dateEdited = splitArray[19];
			else
				dateEdited = null;
			}
		catch(java.lang.NumberFormatException e){
			
		}
			
		}
		
		/*
		 * Returns the FID corresponding to the current line. 
		 */
		public int getFid(){
			return fid;
		}
		
		/*
		 * Returns the correct value based on the command entered.
		 */
		public String commandResult(String command){
			String result = null;
			if(command.equals("show_name")){ //determine which command was entered and return the corresponding data
				return featureName;
			}
			else if(command.equals("show_latitude")){
				return splitLatitude(primaryLatitudeDMS);
			}
			
			else if(command.equals("show_longitude")){
				return splitLongitude(primaryLongitudeDMS);
			}
			
			else if(command.equals("show_elevation")){
				return Integer.toString(featureElevationFeet);
			}
			
			else if(command.equals("quit")){
				return "quit";
			}
			return result;
		}
		
		/*
		 * Converts the Integer representation of the latitude into a readable version. 
		 */
		private String splitLatitude(String latitude){
			String direction; 
			if(latitude.charAt(6) == 'N'){ 
				direction = "North";
			}
			
			else
				direction = "South";
			
			String result = (Integer.parseInt(latitude.substring(0, 2)) + "d " + Integer.parseInt(latitude.substring(2,4)) + "m " + Integer.parseInt(latitude.substring(4, 6)) + "s " + direction);
			return result; //this value is constructed from the data and the correct formatting is added
		}
		
		/*
		 * Converts the Integer representation of the longitude into a readable version. 
		 */
		private String splitLongitude(String latitude){
			String direction;
			if(latitude.charAt(7) == 'W'){
				direction = "West";
			}
			
			else
				direction = "East";
			
			String result = (Integer.parseInt(latitude.substring(0, 3)) + "d " + Integer.parseInt(latitude.substring(3,5)) + "m " + Integer.parseInt(latitude.substring(5, 7)) + "s " + direction);
			return result; //this value is constructed from the data and the correct formatting is added
		}
}
