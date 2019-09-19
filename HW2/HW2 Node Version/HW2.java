import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//Main
public class HW2 {

	public static void main(String[] args) throws Exception{
		
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
		
		Linked_List mat1 = new Linked_List();
		Linked_List mat2 = new Linked_List();
		Linked_List mat3 = new Linked_List();
		
		Operation = args[0];
		Input_File1 = args[1];
		Input_File2 = args[2];
		
		try {//The only case that could throw an error
			Output_File = args[3];
		}	catch (Exception E){
			Other_Operation = true;
			
		}
		
		mat1 = read(Input_File1);
		if(!Other_Operation) {
			mat2 = read(Input_File2);
		}
		
		
		Float number;
		number = (float) 0.0;
		LinkedListNode node = null;

		//Begin Operations
			if(Operation.equals("add")) {//Add
				mat3.set_Rows(mat1.get_Rows());
				mat3.set_Cols(mat1.get_Cols());
				for(int i = 0; i < mat1.get_Rows(); i++) {
					
					for(int j = 0; j < mat1.get_Cols(); j++) {
						node = new LinkedListNode();
						node.set_Value(mat1.Find(i,j) + mat2.Find(i,j));
						mat3.append(node);
					}
				}

			} else if(Operation.equals("sub")) {//Subtract
				mat3.set_Rows(mat1.get_Rows());
				mat3.set_Cols(mat1.get_Cols());
				for(int i = 0; i < mat1.get_Rows(); i++) {
					
					for(int j = 0; j < mat1.get_Cols(); j++) {
						node = new LinkedListNode();
						node.set_Value(mat1.Find(i,j) - mat2.Find(i,j));
						mat3.append(node);
					}
				}
				
			} else if(Operation.equals("mul")) {//Matrix Multiplication
				mat3.set_Rows(mat1.get_Rows());
				mat3.set_Cols(mat1.get_Cols());
				for(int i = 0; i < mat1.get_Rows(); i++) {//Goes through by rows of mat1
					for(int j = 0; j < mat1.get_Cols(); j++) {//Goes through each col of mat1
						node = new LinkedListNode();
						for(int k = 0; k < mat2.get_Rows(); k++) {//Goes through Rows of mat2
							number += mat1.Find(i  , k ) * mat2.Find(k  , j );
						}
						node.set_Value(number);
						mat3.append(node);
						number = (float) 0.0;
					}
				}
			}else if(Operation.equals("tra")) {//Transpose
				
				for(int i = 0; i < mat1.num_nodes; i++) {//Fill in mat3 with 0's
					mat3.append(new LinkedListNode());
					mat3.get_Node(i).set_Value(0);
				}
				mat3.set_Rows(mat1.get_Rows());
				mat3.set_Cols(mat1.get_Cols());
				mat3.Reset_RC();
				
				for(int i = 0; i < mat1.get_Rows(); i++) {
						for(int j = 0; j < mat1.get_Cols(); j++) {
							number = mat1.Find(j , i);
							mat3.get_Node_RC(i , j).set_Value(number);
					}
				}
				
			} else if(Operation.equals("det")) {//Determinant
				node = new LinkedListNode();
				node.set_Value(Find_Determinant(mat1));
				mat3.append(node);
				mat3.set_Rows(1);
				mat3.set_Cols(1);
			}
		//End Operations
			
		mat3.Reset_RC();//Sets rows and cols so no error is thrown
			
			//Output
			if(Other_Operation) {
				FileWriter x = new FileWriter(Input_File2);
				BufferedWriter z = new BufferedWriter(x);
				for(int i = 0; i < mat3.get_Rows(); i++){
					for(int j = 0; j < mat3.get_Cols(); j++){
						z.write(mat3.Find(i, j) + " ");
					}
					z.newLine();
				}
				z.close();
			} else {
				FileWriter x = new FileWriter(Output_File);
				BufferedWriter z = new BufferedWriter(x);
				for(int i = 0; i < mat3.get_Rows(); i++) {
					for(int j = 0; j < mat3.get_Cols(); j++) {
						z.write(mat3.Find(i, j) + " ");
					}
					z.newLine();
				}
				z.close();
			}
	}
	
	public static Linked_List read(String in_filename) throws Exception{
		Linked_List mat = new Linked_List();
		BufferedReader reader = new BufferedReader(new FileReader(in_filename));
		String line;
		while((line = reader.readLine()) != null) {
			
			String[] everything = line.split(" ");
			for(int i = 0; i < everything.length; i++) {
				LinkedListNode a = new LinkedListNode();
				a.set_Value(Float.parseFloat(everything[i]));
				a.set_Row(mat.get_Rows());
				mat.append(a);
				if(mat.get_Rows() == 0) {
					mat.add_Col();//x
				}
			}	
			mat.add_Row();//y
		}
		for(int i = 0; i < mat.num_nodes; i++) {
			mat.get_Node(i).set_Col(i % mat.get_Rows());
		}
		reader.close();
		return mat;
	}
	
	public static Float Find_Determinant(Linked_List mat) {//Used for Determinant
		
		float sum = (float) 0.0;
		int sign;
		
		if(mat.num_nodes == 1) {
			return mat.get_Element(0);
		}
		if(mat.num_nodes == 0) {
			return null;
		}
		
		
		
		for(int i = 0; i < mat.get_Cols(); i++) {
			Linked_List mat2 = new Linked_List();
			mat2.set_Cols(mat.get_Cols() - 1);
			mat2.set_Rows(mat.get_Rows() - 1);
			
			for(int e = 0; e < mat.get_Cols() - 1; e++) {//Initialize Positions
				
				for(int r = 0; r < mat.get_Rows() -1; r++) {
					LinkedListNode a = new LinkedListNode();
					a.set_Value(0);
					mat2.append(a);
				}
			}
			mat2.Reset_RC();
			
			
			for(int a = 1; a < mat.get_Cols(); a++) {//Set Positions
				for(int b = 0; b < mat.get_Rows(); b++) {
					if(b < i) {
						LinkedListNode temp = mat.get_Node_RC(a , b);
						mat2.get_Node_RC(a - 1, b).set_Value(temp.value);
					} else if(b > i) {
						LinkedListNode temp = mat.get_Node_RC(a , b);
						mat2.get_Node_RC(a - 1, b - 1).set_Value(temp.value);
					}
					
				}
			}
			mat2.Reset_RC();
			if(i % 2 ==0) {
				sign = 1;
			} else {
				sign = -1;
			}
			sum += sign * (float) mat.Find(0, i) * (Find_Determinant(mat2));
		}
		return sum;	
	}
}

	


