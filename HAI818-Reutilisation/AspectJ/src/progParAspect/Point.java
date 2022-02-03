package progParAspect;

public class Point implements Figure{

	private int x, y;
		
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String printFigure() {
		return "Point [" + x + "; " + y + "]";
	}
}
