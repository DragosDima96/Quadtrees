

public class Shape {

	double a1, a2, a3,a4;
	double b1, b2, b3,b4;
	double x1, x2, y1, y2;
	int ID;
	String figura;

	public Shape(double x1, double y1, double x2, double y2) {

		this.x1 = Math.min(x1, x2);
		this.x2 = Math.max(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.y2 = Math.max(y1, y2);
		this.figura = "dreptunghi";
	}

	public Shape(double d, double x1, double y1) {
		this.x1 = x1 - d;
		this.x2 = x1 + d;
		this.y1 = y1 - d;
		this.y2 = y1 + d;
		this.figura = "cerc";
	}

	public Shape(double x1, double y1, double x2, double y2, double x3, double y3) {

		a1 = x1;
		a2 = x2;
		a3 = x3;
		b1 = y1;
		b2 = y2;
		b3 = y3;
		this.x1 = Math.min(Math.min(x1, x2), x3);
		this.x2 = Math.max(Math.max(x1, x2), x3);
		this.y1 = Math.min(Math.min(y1, y2), y3);
		this.y2 = Math.max(Math.max(y1, y2), y3);
		this.figura = "triunghi";
	}

	public Shape(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		a1 = x1;
		a2 = x2;
		a3 = x3;
		b1 = y1;
		b2 = y2;
		b3 = y3;
		a4 = x4;
		b4 = y4;
		this.x1 = Math.min(Math.min(x1, x2), Math.min(x3, x4));
		this.x2 = Math.max(Math.max(x1, x2), Math.max(x3, x4));
		this.y1 = Math.min(Math.min(y1, y2), Math.min(y3, y4));
		this.y2 = Math.max(Math.max(y1, y2), Math.max(y3, y4));
		this.figura = "romb";
	}

}
