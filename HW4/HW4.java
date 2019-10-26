import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Towers of Hanoi solved by recursion
 * 		currently supports 3-5 pegs
 */

public class HW4 {

	public static void main(String[] args) throws IOException {
		
		int num_Pegs = Integer.parseInt(args[0]);
		int num_Disks = Integer.parseInt(args[1]);
		String outputFile = args[2];
		
		FileWriter a = new FileWriter(outputFile);
		BufferedWriter theFile = new BufferedWriter(a);
		
		
		if(num_Pegs == 3) {
			Hanoi3(num_Pegs, 'A', 'B', 'C', theFile);
		} else if(num_Pegs == 4) {
			Hanoi4(num_Disks, 'A', 'B', 'C', 'D', theFile);
		} else if(num_Pegs == 5) {
			Hanoi5(num_Disks, 'A', 'B', 'C', 'D', 'E', theFile);
		}
		
		theFile.close();
		
		/*
		 * Unload tops onto open pegs topmost from right to left
		 * sort those pegs
		 * repeat until bottom of first peg is revealed and move to last
		 * 
		 */

	}
	
	/*
	 * goes back and forth
	 * 
	 * 1. remove disk from first pole to open spaces run as many times for open poles. (A -> N)
	 * 2. sort those back making one pass each pole (N -> A) check to put disk on last first
	 * 
	 */
	
	public static void Hanoi3(int Disk, char first, char middle, char last, BufferedWriter theFile) throws IOException {
		
		//When Biggest disk is left
		if(Disk == 1) {
			//System.out.println(Disk + " " + first + " " + last);
			theFile.write(Disk + " " + first + " " + last);
			theFile.newLine();
			return;
		}
		
		//				first	middle last
		Hanoi3(Disk - 1, first, last, middle, theFile);
		
		//System.out.println(Disk + " " + first + " " + last);
		theFile.write(Disk + " " + first + " " + last);
		theFile.newLine();
		
		//				first	middle last
		Hanoi3(Disk - 1, middle, first, last, theFile);
		
	}
	public static void Hanoi4(int Disk, char first, char middle1, char middle2, char last, BufferedWriter theFile) throws IOException {
		
		//When there is no disk
		if(Disk == 0) {
			return;
		}
		
		//When Biggest disk is left
		if(Disk == 1) {
			//System.out.println(Disk + " " + first + " " + last);
			theFile.write(Disk + " " + first + " " + last);
			theFile.newLine();
		}
		
		//				first  middle1 middle2 last
		Hanoi4(Disk - 2, first, last, middle2, middle1, theFile);
		
		//System.out.println(Disk - 1 + " " + first + " " + middle2);
		theFile.write(Disk - 1 + " " + first + " " + middle2);
		theFile.newLine();
		//System.out.println(Disk + " " + first + " " + last);
		theFile.write(Disk + " " + first + " " + last);
		theFile.newLine();
		//System.out.println(Disk - 1 + " " + middle2 + " " + last);
		theFile.write(Disk - 1 + " " + middle2 + " " + last);
		theFile.newLine();
		
		//				 first    middle1 middle2 last
		Hanoi4(Disk - 2, middle1, middle2, first, last, theFile);
		
	}
	
	public static void Hanoi5(int Disk, char first, char middle1, char middle2, char middle3, char last, BufferedWriter theFile) throws IOException {
		
		//When there is no disk left
		if(Disk <= 0) {
			return;
		}
		//When Biggest disk is left
		if(Disk == 1) {
			//System.out.println(Disk + " " + first + " " + last);
			theFile.write(Disk + " " + first + " " + last);
			theFile.newLine();
		}

		//				 first middle1 middle2 middle3  last
		Hanoi5(Disk - 3, first, last, middle3, middle2, middle1, theFile);
		
		if(Disk >= 3) {
			//System.out.println(Disk - 2 + " " + first + " " + middle2);
			theFile.write(Disk - 2 + " " + first + " " + middle2);
			theFile.newLine();
		}
		
		if(Disk >= 2) {
			//System.out.println(Disk - 1 + " " + first + " " + middle3);
			theFile.write(Disk - 1 + " " + first + " " + middle3);
			theFile.newLine();

			//System.out.println(Disk + " " + first + " " + last);
			theFile.write(Disk + " " + first + " " + last);
			theFile.newLine();
			
			//System.out.println(Disk - 1 + " " + middle3 + " " + last);
			theFile.write(Disk - 1 + " " + middle3 + " " + last);
			theFile.newLine();
		}
		
		if(Disk >= 3) {
			//System.out.println(Disk - 2 + " " + middle2 + " " + last);
			theFile.write(Disk - 2 + " " + middle2 + " " + last);
			theFile.newLine();
		}
		
		//				 first   middle1 middle2 middle3  last
		Hanoi5(Disk - 3, middle1, middle2, middle3, first, last, theFile);
		
	}

}
