

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Quadtree {
	int level;
	Shape[] form;
	Rectangle bounds;
	Quadtree[] node;
/**
 * Constructorul clasei,aloca memorie pentru 4 fii,instantieaza vectorul de figuri,si seteaza boundurile fiilor
 */
	
	public Quadtree(int new_level, Rectangle new_bounds) {
		level = new_level;
		bounds = new_bounds;
		node = new Quadtree[4];
		form = new Shape[1600];
	}
/**
 * Functie care instantieaza cei 4 fii si calculeaza boundurile.
 * 
 */
	

	public void split() {

		double a, b, c, d;
		a = bounds.x1;
		b = bounds.x2;
		c = bounds.y1;
		d = bounds.y2;

		double mijx = (a + b) / 2;
		double mijy = (c + d) / 2;
		node[0] = new Quadtree(level + 1, new Rectangle(mijx, mijy, b, d));
		node[1] = new Quadtree(level + 1, new Rectangle(a, mijy, mijx, d));
		node[2] = new Quadtree(level + 1, new Rectangle(a, c, mijx, mijy));
		node[3] = new Quadtree(level + 1, new Rectangle(mijx, c, b, mijy));

	}
/**
 * O sa returneze un numar in care fiecare cifra reprezinta un cadran in care se afla.(ex  :pt toate cele 4 avem:3210)
 */
	
	public int getIndex(Shape to_insert) {

		int index = -1;

		double a = to_insert.x1;
		double b = to_insert.x2;
		double c = to_insert.y1;
		double d = to_insert.y2;

		double x1 = bounds.x1;
		double x2 = bounds.x2;
		double y1 = bounds.y1;
		double y2 = bounds.y2;
		double mijx = (x1 + x2) / 2;
		double mijy = (y1 + y2) / 2;

		boolean left = false;
		boolean right = false;
		boolean left_right = false;
		boolean top = false;
		boolean bot = false;
		boolean top_bot = false;

		if (a > mijx) {
			right = true;
		}

		if (a < mijx) {
			left = true;
		}

		if (a == mijx)
			left_right = true;
		if (b == mijx)
			left_right = true;
		if (left == true)
			if (b > mijx)
				left_right = true;

		if (c > mijy)
			top = true;
		if (c < mijy)
			bot = true;
		if (c == mijy)
			top_bot = true;
		if (d == mijy)
			top_bot = true;
		if (bot == true) {
			if (d > mijy)
				top_bot = true;
		}

		if (top_bot == true) {
			if (left_right == true) {
				return 3210;
			} else if (left == true) {
				return 21;
			} else if (right == true) {
				return 30;
			}

		}
		if (left_right == true) {
			if (top_bot == true) {
				return 4;
			} else if (bot == true) {
				return 32;
			} else if (top == true) {
				return 10;
			}

		} else if (left == true) {
			if (top == true) {
				return 1;
			}

			else if (bot == true) {
				return 2;
			}
		} else if (right == true) {
			if (top == true) {
				return 0;
			} else if (bot == true) {
				return 3;
			}
		}
		return index;
	}

	/**
	 * Verifica daca punctul de coordanate x,y se intersecteaza cu un dreptunhi
	 */
	int checkRectangle(double x, double y, Shape form) {

		if (form.x1 <= x && form.x2 >= x)
			if (form.y1 <= y && form.y2 >= y)
				return 1;

		return 0;
	}
/**
 *  Verifica daca punctul de coordanate x,y se intersecteaza cu un cerc
 */
	int checkCircle(double x, double y, Shape form) {

		double R = (form.x2 - form.x1) / 2;
		double mijx = form.x1 + R;
		double mijy = form.y1 + R;
		double f1 = Math.pow(x - mijx, 2);
		double f2 = Math.pow(y - mijy, 2);

		if (f1 + f2 <= Math.pow(R, 2))
			return 1;
		return 0;

	}
	/**
	 *  Verifica daca punctul de coordanate x,y se intersecteaza cu un triunghi.
	 *  Functia aceasta am gasit-o pe internet.
	 */
	boolean PointInTriangle(double x, double y, Shape p) {
		double b1, b2, b3;

		b1 = ((x - p.a3) * (p.b2 - p.b3) - (p.a2 - p.a3) * (y - p.b3));
		b2 = ((x - p.a2) * (p.b1 - p.b2) - (p.a1 - p.a2) * (y - p.b2));
		b3 = ((x - p.a1) * (p.b3 - p.b1) - (p.a3 - p.a1) * (y - p.b1));
		boolean a1 = b1 < 0.0f;
		boolean a2 = b2 < 0.0f;
		boolean a3 = b3 < 0.0f;
		return ((a1 == a2) && (a2 == a3));
	}
	/**
	 * Verifica daca un punct se afla intr-un triunghi.
	 */
	
	boolean PointInTriangle1(double x, double y, Shape p) {
		double b1, b2, b3;

		b1 = ((x - p.a4) * (p.b2 - p.b4) - (p.a2 - p.a4) * (y - p.b4));
		b2 = ((x - p.a2) * (p.b1 - p.b2) - (p.a1 - p.a2) * (y - p.b2));
		b3 = ((x - p.a1) * (p.b4 - p.b1) - (p.a4 - p.a1) * (y - p.b1));
		boolean a1 = b1 < 0.0f;
		boolean a2 = b2 < 0.0f;
		boolean a3 = b3 < 0.0f;
		return ((a1 == a2) && (a2 == a3));
	}
	/**
	 * Verifica daca un punct se afla intr-un triunghi.
	 */
	boolean PointInTriangle2(double x, double y, Shape p) {
		double b1, b2, b3;

		b1 = ((x - p.a4) * (p.b2 - p.b4) - (p.a2 - p.a4) * (y - p.b4));
		b2 = ((x - p.a2) * (p.b3 - p.b2) - (p.a3 - p.a2) * (y - p.b2));
		b3 = ((x - p.a3) * (p.b4 - p.b3) - (p.a4 - p.a3) * (y - p.b3));
		boolean a1 = b1 < 0.0f;
		boolean a2 = b2 < 0.0f;
		boolean a3 = b3 < 0.0f;
		return ((a1 == a2) && (a2 == a3));
	}
	 /**
	  *  Verifica daca punctul de coordanate x,y se intersecteaza cu un romb apeland functia pentru triunghi de 2 ori
	  */
	
	boolean checkRomb(double x, double y, Shape p) {
		boolean a = PointInTriangle1(x, y, p);
		boolean b = PointInTriangle2(x, y, p);
		return a | b;

	}
/**
 * Aceasta functie verifica daca 2 dreptunghiuri se intersecteaza
 */

	int collide(Shape a, Shape b) {

		double x1 = a.x1;
		double y1 = a.y2;
		double x2 = a.x2;
		double y2 = a.y1;

		double a1 = b.x1;
		double b1 = b.y2;
		double a2 = b.x2;
		double b2 = b.y1;

		if (x1 <= a2)
			if (x2 >= a1)
				if (y1 >= b2)
					if (y2 <= b1)
						return 1;
		return 0;
	}
/**
 * Functia care imi insereaza recursiv un element in quadtree.
 */
	
	public void insert(Shape to_insert, Quadtree quad, int k) {

		int index1;
		int index2;

		if (quad.node[0] == null) {

			if (quad.form[0] == null) {
				quad.form[0] = to_insert;
				quad.form[0].ID = k;
			} else {
				int j = 0;
				int i = 0;
				while (quad.form[i] != null) {
					j = i;
					if (collide(to_insert, quad.form[i]) == 1) {
						while (quad.form[j] != null)
							j++;
						quad.form[j] = to_insert;
						quad.form[j].ID = k;
						return;
					}
					i++;
				}
				quad.split();
				index1 = quad.getIndex(to_insert);
				if (index1 == 0) {
					insert(to_insert, quad.node[0], k);

				} else
					while (index1 > 0) {
						insert(to_insert, quad.node[index1 % 10], k);
						index1 = index1 / 10;

					}

				i = 0;
				while (quad.form[i] != null) {
					Shape copy = quad.form[i];
					quad.form[i] = null;
					index2 = quad.getIndex(copy);
					if (index2 == 0) {
						insert(copy, quad.node[index2 % 10], copy.ID);
					}
					while (index2 > 0) {
						insert(copy, quad.node[index2 % 10], copy.ID);
						index2 = index2 / 10;
					}
					i++;
				}
			}
		} else {

			index1 = quad.getIndex(to_insert);
			if (index1 == 0) {
				insert(to_insert, quad.node[0], k);
			} else
				while (index1 > 0) {
					insert(to_insert, quad.node[index1 % 10], k);
					index1 = index1 / 10;
				}
		}

	}

	/**
	 * Functie imi afiseaza cel mai mic cadran in care se gaseste punctul de coordonate x,y
	 * Apeleaza de mai multe ori functia cadran
	 */
	Quadtree finals(double x, double y, Quadtree quad, int k,BufferedWriter writer) throws IOException {

		Quadtree quad2 = quad;
		quad = cadran(x, y, quad, k);
		if (quad == null) {
			writer.write("NIL");
			writer.newLine();
			return null;
		}
		while (quad2 != quad) {
			quad2 = quad;
			quad = cadran(x, y, quad, k + 1);
		}

		return quad;
	}

	/**
	 * Functia imi retuneaza in ce cadran,dintree fii parametrului Quad se afla un punct de coordonate x,y
	 */
	Quadtree cadran(double x, double y, Quadtree quad, int k) {

		double mijx = (quad.bounds.x1 + quad.bounds.x2) / 2;
		double mijy = (quad.bounds.y1 + quad.bounds.y2) / 2;
		if (x > quad.bounds.x2 && k == 0)
			return null;
		if (y > quad.bounds.y2 && k == 0)
			return null;

		if (quad.node[0] != null) {
			if (x <= mijx) {

				if (y <= mijy)
					return quad.node[2];
				if (y >= mijy)
					return quad.node[1];
			} else {
				if (y <= mijy)
					return quad.node[3];
				if (y >= mijy)
					return quad.node[0];

			}
		}
		return quad;
	}
	/**
	 * Aceasta functie doar apeleaza o alta functie
	 */
	
	void verificare(Quadtree quad, Shape form, Quadtree quad2) {

		check(quad, form, quad2);
	}
/**
 * Functia verifca daca o figura primita ca parametru se intersecteaza cu o alta figura din quadtree.
 * Pentru acest lucru am mers recursiv in cadranul in care s-ar introduce figura si am verificat cu figurile de acolo
 * Pentru ca functia delete nu reface cadranele este necesar sa verific si pe parcurs.
 * Cand se intersecteaza cu un element ,inserez acel element intr-o vector de figuri,care se afla intr-un alt quadtree primit ca parametru
 *
 */
	void check(Quadtree quad, Shape form, Quadtree quad2) {

		int index1;
		int i = 0;
		int j = 0;
		int ok = 0;
		if (quad.node[0] != null) {
			index1 = quad.getIndex(form);
			if (index1 == 0) {
				check(quad.node[0], form, quad2);
			} else
				while (index1 > 0) {
					check(quad.node[index1 % 10], form, quad2);
					index1 = index1 / 10;
				}

		} else {
			while (quad.form[i] != null) {

				if (collide(form, quad.form[i]) == 1) {
					j = 0;
					while (quad2.form[j] != null)
						j++;

					quad2.form[j] = quad.form[i];
					quad2.form[j].ID = quad.form[i].ID;

				}
				i++;

			}
		}
	}

	int[] sort(int[] v, int counter) {
		int c = 0;
		for (int i = 0; i < counter - 1; i++)
			for (int j = i + 1; j < counter; j++) {
				if (v[i] > v[j]) {
					c = v[i];
					v[i] = v[j];
					v[j] = c;
				}
			}

		return v;
	}
/**
 * Functia imi sterge figura cu id'ul primit ca parametru in arbore
 * Atunci cand sterge o figura,nu mai refac cadranele,chair daca este mai neeficient pentru alte functii.
 */
	
	void delete(Quadtree quad, int k) {

		int i = 0;
		int j = 0;
		if (quad.node[0] != null) {
			if (quad.form != null) {
				i = 0;
				while (quad.form[i] != null)
					i++;
				if (i != 0)
					i--;
				j = 0;
				while (quad.form[j] != null) {

					if (quad.form[j].ID == k) {
						quad.form[j] = quad.form[i];
						quad.form[i] = null;
						i--;
					}
					j++;

				}

			}
		}
		if (quad.form[0] != null) {
			i = 0;
			while (quad.form[i] != null)
				i++;
			i--;
			j = 0;

			while (quad.form[j] != null) {

				if (quad.form[j].ID == k) {
					quad.form[j] = quad.form[i];
					quad.form[i] = null;
					i--;
				}
				j++;

			}

		}

		if (quad.node[0] != null) {
			delete(quad.node[0], k);
			delete(quad.node[1], k);
			delete(quad.node[2], k);
			delete(quad.node[3], k);
		}
	}
}