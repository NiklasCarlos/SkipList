package de.tuberlin.ise.prog1;

import java.util.Collection;
import java.util.Iterator;

public class SkipList<T extends Comparable<T>> implements Collection<T> {

	SNode<T> head; // <T extends Comparable<T>>
	static int size;
	IndexList<T> expressLine = new IndexList<>();

	/**
	 * Returns the value at the specified position
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index > size) {
			System.out.println("Index exisiert nicht");
			return null;
		}

		// geht indexlist durch bis Index eins drüber(iteration auf der
		// Sekundärlist)
		IndexNode<T> tmp = expressLine.head;
		while (tmp.index < index) {
			tmp = tmp.next;
		}

		// Fehlertest
		// System.out.println(tmp.index + " value:" + tmp.value);

		// falls index treffer;
		if (tmp.index == index) {
			return tmp.value;
		}

		// vorheriger Ausgang der expressLine(iteration auf der Sekundärlist) da
		// nur ihn eine richtung iteriert werden kann
		IndexNode<T> b4tmp = expressLine.head;
		while (b4tmp.next != tmp) {
			b4tmp = b4tmp.next;
		}

		System.out.println("b4tmp " + b4tmp.index + "value " + b4tmp.value);

		// snode interation um index zu finden auf (iteration auf Primärlist)
		SNode<T> findindex = b4tmp.link;

		while (findindex.next.getIndex() != index) {
			findindex = findindex.next;
		}

		return findindex.next.value; // next hinzugefügt da while bedingung
										// letztn nicht mehr ausgibt

	}

	/**
	 * Inserts a value into the SkipList.
	 * 
	 * @param value
	 * @return
	 */
	@Override
	public boolean add(T e) {
		size++;
		if (head == null) {
			head = new SNode<T>(e);

			expressLine.addConnection(head, null);
			return true;
		}

		SNode<T> tmp = head;

		while (tmp.next != null) {

			tmp = tmp.next;
		}

		tmp.next = new SNode<T>(e);

		expressLine.addConnection(tmp.next, this.head);
		return true;

	}

	/**
	 * Checks, whether a number is contained in the list.
	 * 
	 * @param value
	 * @return boolean value whether number is contained in list
	 */
	public boolean contains(T value) {

		// zwei fälle
		// 1. fall nur number, aber was wenn number nicht geordnet?

		if (value instanceof Number) {
			IndexNode<T> tmp = expressLine.head;

			if (tmp.value.compareTo(value) > 0) {
				return false;
			}
			while (tmp.value.compareTo(value) < 0) { // tmp.value < value
				tmp = tmp.next;
			}

			if (tmp.value.equals(value)) {
				return true;
			}

			// vorheriger Ausgang der expressLine(iteration auf der
			// Sekundärlist) da
			// nur in eine richtung iteriert werden kann
			IndexNode<T> b4tmp = expressLine.head;
			while (b4tmp.next != tmp) {
				b4tmp = b4tmp.next;
			}

			// snode interation um index zu finden auf (iteration auf
			// Primärlist)
			SNode<T> findvalue = b4tmp.link;

			while (findvalue.value.compareTo(value) != 0) {
				findvalue = findvalue.next;

				if (findvalue.next == null) {
					return false;
				}

			}

			return true;

		}

		// 2. beliebiges obj wie z.b. String , wenn wie in Hausaufgabe
		// beschrieben andererseits

		if (value instanceof String) {

			SNode<T> tmp = head;
			while (tmp.next != null) {
				if (tmp.value.equals(value)) {
					return true;
				}

				tmp = tmp.next;

			}
		}
		// evtl return false mit else einfügen

		return false;
	}

	/**
	 * removes all elements form the list
	 */
	@Override
	public void clear() {
		head = null;
		expressLine.head = null;
		SkipList.size = 0;
		head.setIndextoZero(); // setzt Snode index wieder auf null

	}

	/**
	 * @return the size of the list
	 */
	@Override
	public int size() {

		return SkipList.size;
	}

	/**
	 * Fehler da letzter node nicht ausgegeben wird
	 */
	@Override
	public String toString() {

		String Indexlist = "head ->";
		IndexNode<T> tmp = expressLine.head;
		while (tmp.next != null) {
			Indexlist = Indexlist + "" + tmp.link.value + "-> ";
			tmp = tmp.next;
		}
		String listall = "head -> ";
		SNode<T> tmpall = head;
		while (tmpall.next != null) {
			listall = listall + "" + tmpall.value + "-> ";

			tmpall = tmpall.next;

		}

		// tmpall am ende mit return da sonst letzte zahl fehlt
		return "\n  indexList: " + Indexlist + tmp.value + "-> " + " null" + " \n Basislist: " + listall + tmpall.value
				+ "-> " + " null";
	}

	// do not change the following methods

	@Override
	public boolean isEmpty() {
		return size() <= 0;
	}

	@Override
	public boolean contains(Object o) {
		try {
			T casted = (T) o;
			return contains(casted);
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	@Deprecated
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c) {
			add(t);
		}
		return true;
	}

	@Override
	@Deprecated
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	@Deprecated
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// really slow way to implement this but should be some what
		// understandable
		return new Iterator<T>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < size();
			}

			@Override
			public T next() {
				return get(index++);
			}
		};
	}

	@Override
	public Object[] toArray() {
		Object[] obj = new Object[size()];
		for (int i = 0; i < size(); i++) {
			obj[i] = get(i);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X[] toArray(X[] a) {
		for (int i = 0; i < size(); i++) {
			a[i] = (X) get(i);
		}
		return a;
	}

}
