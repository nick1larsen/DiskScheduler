import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/**
 * The algorithm abstract class is used by all of the individual algorithms. Each algorithm uses it differently
 * @author Nick Larsen
 *
 */
public abstract class Algorithm {
	private int[] diskLocationArray;
	private Queue<Integer> locationQ = new LinkedList<>();
	private int totalDistance = 0;
	private String finalLocationList = "";

	/**
	 * This constructor takes in the diskLocationArray and creates an instance of the Algorithm class
	 * @param diskLocationArray
	 */
	Algorithm(int[] diskLocationArray){
		this.diskLocationArray = diskLocationArray;
	}

	/**
	 * Prints the diskLocationArray
	 */
	public void printDiskLocationArray() {
		for(int i = 0; i < diskLocationArray.length; i++) {
			System.out.println("position " + i + ": " + diskLocationArray[i]);
		}
	}

	/**
	 * Adds a new location to the locationQ
	 * @param newLocation
	 */
	public void addToLocationQ(int newLocation) {
		locationQ.add(newLocation);
		//Algorithm.addToFinalLocationList(newLocation);
	}

	/**
	 * Returns the value at the head of locationQ without removing it
	 * @return
	 */
	public Integer peekLocationQ() {
		return locationQ.peek();
	}

	/**
	 * Returns the value at the head of the locationQ and removes it
	 * @return
	 */
	public Integer pollLocationQ() {
		finalLocationList = finalLocationList + locationQ.peek() + ",";
		return locationQ.poll();
	}

	/**
	 * Takes in a current location and new location, then calculates the distance between the two, then adds the distance to the total distance
	 * @param currentLoc
	 * @param newLoc
	 */
	public void calcDistance(int currentLoc, int newLoc) {
		int distance;

		distance = Math.abs(currentLoc - newLoc);
		System.out.println("The distance between the current sector (" + currentLoc + ") and the new sector (" + newLoc + ") is " + distance +"\n");
		totalDistance = totalDistance + distance;
	}
	
	/**
	 * Returns totalDistance
	 * @return
	 */
	public int getTotalDistance() {
		return totalDistance;
	}
	
	/**
	 * Returns the length of the diskLocationArray
	 * @return
	 */
	public int getDiskLocationArrayLength() {
		return diskLocationArray.length;
	}
	
	/**
	 * Given a position in the diskLocationArray, this returns the value of the location on the disk
	 * @param i
	 * @return
	 */
	public int getLocation(int i) {
		return diskLocationArray[i];
	}
	
	public void updateLocationValue(int i) {
		diskLocationArray[i] = -1;
	}
	
	/**
	 * Sorts the diskLocationArray
	 */
	public void sortDiskLocationArray() {
		Arrays.sort(diskLocationArray);
	}
	
	public void addToFinalLocationList(int location) {
		finalLocationList += location + ",";
	}
	
	public String getFinalLocationList() {
		return finalLocationList;
	}
}
