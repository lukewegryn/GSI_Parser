
public class lineHandler {
		private int fid, stateNumCode, countyNumCode, featureElevationMeters, featureElevationFeet;
		private String featureName, featureClass, stateAlphaCode, countyName, primaryLatitudeDMS, primaryLongitudeDMS, sourceLatitudeDMS, sourceLongitudeDMS, mapName, dateCreated, dateEdited;
		private float primaryLatitudeDeg, primaryLongitudeDeg, sourceLatitudeDeg, sourceLongitudeDeg;
		
		public lineHandler(String line){
			setLineHandler(line);
		}
		
		public void setLineHandler(String line){
		try{
			String[] splitArray = line.split("\\|");
			fid = Integer.parseInt(splitArray[0]);
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
		
		public int getFid(){
			return fid;
		}
		public void lineHandlerPrint()
		{
			System.out.println(fid + " "+ featureName + " " + dateCreated);
		}
}
