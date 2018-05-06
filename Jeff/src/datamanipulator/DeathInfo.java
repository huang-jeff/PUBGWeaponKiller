package datamanipulator;

public class DeathInfo {

	private String deathType, killerName, victimName, gridCoord;
	private double killerXPosition, killerYPosition, victimXPosition, victimYPosition, killDistance;
	private int deathTime;

	public DeathInfo(String kType, String kName, double kXPos, double kYPos, int time, String vName, double vXPos, double vYPos) {
		
		this.deathType = kType;
		this.killerName = kName;
		this.killerXPosition = checkVal(kXPos);
		this.killerYPosition = checkVal(kYPos);
		this.deathTime = time;
		this.victimName = vName;
		this.victimXPosition = checkVal(vXPos);
		this.victimYPosition = checkVal(vYPos);
		this.killDistance = distanceCalc(kXPos, kYPos, vXPos, vYPos);
	}

	public double checkVal(double value) {
		if(value >= 800000) {
			value = 799999;
		}
		return value;
	}
	
	public double distanceCalc(double kX, double kY, double vX, double vY) {
		int conversion = 100;
		double distance = Math.sqrt(Math.pow(vX/conversion - kX/conversion, 2) + Math.pow(vY/conversion - kY/conversion,  2));
		if(this.deathType.equals("Drown") || this.deathType.equals("Bluezone") || this.deathType.equals("Redzone")) {
			distance = 0;
		}
		if(this.killerName.isEmpty()) {
			distance = 0;
		}
		return distance;
	}

	public void printInfo() {
		System.out.println("Killed with " + this.deathType + 
				"\nDeath time >> " + deathTime + 
				"\nKilled by " + killerName + 
				"\nKiller position >> (" + killerXPosition + ", " + killerYPosition + ")" +
				"\nVictim name: " + victimName + 
				"\nVictim position >> (" + victimXPosition + ", " + victimYPosition + ")\n");
	}

	public String getKName() {
		return this.killerName;
	}
	
	public String getVName() {
		return this.victimName;
	}
	
	public String getType() {
		return this.deathType;
	}
	
	public double getvX() {
		return this.victimXPosition;
	}
	
	public double getvY() {
		return this.victimYPosition;
	}
	
	public double getkX() {
		return this.killerXPosition;
	}
	
	public double getkY() {
		return this.killerYPosition;
	}
	
	public double getDist() {
		return this.killDistance;
	}
	
	public String convertToCoord() {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int x = (int) ((this.victimXPosition/50000) + 1);
		int y = (int) (this.victimYPosition/50000);
		//System.out.printf("(%6.1f, %6.1f) --> (%2d, %2d)\n", this.victimXPosition, this.victimYPosition, x, y);
		
		String coord = alpha.substring(y, y+1) + x;
		return coord;
	}

	public String convertToCSV() {
		String csvLine = this.deathType +  
				"," + this.killerName +
				"," + this.killerXPosition +
				"," + this.killerYPosition +
				"," + this.deathTime +
				"," + this.victimName +
				"," + this.victimXPosition +
				"," + this.victimYPosition +
				"," + this.killDistance + 
				"," + convertToCoord() + 
				"\n"; 
		return csvLine;
	}
	
	public String convertToCSVcompact() {
		String csvLine = this.killerXPosition +  
				"," + this.killerYPosition +
				"," + this.killDistance +
				//"," + this.victimXPosition +
				//"," + this.victimYPosition +
				"," + this.deathType + 
				//"," + convertToCoord() + 
				"\n"; 
		return csvLine;
	}
}
