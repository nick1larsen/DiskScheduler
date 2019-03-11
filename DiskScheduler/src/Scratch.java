import java.util.Arrays;
import java.util.NoSuchElementException;

public class Scratch {

	public static void main(String[] args) {
		int[] diskLocationArray = new int[6];
		
		diskLocationArray[0] = 50;
		diskLocationArray[1] = 452;
		diskLocationArray[2] = 98;
		diskLocationArray[3] = 50;
		diskLocationArray[4] = 8;
		diskLocationArray[5] = 980;
		
DoublyLinkedListImpl<Integer> dll = new DoublyLinkedListImpl<Integer>();
        
        /*
        dll.addFirst(10);
        dll.addFirst(34);
        dll.addLast(56);
        dll.addLast(364);
        dll.iterateForward();
        dll.removeFirst();
        dll.removeLast();
        dll.iterateBackward();
        */
        
	//	dll.removeFirst();

        dll.addFirst(1);
        dll.addLast(22);
        dll.addLast(33);
        dll.addLast(44);
     //   dll.addLast(5);
        
        dll.setCurrentAsHead();
        dll.moveForwardOne();
        dll.moveForwardOne();
        dll.removeCurrent();
        dll.moveForwardOne();
//        dll.moveForwardOne();
//        dll.moveBackwardOne();
        dll.moveBackwardOne();
        dll.iterateBackward();
        dll.iterateForward();
        
        /*
        try {
        dll.setCurrentAsHead();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.moveForwardOne();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.removeCurrent();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.moveForwardOne();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.moveForwardOne();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.removeCurrent();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.removeCurrent();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.moveBackwardOne();
        System.out.println("NOw at " + dll.getCurrentElement());
        dll.removeCurrent();
        System.out.println("NOw at " + dll.getCurrentElement());

        dll.removeCurrent();
        System.out.println("NOw at " + dll.getCurrentElement());
        }catch (NoSuchElementException ex){
        	System.out.println("Terminating gracefully");
        }
        */
      //  dll.cu
	}

}
