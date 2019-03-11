/**
 * This class runs the CSCAN disk scheduling algorithm. It goes from the starting location up, and stops at the final location in that direction,
 * then switches directions to the lowest location 
 * @author nlarsen
 *
 */
public class CSCAN extends Algorithm{

	int startingLocation;

	/**
	 * This constructor takes in the diskLocation Array and creates an instance of the Algorithm interface
	 * @param diskLocationArray
	 */
	CSCAN (int[] diskLocationArray, int startingLocation){
		super(diskLocationArray);
		this.startingLocation = startingLocation;
	}

	/**
	 * Runs the CSCAN disk scheduling algorithm given the input
	 */
	public void runCSCAN() {
		System.out.println("Now running the Circular-SCAN (C-SCAN) disk scheduling algorithm");
		int currentLoc = startingLocation;
		int newLoc;
		int headOfQueueLocation = 0;

		//     sort the array
		//     find the first item that is greater than or equal to the starting location
		//-----if all items are less than the starting location then add them from the beginning
		//add the items from that point until the greatest one has been added
		//go back to the beginning of the array and start adding those items, stop before the first one has been added

		super.sortDiskLocationArray();

		//this determines which location will be next based on being greater than or equal to the starting location
		for(int i = 0; i < super.getDiskLocationArrayLength(); i++) {
			if(super.getLocation(i) >= startingLocation){
				headOfQueueLocation = i;
				i = super.getDiskLocationArrayLength();
			}
		}
		
		//populates the locationQ starting with the first location that is greater or equal to the starting location
		for(int i = headOfQueueLocation; i < super.getDiskLocationArrayLength(); i++) {
			if(super.getLocation(i)>1999) {
				System.out.println("There are only 2000 sectors on this disk. Ignoring request for Sector " + super.getLocation(i));
			}else {
				super.addToLocationQ(super.getLocation(i));
			}
		}
		
		//populates the remaining locations into locationQ
		for(int i = 0; i < headOfQueueLocation; i++) {
			super.addToLocationQ(super.getLocation(i));
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
