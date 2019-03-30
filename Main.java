package de.tuberlin.ise.prog1;

public class Main {

	public static void main(String[] args) {

		SkipList<Integer> list = new SkipList<>();

		list.add(1);
		System.out.println(list.toString());
		list.add(3);
		System.out.println(list.toString());
		list.add(6);
		System.out.println(list.toString());
		list.add(7);
		System.out.println(list.toString());
		list.add(12);
		System.out.println(list.toString());
		list.add(14);
		System.out.println(list.toString());
		list.add(21);
		System.out.println(list.toString());
		list.add(34);
		list.add(36);
		list.add(37);
		System.out.println(list.toString());

		System.out.printf("test get(5) should be:14 is:%d\n", list.get(5));

		System.out.printf("test contains(7) should be:true	 is:%b\n", list.contains(7));
		System.out.printf("test contains(37) should be:true is:%b\n", list.contains(37));
		System.out.printf("test contains(-1) should be:false is:%b\n", list.contains(-1));
		System.out.printf("test contains(21) should be:true	 is:%b\n", list.contains(21));
		
		
		
		System.out.printf(
				"test toString() should be:\n" + "head -> 1 -> 7 -> 21 -> 36 -> null\r\n"
						+ "head -> 1 -> 3 -> 6 -> 7 -> 12 -> 14 -> 21 -> 34 -> 36 -> 37 ->	 null\r\n" + "\nis:\n%s\n",
				list);
	}

}
