import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class HW3 {

	public static void main(String[] args) throws Exception{
		
		String algorithm = args[0];
		String Output_File = args[1];
		int Arr_Max = Integer.parseInt(args[2]);
		double times[] = new double[Arr_Max];
		
		/*
		 * 	P-4.60 Perform an experimental analysis of the two algorithms prefixAverage1 and prefixAverage2,
			from Section 4.3.3. Visualize their running times as a function of the input size with a log-log chart.
			P-4.61 Perform an experimental analysis that compares the relative running times of the
			methods shown in Code Fragment 4.12.
		 */
		
		//A total of 4 plots should be generated
		
		for(int i = 0; i < Arr_Max; i++) {
		 
			double a[] = new double[(int) Math.pow(10, i + 1)];
			int b[] = new int[(int) Math.pow(10, i + 1)];
			int c[] = new int[(int) Math.pow(10, i + 1)];
			
			Random rd = new Random();
			
			for (int j = 0; j < a.length; j++) {
		         a[i] = rd.nextDouble();
		         b[i] = rd.nextInt();
		         c[i] = rd.nextInt();
			}
			//System.out.println(a.length);
			long startTime = System.nanoTime();
		
			if(algorithm.equals("p1")) {//Double
				prefixAverage1(a);
			} else if(algorithm.equals("p2")) {//Double
				prefixAverage2(a);			
			} else if(algorithm.equals("e1")) {//Int
				example1(b);
			} else if(algorithm.equals("e2")) {//Int
				example2(b);
			} else if(algorithm.equals("e3")) {//Int
				example3(b);
			} else if(algorithm.equals("e4")) {//Int
				example4(b);
			} else if(algorithm.equals("e5")) {//Int, Int
				example5(b,c);
			}
			long endTime = System.nanoTime();
			times[i] = Math.log10((endTime - startTime));
		}
		
		//for(int i = 0; i < times.length; i++)
		//	System.out.println(times[i]);
		
		FileWriter x = new FileWriter(Output_File);
		BufferedWriter z = new BufferedWriter(x);
		for(int i = 0; i < times.length; i++) {
				z.write(times[i] + "\n");
		}
		z.close();
	}

	
	//Start Examples
	
	/** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
	public static void prefixAverage1(double[ ] x) {
		int n = x.length;
		double[ ] a = new double[n]; // filled with zeros by default
		for (int j=0; j < n; j++) {
				double total = 0; // begin computing x[0] + ... + x[j]
				for (int i=0; i <= j; i++)
					total += x[i];
				a[j] = total / (j+1); // record the average
	} 
		//return a;
	}
	/** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
	public static void prefixAverage2(double[ ] x) {
		int n = x.length;
		double[ ] a = new double[n]; // filled with zeros by default
		double total = 0; // compute prefix sum as x[0] + x[1] + ...
		for (int j=0; j < n; j++) { 
				total += x[j]; // update prefix sum to include x[j]
				a[j] = total / (j+1); // compute average based on current sum
		} 
		//return a;
	}
	/** Returns the sum of the integers in given array. */
	public static void example1(int[ ] arr) {
		int n = arr.length, total = 0;
		for (int j=0; j < n; j++) // loop from 0 to n-1
				total += arr[j];
		//return total;
	}
	/** Returns the sum of the integers with even index in given array. */
	public static void example2(int[ ] arr) {
		int n = arr.length, total = 0;
			for (int j=0; j < n; j += 2) // note the increment of 2
				total += arr[j];
		//return total;
	}
	/** Returns the sum of the prefix sums of given array. */
	public static void example3(int[ ] arr) {
		int n = arr.length, total = 0;
		for (int j=0; j < n; j++) // loop from 0 to n-1
			for (int k=0; k <= j; k++) // loop from 0 to j
				total += arr[j];
		//return total;
	}
	/** Returns the sum of the prefix sums of given array. */
	public static void example4(int[ ] arr) {
		int n = arr.length, prefix = 0, total = 0;
		for (int j=0; j < n; j++) { // loop from 0 to n-1
			prefix += arr[j];
			total += prefix;
		}
		//return total;
	}
	/** Returns the number of times second array stores sum of prefix sums from first. */
	public static void example5(int[ ] first, int[ ] second) { // assume equal-length arrays
		int n = first.length, count = 0;
		for (int i=0; i < n; i++) { // loop from 0 to n-1
			int total = 0;
			for (int j=0; j < n; j++) // loop from 0 to n-1
				for (int k=0; k <= j; k++) // loop from 0 to j
					total += first[k];
			if (second[i] == total) count++;
		}
		//return count;
	}
	
	//End Examples

}
