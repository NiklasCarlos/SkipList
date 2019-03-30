package de.tuberlin.ise.prog1;

public class IndexList<T> {

	static final int finalsize = 4;
	int size;

	IndexNode<T> head;

	/**
	 * methode that connects an IndexList Node with the Skiplist Node
	 * 
	 * @param partner
	 *            connection between INode and SNode;
	 */
	public void addConnection(SNode<T> partner, SNode<T> skipListhead) {
		size++;
		if (head == null) {
			head = new IndexNode<>();
			head.link = partner;
			head.index = partner.getIndex();
			head.value = partner.value;

			return;
		} else if (size <= finalsize) {
			IndexNode<T> tmp = head;

			while (tmp.next != null) {
				tmp = tmp.next;
			}

			tmp.next = new IndexNode<>();
			tmp.next.link = partner;
			tmp.next.index = partner.getIndex();
			tmp.next.value = partner.value;
			return;
		} else {

			// Update the IndexPointers
			updatePointers(partner, skipListhead);

		}

		// NICHT vergessen: INode index anpassen an neuen Snode index

	}

	/**
	 * method to update the Snode Links from the indexNodes
	 * 
	 * @param partner
	 */
	public void updatePointers(SNode<T> partner, SNode<T> skiphead) {
		
		if(SkipList.size>18){
			optimalPointer(partner, skiphead);
			return;
		}
		
		// System.out.println("letzter node wird zugewiesen");
		// letzten INode neu zuweisen

		IndexNode<T> tmplastNode = head;

		while (tmplastNode.next != null) {
			tmplastNode = tmplastNode.next;
		}

		tmplastNode.index = partner.getIndex();
		tmplastNode.link = partner;
		tmplastNode.value = partner.value;

		// System.out.println("index " + tmplastNode.index + " linkv: " +
		// tmplastNode.link.value); //für fehler analyse

		// evtl mit skiplist noch head übergeben um sl durchlaufen zu können;

		int counter = SkipList.size;
		int moveN3 = counter / 4;
		int moveN2 = counter / 4;
//		double rest = (moveN3/ 4) - moveN2;
//		System.out.println(rest + "rest");

		// zweiten IndexNode zuweisen

		IndexNode<T> tmp2Node = head.next;
//		System.out.println(tmp2Node.index + "value " + tmp2Node.value);

		SNode<T> tmp = skiphead; // durchlaufe basislist und verlinke dann 2NOde
									// der Indexliste miteinander

		while (moveN2 + 1 != 0) {
			tmp = tmp.next;
			// System.out.println(tmp.getIndex() + " index von tmp
			// node2zuweisung" + "value " + tmp.value);
			moveN2--;
		}

		tmp2Node.index = tmp.getIndex();
		tmp2Node.link = tmp;
		tmp2Node.value = tmp.value;

		// dritten IndexNode zuweisen

		IndexNode<T> tmp3Node = head.next.next;
		// System.out.println(tmp3Node.index + "value " + tmp3Node.value);
		SNode<T> tmp2 = tmp; // durchlaufe basislist und verlinke dann 3.
								// Node der Indexliste miteinander

		// iteriere solange bis snode von iN2 dann nochmal move dran hängen und
		// System.out.println(moveN3 + "moveN3");
		while (moveN3 + 1 != 0) {
			tmp2 = tmp2.next;
			// System.out.println(tmp2.getIndex() + " index von tmp
			// node3zuweisung" + "value " + tmp2.value);
			moveN3--;
		}

		tmp3Node.index = tmp2.getIndex();
		tmp3Node.link = tmp2;
		tmp3Node.value = tmp2.value;

	}

	private void optimalPointer(SNode<T> partner, SNode<T> skiphead) {
		
		//lastNode zuweisen
		IndexNode<T> tmplastNode = head;

		while (tmplastNode.next != null) {
			tmplastNode = tmplastNode.next;
		}

		tmplastNode.index = partner.getIndex();
		tmplastNode.link = partner;
		tmplastNode.value = partner.value;
		
		
	}
	
	//RestNodes aufteilen auf 2INode und 3INode
	
	int counter = SkipList.size;
	int moveN3 = counter / 4;
	int moveN2 = counter / 4;
	double rest = (moveN3/ 4) - moveN2;
	

}
