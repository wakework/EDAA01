package mountain;

public class Side {
	private Point p1, p2, m;

	public Side(Point p1, Point p2, double dev) {
		this.p1 = p1;
		this.p2 = p2;
		this.m = new Point((p1.getX() + p2.getX()) / 2, ((p1.getY() + p2.getY()) / 2) + dev);
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public Point getPM() {
		return m;
	}

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			Side p = (Side) obj;
			return p1.equals(p.getP1()) && p2.equals(p.getP2()) || p1.equals(p.getP2()) && p2.equals(p.getP1());
		} else {
			return false;
		}
	}
}
