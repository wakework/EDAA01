package fractal;


import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Koch(300);
		Point p1 = new Point(200, 400);
		Point p2 = new Point(300, 200);
		Point p3 = new Point(400, 450);
		fractals[1] = new Mountain(p1, p2, p3, 10);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
