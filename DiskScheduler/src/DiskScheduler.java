import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program takes in a CSV of disk locations and schedules them according to three different disk scheduling
 * algorithms. It then prints the output to a different CSV
 * @author Nick Larsen
 *
 */
public class DiskScheduler {

	static int startingLocation = 200;
	private static String fileLocation;
	private static String outputLocation;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileScan = new Scanner(System.in);
	
	System.out.println("What is the file location? Enter it in this format: C:\\Users\\nlarsen\\Desktop\\input.csv");
	fileLocation = fileScan.nextLine();
	System.out.println("What is the output Location: Enter it in this format: C:\\Users\\nlarsen\\Desktop\\output.csv");
	outputLocation = fileScan.nextLine();

	//	fileLocation = "C:\\Users\\nlarsen\\Desktop\\input.csv";
		//This try statement opens up the CSV, determines how many lines there are in the file, 
		try {
			Scanner scan = new Scanner(new File(fileLocation));
			if (scan.hasNext() == true) {
				DiskScheduler.runShchedules();
			}


		} catch (FileNotFoundException e) {
			System.out.println("Input file not found. Enter a different location: ");
			fileLocation = fileScan.nextLine();
			Scanner scan = new Scanner(new File(fileLocation));

			if (scan.hasNext() == true) {
				DiskScheduler.runShchedules();
			}

		}






	}

	private static void runShchedules() {
		String inputString;
		int[] diskLocationArray = null;
		try {
			int numberOfCommas = 0;
			int locationInArray = 0;
			Scanner scan = new Scanner(new File(fileLocation));
			inputString = scan.nextLine();

			//counts the number of commas to setup the disk locations array
			for(int i = 0; i < inputString.length(); i++) {
				if(inputString.charAt(i)==',') {
					numberOfCommas++;
				}
			}

			diskLocationArray = new int[numberOfCommas+1];

			boolean finishedParsing = false;

			//populates location array with the inputString
			while(finishedParsing == false) {
				if(inputString.contains(",")==false) {
					finishedParsing = true;
					diskLocationArray[locationInArray] = Integer.parseInt(inputString);
				}else {

					// if(inputString.charAt(i) != ',') {
					diskLocationArray[locationInArray] = Integer.parseInt(inputString.substring(0, inputString.indexOf(",")));
					inputString = inputString.substring(inputString.indexOf(",")+1);
					locationInArray++;
				}

			}


			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Terminating.");
		}

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			
			
			fw = new FileWriter(outputLocation);
			bw = new BufferedWriter(fw);

			//FCFS fcfs = new FCFS(diskLocationArray);
			FCFS fcfs = new FCFS(diskLocationArray, startingLocation);
			fcfs.runFCFS();
			bw.write("Algorithm,TotalDistance");
			bw.newLine();
			bw.write("FCFS," + fcfs.getTotalDistance());
			bw.newLine();
			bw.write("Locations traveled: " + fcfs.getFinalLocationList());
			bw.newLine();
			bw.newLine();
			System.out.println("\n");

			SSTF sstf = new SSTF(diskLocationArray, startingLocation);
			sstf.runSSTF();
			bw.write("Algorithm,TotalDistance");
			bw.newLine();
			bw.write("SSTF," + sstf.getTotalDistance());
			bw.newLine();
			bw.write("Locations traveled: " + sstf.getFinalLocationList());
			bw.newLine();
			bw.newLine();
			System.out.println("\n");

			CSCAN cscan = new CSCAN(diskLocationArray, startingLocation);
			cscan.runCSCAN();
			bw.write("Algorithm,TotalDistance");
			bw.newLine();
			bw.write("CSCAN," + cscan.getTotalDistance());
			bw.newLine();
			bw.write("Locations traveled: " + cscan.getFinalLocationList());
			bw.newLine();
			bw.newLine();
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Unable to write to file");

		} finally {

			try {
				bw.close();
				fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("Unable to write to file");

			}

		}

	}
	
}
