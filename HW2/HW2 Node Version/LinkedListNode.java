
public class LinkedListNode {
	
	LinkedListNode next = null;//Points to next node
	LinkedListNode prev = null;//Points to previous node
	float value;
	int row = 0;
	int col = 0;
	
	public int get_Row() {
		return row;
	}
	public int get_Col() {
		return col;
	}
	public void set_Row(int r) {
		row = r;
	}
	public void set_Col(int c) {
		col = c;
	}
	
	void add_next(LinkedListNode new_node) {
		next = new_node;
	}
	void add_prev(LinkedListNode new_node) {
		prev = new_node;
	}
	LinkedListNode get_next() {
		return next;
	}
	LinkedListNode get_prev() {
		return prev;
	}
	void set_Value(float v) {
		value = v;
	}
}
