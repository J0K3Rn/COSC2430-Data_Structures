import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class HW2 {

	public static void main(String[] args) throws Exception {
		
		/*
		For Addition: 		add a.txt b.txt output.txt
		For Subtraction: 	sub a.txt b.txt output.txt
		For Multiplication: mul a.txt b.txt output.txt
		For Transpose: 		tra a.txt output.txt
		For Determinant: 	det a.txt output.txt
		*/
		
		//Can have 3 or 4 arguments
		//Use try/catch blocks
		String Operation; 
		String Input_File1 = "";
		String Input_File2 = "";
		String Output_File = "";
		boolean Other_Operation = false;
		
		Operation = args[0];
		Input_File1 = args[1];
		Input_File2 = args[2];
		
		try {//The only case that could throw an error
			Output_File = args[3];
		}	catch (Exception E){
			Other_Operation = true;
			
		}
		
		BufferedReader reader1 = new BufferedReader(new FileReader(Input_File1));
		BufferedReader reader2 = null;
		if(!Other_Operation) {
			reader2 = new BufferedReader(new FileReader(Input_File2));
		}
				
		String line;
		LinkedList<LinkedList> list1 = new LinkedList<LinkedList>();
		LinkedList<LinkedList> list2 = new LinkedList<LinkedList>();
		LinkedList<LinkedList> list3 = new LinkedList<LinkedList>();
		
		
		//Initialize lists
		int count;
		
		count = 0;
		while((line = reader1.readLine()) != null) { //Put reader1 into list1
			
			String[] everything = line.split(" ");
				list1.add(new LinkedList<Float>());
			for(int i = 0; i < everything.length; i++) {
				list1.get(count).add(i, Float.parseFloat(everything[i]));
			}
			count += 1;
		}
		
		//if(!Operation.equals("tra") || !Operation.equals("det")) {
		if(!Other_Operation) {
			count = 0;
			while((line = reader2.readLine()) != null) { //Put reader2 into list2
				
				String[] everything = line.split(" ");
					list2.add(new LinkedList<Float>());
				for(int i = 0; i < everything.length; i++) {
					list2.get(count).add(i, Float.parseFloat(everything[i]));
				}
				count += 1;
			}
		}
		//End initialize lists
		
		//debug
		/*for(int i = 0; i < list2.size(); i++) {	//Prints list
			for(int j = 0; j < list2.get(i).size(); j++) {
				System.out.print(list2.get(i).get(j) + " ");
			}
			System.out.println("");
		}*/
		
		
		//Operations
		//Will crash if matrix's are not the same size
		
		Float number;
		number = (float) 0.0;
		
		//Begin Operations
			if(Operation.equals("add")) {//Add
				for(int i = 0; i < list1.size(); i++) {
					list3.add(new LinkedList<Float>());
					for(int j = 0; j < list1.get(i).size(); j++) {
						number = (Float) list1.get(i).get(j) + (Float) list2.get(i).get(j);
						list3.get(i).add(number);
					}
				}
			} else if(Operation.equals("sub")) {//Subtract
				for(int i = 0; i < list1.size(); i++) {
					list3.add(new LinkedList<Float>());
					for(int j = 0; j < list1.get(i).size(); j++) {
						number = (Float) list1.get(i).get(j) - (Float) list2.get(i).get(j);
						list3.get(i).add(number);
					}
				}
			} else if(Operation.equals("mul")) {//Matrix Multiplication
				for(int i = 0; i < list1.size(); i++) {
					list3.add(new LinkedList<Float>());
					for(int j = 0; j < list1.get(i).size(); j++) {
						for(int k = 0; k < list1.get(i).size(); k++) {
							number += (Float) list1.get(i).get(k) * (Float) list2.get(k).get(j);
						}
						list3.get(i).add(number);
						number = (float) 0.0;
					}
				}
			}else if(Operation.equals("tra")) {//Transpose
				for(int i = 0; i < list1.size(); i++) {
					list3.add(new LinkedList<Float>());
					for(int j = 0; j < list1.get(i).size(); j++) {
						number = (Float) list1.get(j).get(i);
						list3.get(i).add(number);
					}
				}
			} else if(Operation.equals("det")) {//Determinant
				number = Find_Determinant(list1);
				list3.add(new LinkedList<Float>());
				list3.get(0).add(number);
			}
		//End Operations
		
		/*//Testing
		for(int i = 0; i < list3.size(); i++) {	//Prints list3
			for(int j = 0; j < list3.get(i).size(); j++) {
				//System.out.printf("%5s" , list3.get(i).get(j));//I Feel like theres a pattern for the higher numbers... leave a blank space infront of highest number?
				System.out.print(list3.get(i).get(j) + " ");
			}
			System.out.println("");
		}*/
		
		//Output
		if(Other_Operation) {
			FileWriter x = new FileWriter(Input_File2);
			BufferedWriter z = new BufferedWriter(x);
			for(int i = 0; i < list3.size(); i++){
				for(int j = 0; j < list3.get(i).size(); j++){
					z.write(list3.get(i).get(j) + " ");
				}
				z.newLine();
			}
			z.close();
		} else {
			FileWriter x = new FileWriter(Output_File); //+ "_" + Operation.toString() );
			BufferedWriter z = new BufferedWriter(x);
			for(int i = 0; i < list3.size(); i++) {
				for(int j = 0; j < list3.get(i).size(); j++) {
					z.write(list3.get(i).get(j) + " ");
				}
				z.newLine();
			}
			z.close();
		}
	}
	
	
	public static Float Find_Determinant(LinkedList<LinkedList> lista) {//Used for Determinant
		
		float sum = (float) 0.0;
		int sign;
		
		if(lista.size() == 1) {
			return (float)lista.get(0).get(0);
		}
		for(int i = 0; i < lista.size(); i++) {
			LinkedList<LinkedList> listb = new LinkedList<LinkedList>();
			for(int e = 0; e < lista.size() - 1; e++) {//Initialize Positions
				listb.add(new LinkedList<Float>());
				for(int r = 0; r < lista.get(e).size() -1; r++) {
					listb.get(e).add(0);
				}
			}
			for(int a = 1; a < lista.size(); a++) {//Set Positions
				for(int b = 0; b < lista.size(); b++) {
					if(b < i) {
						listb.get(a - 1).set(b, lista.get(a).get(b));
					} else if(b > i) {
						listb.get(a - 1).set(b - 1, lista.get(a).get(b));
					}
				}
			}
			if(i % 2 ==0) {
				sign = 1;
			} else {
				sign = -1;
			}
			sum += sign * (float) lista.get(0).get(i) * (Find_Determinant(listb));
		}
		return sum;	
	}
}
