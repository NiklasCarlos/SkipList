package de.tuberlin.ise.prog1;

public class SNode<T> {

	private int index = nextIndex++; // gives every INode a new Indexnumber
	private static int nextIndex = 0;

	T value;
	SNode<T> next;

	public SNode(T value, SNode<T> next) {
		this.value = value;
		this.next = next;
	}

	public SNode(T value) {
		this(value, null);
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndextoZero() {
		this.index = 0;
		SNode.nextIndex = 0;
	}

}
