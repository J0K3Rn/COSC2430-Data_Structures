import java.util.ArrayList;

public class StackClass {

	ArrayList<String> stackList = new ArrayList<String>();
	
	String top() {
		return stackList.get(stackList.size() - 1);
	}
	String pop() {
		String temp = stackList.get(stackList.size() - 1);
		stackList.remove(stackList.size() - 1);
		return (temp);
	}
	void add(String element) {
		stackList.add(element);
	}
	int size() {
		return stackList.size();
	}
	
	StackClass reverse(StackClass a) {
		StackClass temp = new StackClass();
		while(a.size() > 0) {
			temp.add(a.pop());
		}
		return temp;
	}
	
	String toString(StackClass a) {
		String equation = "";
		while(a.size() > 0) {
			equation += a.pop();
		}
		return equation;
	}
	
	
}
