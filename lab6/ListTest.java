public class ListTest{
	public static void main(String[] args){
		List<String> A= new List<String>();
		List<String> compare = new List<String>();
		System.out.println(A.isEmpty());
		System.out.println(A.size());

		A.add(1,"Hello");
		System.out.println(A.isEmpty());
		System.out.println("Size = " + A.size());

		A.add(2,"Whats up");
		System.out.println("Size = " + A.size());
		System.out.println(A.get(1));

		A.remove(1);
		System.out.println(A.get(1));

		A.add(2,"yeee");
		A.add(3,"third");
		System.out.println(A);
		System.out.println("Size = " + A.size());

		//A.removeAll();
		//System.out.println("Size = " + A.size());

		compare.add(1,"adsf");
		compare.add(2,"yeee");

		System.out.println(A.get(1).equals(compare.get(2)));
		System.out.println(A.get(2).equals(compare.get(2)));
	}
}