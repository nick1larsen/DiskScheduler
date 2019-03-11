/**
 * This class runs the First Come First Serve disk scheduling algorithm.
 * @author nlarsen
 *
 */
public class FCFS extends Algorithm {
	
	int startingLocation;

	/**
	 * This constructor takes in the diskLocation Array and creates an instance of the Algorithm interface
	 * @param diskLocationArray
	 */
	FCFS(int[] diskLocationArray, int startingLocation){
		super(diskLocationArray);
		this.startingLocation = startingLocation;
	}

	/**
	 * Runs the FCFS disk scheduling algorithm given the input
	 */
	public void runFCFS() {
		System.out.println("Now running the First Come First Served disk scheduling algorithm");
		int currentLoc = startingLocation;
		int newLoc;

		//populates the locationQ
		for(int i = 0; i < super.getDiskLocationArrayLength(); i++) {
			if(super.getLocation(i)>1999) {
				System.out.println("There are only 2000 sectors on this disk. Ignoring request for Sector " + super.getLocation(i));
			}else {
				super.addToLocationQ(super.getLocation(i));
			}
		}

		while(super.peekLocationQ() != null) {

			newLoc = super.pollLocationQ();

			System.out.println("Moving arm from sector " + currentLoc + " to sector " + newLoc);
			super.calcDistance(currentLoc, newLoc);
			currentLoc = newLoc;
		}
		System.out.println("The total distance the arm traveled is " + super.getTotalDistance());
		System.out.println("The arm traveled to the locations in this order " + super.getFinalLocationList());
	}

}
