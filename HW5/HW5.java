import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class HW5 {

	public static void main(String[] args) throws IOException {
		
		int part = Integer.parseInt(args[0]);
		String equation = args[1];
		String output = args[2];
		
		FileWriter a = new FileWriter(output);
		BufferedWriter theFile = new BufferedWriter(a);
		
		StackClass solver = new StackClass();
		
		if(part == 2) {//Convert equation into postfix form
			solver = toPostFix(equation);
			solver = solver.reverse(solver);
			String answer = solver.toString(solver);
			System.out.println(answer);
			theFile.write(answer);
			theFile.close();
		}
		if(part == 3) {//Solve postfix equation
			//Check input if in postfix form
			//Evaluate postfix
			String answer = "";
			answer = solvePostFix(equation);
			System.out.println(answer);
			theFile.write(answer);
			theFile.close();
		}

	}
	
	

	static StackClass toPostFix(String equation) {
		StackClass operators = new StackClass();
		StackClass postFix = new StackClass();
		
		Dictionary<String, Integer> opValues = new Hashtable<String, Integer>();
			opValues.put("-", 1);
			opValues.put("+", 1);
			opValues.put("*", 3);
			opValues.put("/", 3);
			opValues.put("^", 5);
			opValues.put("(", 6);
			opValues.put(")", 7);
		
		String[] arEquation = equation.split("");
		//Operators in rank Low -,+,*,/,^,() High
		for(String i : arEquation) {
			//add numbers to stack
			//Check priority operators
			
			if(isInteger(i)) {//Order of number are never changed
				//System.out.println(i);
				postFix.add(i);
			} else {//Operators have positions changed
				//System.out.println(i);

				if(operators.size() == 0 || opValues.get(i) > opValues.get(operators.top()) ) {//If operator is greater than other operator
					if(opValues.get(i) <= 5) {//Not ( or )
						operators.add(i);
					}
					
				} else {
					if(opValues.get(i) <= 5) {//Not ( or )
						while(operators.size() > 0 && opValues.get(i) <= opValues.get(operators.top()) ) {
							postFix.add(operators.pop());
						}
						operators.add(i);
					}
				}
				
				//
				//if(opValues.get(i) == 6){//If char is "("
				//	operators.add(i);
				//}

				if(opValues.get(i) == 7){//If char is ")"
					while(operators.size() > 0 && opValues.get(operators.top()) != 6) {
						postFix.add(operators.pop());
					}
					//operators.pop(); //Gets rid of spare "("
				} 
			}
		}
		
		while(operators.size() > 0) {
			postFix.add(operators.pop());
		}
		
		return postFix;
	}
	
	private static String solvePostFix(String equation) {
		
		//Step 1: Check if input follows true postfix
		int operandCount = 0;
		int operatorCount = 0;
		String[] arEquation = equation.split("");
		for(String i : arEquation) {
			if(isInteger(i)) {
				operandCount += 1;
			} else {
				operatorCount += 1;
			}
		}
		if(operandCount != (operatorCount + 1)) {
			return "nv";
		} else if(arEquation.length > 1 && !isInteger(arEquation[0])){//If first item is not a number
			return "nv";
		}
		
		//Step 2: Evaluate the postfix expression
		
		//There are no "(" or ")"
		StackClass holder = new StackClass();
		StackClass operands = new StackClass(); //Holds spare operands
		Double item1 = 0.0;
		Double item2 = 0.0;
		
		for(String i : arEquation) {//Add items to stack
			holder.add(i);
		}
		holder = holder.reverse(holder);
		while(holder.size() > 0) {
			if(isInteger(holder.top())) {
				operands.add(holder.pop());
			} else {
				item2 = Double.parseDouble(operands.pop());
				item1 = Double.parseDouble(operands.pop());
				if(holder.top().equals("+")) {
					item1 += item2;
				} else if(holder.top().equals("-")) {
					item1 -= item2;
				} else if(holder.top().equals("*")) {
					item1 *= item2;
				} else if(holder.top().equals("/")) {
					item1 /= item2;
				} else if(holder.top().equals("^")) {
					item1 = Math.pow(item1, item2);
				}
				holder.pop();
				operands.add(item1.toString());
			}
		}
		Double ans = Double.parseDouble(operands.pop());
		ans = Math.round(ans * 10) / 10.0;
		return ans.toString();
	}
	
	static boolean isInteger(String a) {
		boolean isValid = false;
		try {
			Integer.parseInt(a);
			isValid = true;
		} catch(NumberFormatException f) {
			
		}
		return isValid;
	}

}
