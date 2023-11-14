package mountain;

import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
import java.util.Map;
//import java.util.Set;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point a, b, c;
//	private LinkedList<Side> list;
	private double dev;
	private HashMap<Side, Point> map;

	/**
	 * Creates an object that handles Koch's fractal.
	 * 
	 * @param p1, p2, p3 are the three points of the triangle
	 */
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
//		list = new LinkedList<Side>();
		map = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return "Bergsfraktalen";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle the turtle graphic object.
	 */
	@Override
	public void draw(TurtleGraphics turtle) {
		mountainLine(turtle, order, a, b, c, dev);
	}

	/**
	 * Private method, used in draw.
	 * 
	 * @param turtle the turtle graphic object, the points and dev.
	 */
	private void mountainLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {

		double dev1 = RandomUtilities.randFunc(dev);
		double dev2 = RandomUtilities.randFunc(dev);
		double dev3 = RandomUtilities.randFunc(dev);

//		Point a1 = getMiddlePoint(a, b, dev1);
//		Point a2 = getMiddlePoint(b, c, dev2);
//		Point a3 = getMiddlePoint(a, c, dev3);

		Point a1 = getMPoint(a, b, dev1);
		Point a2 = getMPoint(b, c, dev2);
		Point a3 = getMPoint(a, c, dev3);

		// basfall
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			turtle.penUp();
		} else {
			// middle
			mountainLine(turtle, order - 1, a1, a2, a3, dev / 2);
			// down left
			mountainLine(turtle, order - 1, a, a1, a3, dev / 2);
			// top
			mountainLine(turtle, order - 1, a1, b, a2, dev / 2);
			// down right
			mountainLine(turtle, order - 1, a3, a2, c, dev / 2);
		}

	}

	/**
	 * Private method for finding the the middle point between two points.
	 * 
	 * @param the two points and dev.
	 * @return the middle point.
	 */
//	private Point getMiddlePoint(Point b1, Point b2, double dev) {
//		Iterator<Side> itr = list.iterator();
//		while (itr.hasNext()) {
//			Side temp = itr.next();
//			if ((temp.getP1().equals(b1) && temp.getP2().equals(b2))
//					|| (temp.getP1().equals(b2) && temp.getP2().equals(b1))) {
//				itr.remove();
//				return temp.getPM();
//			}
//		}
//		Side n = new Side(b1, b2, dev);
//		list.add(n);
//		return n.getPM();
//	}

	private Point getMPoint(Point b1, Point b2, double dev) {

		Side temp = new Side(b1, b2, dev);

		for (Map.Entry<Side, Point> mapElement : map.entrySet()) {
			if (mapElement.getKey().equals(temp)) {
				return mapElement.getKey().getPM();
			}
		}

		Side n = new Side(b1, b2, dev);
		map.put(n, n.getPM());
		return n.getPM();
	}
}
