package mountain;

public class Point {
	private double x, y;

	/** Constructs and initializes a point at the specified (x,y) location. */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate.
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		} else {
			return false;
		}
	}

}
