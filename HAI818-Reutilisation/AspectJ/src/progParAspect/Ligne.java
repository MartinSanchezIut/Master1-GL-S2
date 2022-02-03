package progParAspect;

public class Ligne implements Figure{
	
	private Point a, b;

	public Ligne(Point a, Point b) {
		super();
		this.a = a;
		this.b = b;
	}

	
	@Override
	public String printFigure() {
		return "Ligne [" + a + "; " + b + "]";
	}


	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public void setB(Point b) {
		this.b = b;
	}
	
	

}
