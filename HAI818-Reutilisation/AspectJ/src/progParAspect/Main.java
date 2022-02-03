package progParAspect;

public class Main {

	
	public static void main(String[] args) {
		
		Point a = new Point(1, 2);
		Point b = new Point(4, 2);
		Point c = new Point(6, 1);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		
		Ligne l = new Ligne(a, b);
		System.out.println(l);
		
		Ligne ll = new Ligne(c, b);
		System.out.println(ll);
	}
}
