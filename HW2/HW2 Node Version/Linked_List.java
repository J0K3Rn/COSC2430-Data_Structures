
public class Linked_List {
	LinkedListNode head = null;
	LinkedListNode tail = null;
	
	int rows = 0;
	int cols = 0;
	
	
	int num_nodes = 0;
	
	public void append(LinkedListNode new_node) {//Add node to matrix. If no head add head. Add and update tail
		if(head == null) {
			head = new_node;
		} else {
			tail.add_next(new_node);
		}
		new_node.add_prev(tail);
		tail = new_node;
		num_nodes++;
	}
	
	
	public void add_Col() {
		cols += 1;
	}
	public int get_Cols() {
		return cols;
	}
	public void add_Row() {
		rows += 1;
	}
	public int get_Rows() {
		return rows;
	}
	public void set_Rows(int r) {
		rows = r;
	}
	public void set_Cols(int c) {
		cols = c;
	}
	
	
	LinkedListNode get_Node(int index) {//Returns node at index
		LinkedListNode current = head;
		for(int ii = 0; ii < index; ii++) {
			current = current.get_next();
		}
		return current;
	}
	LinkedListNode get_Node_RC(int row, int col) {//Returns the node at row, col
		LinkedListNode current = head;
		for(int i = 0; i < num_nodes; i++) {
			if(current.get_Col() == col && current.get_Row() == row) {
				break;
			}
			current = current.get_next();
		}
		return current;
	}
	
	float get_Element(int index) {//Return element at index
		LinkedListNode current = head;
		for(int ii = 0; ii < index; ii++) {
			current = current.get_next();
		}
		return current.value;
	}
	
	float Find(int row, int col) {//Find node at position row, col and returns its value
		LinkedListNode current = head;
		
		for(int i = 0; i < num_nodes; i++) {
			if(current.get_Col() == col && current.get_Row() == row) {
				break;
			}
			current = current.get_next();
		}
		return current.value;
	}
	
	void Reset_RC() {//Resets Rows and Columns
		LinkedListNode current = head;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				current.set_Col(j);
				current.set_Row(i);
				current = current.get_next();
			}
		}
	}
	
	
}
