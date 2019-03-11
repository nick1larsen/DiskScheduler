/**
 * This class runs the Shortest Seek Time First (SSTF) disk scheduling algorithm.
 * @author nlarsen
 *
 */
public class SSTF extends Algorithm{

	int startingLocation;

	/**
	 * This constructor takes in the diskLocation Array and creates an instance of the Algorithm interface
	 * @param diskLocationArray
	 */
	SSTF(int[] diskLocationArray, int startingLocation){
		super(diskLocationArray);
		this.startingLocation = startingLocation;
	}

	/**
	 * Runs the SSTF disk scheduling algorithm given the input
	 */
	public void runSSTF() {
		System.out.println("Now running the Shortest Seek Time First disk scheduling algorithm");
		int currentLoc = startingLocation;
		int newLoc;
		//int potentialLoc;

		DoublyLinkedListImpl<Integer> dll = new DoublyLinkedListImpl<Integer>();
		super.sortDiskLocationArray();

		//populating the dll with the contents of diskLocationArray
		for(int i = 0 ; i<super.getDiskLocationArrayLength(); i++) {
			dll.addLast(super.getLocation(i));
		}

		dll.setCurrentAsHead();

		int dllLoc = 0;

		//if the first element (the head) is in the dll, then it gets added to the locationQ otherwise 
		if(super.getDiskLocationArrayLength() == 1) {
			dllLoc = dll.getCurrentElement();
			super.addToLocationQ(dll.removeCurrent());
		}else if (dll.getCurrentElement() > currentLoc) {
			dllLoc = dll.getCurrentElement();
			super.addToLocationQ(dll.removeCurrent());
		}else {
			boolean notSetFirst = true;
			while(notSetFirst == true) {
				dll.moveForwardOne();
				if(dll.checkIsTail() == true) {
					dllLoc = dll.getCurrentElement();
					super.addToLocationQ(dll.removeCurrent());
					notSetFirst = false;
				}else {
					//once the element that is greater than the current location is found then need to see which location is closer to current
					if(dll.getCurrentElement() >= currentLoc) {
						int nextInDLL = dll.getCurrentElement();
						dll.moveBackwardOne();
						//if the currentElement is closer to the currentLoc then it gets added to the queue else the next element will get added
						if(Math.abs(dll.getCurrentElement() - currentLoc) <= Math.abs(nextInDLL - currentLoc)){
							dllLoc = dll.getCurrentElement();
							super.addToLocationQ(dll.removeCurrent());
							notSetFirst = false;
						} else {
							dll.moveForwardOne();
							dllLoc = dll.getCurrentElement();
							super.addToLocationQ(dll.removeCurrent());
							notSetFirst = false;
						}
					}
				}
			}
		}


		int nextInDLL = 0;
		//this goes through the dll rest of the DLL and populates the locationQ based on the SSTF algorithm
		while(dll.size() > 1) {

			if(dll.checkIsTail() == false) {
				nextInDLL = dll.getNextElement();
			}
			if(Math.abs(dll.getCurrentElement() - dllLoc) <= Math.abs(nextInDLL - dllLoc)) {
				dllLoc = dll.getCurrentElement();
				super.addToLocationQ(dll.removeCurrent());
			} else {
				if(dll.checkIsTail() == true) {
					dllLoc = dll.getCurrentElement();
					super.addToLocationQ(dll.removeCurrent());
				}else {
				dll.moveForwardOne();
				dllLoc = dll.getCurrentElement();
				super.addToLocationQ(dll.removeCurrent());
				}
			}
		}
		if(dll.size() == 1) {
			super.addToLocationQ(dll.removeCurrent());
		}



		//    create dll
		//    sort diskLocationArray
		//    populate dll with diskLocationArray
		//    set current as head
		//    iterate through dll until find an element that is greater than the starting location, terminate when DLL is empty
		//    ****Need to handle situations where starting location is greater than all elements in DLL
		//determine whether current or previous node is closer to starting location
		//add that to the diskQ
		//remove it from DLL
		//when DLL is empty print diskQ

		while(super.peekLocationQ() != null) {

			newLoc = super.pollLocationQ();

			System.out.println("Moving arm from sector " + currentLoc + " to sector " + newLoc);
			super.calcDistance(currentLoc, newLoc);
			currentLoc = newLoc;
		}
		System.out.println("The arm traveled to the locations in this order " + super.getFinalLocationList());
		System.out.println("The total distance the arm traveled is " + super.getTotalDistance());
	}



}//SSTF