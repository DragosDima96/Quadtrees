
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
/**
 * Citesc din fisieru linie cu linie.Sparg propozitia intr-o vectori de stringuri
 * Transform primul element intr-un double.
 * Daca aceasta este 11.0 atunci mai citesc un element si in functie de acesta citesc coordonatele.Creez figura si o inserez in quadtree
 * Daca este 22.0 citesc idul,si apelez functia de stergere
 * Daca este 33.0 folosesc functia de verificare pentru fiecare figura in parte.Pun toate aceste valori intr-un vector pe care il ordonez
 * Daca este 44,creez figura,si aplica functia de gasire a cadranului in care s-ar afla.
 * 	Ultimul parametru este quadtreeul in care salvez element idu'rilor(o sa fie duplica si intr-o ordine aleeatoare)
 * Creez un vector de inturi in care le pun fara sa fie duplicate si de asemenea le ordonez,afisez vectorul
 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = null;
		double a, b, c, d, x1, x2, x3, x4, y1, y2, y3, y4;
		int v[];
		int i = 0;
		int k = 0;
		int ok = 0;
		int h;
		int counter = 0;
		int vai[];
		boolean k1;
		int valoare2;
		int sterse = 0;
		Quadtree quad4;
		String sCurrentLine;
		String[] word = new String[10000];
		br = new BufferedReader(new FileReader("quadtree.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("quadtree.out"));
		int id;
		Shape to_insert;
		sCurrentLine = br.readLine();
		word = sCurrentLine.split(" ");
		Quadtree quad2;
		a = Double.parseDouble(word[0]);
		b = Double.parseDouble(word[1]);
		c = Double.parseDouble(word[2]);
		d = Double.parseDouble(word[3]);
		Quadtree quad = new Quadtree(0, new Rectangle(a, b, c, d));
		Quadtree quad3 = new Quadtree(0, new Rectangle(a, b, c, d));
		while ((sCurrentLine = br.readLine()) != null) {
			word = sCurrentLine.split(" ");
			a = Double.parseDouble(word[0]);

			if (a == 11.0) {
				i = 0;
				valoare2 = sterse;
				b = Double.parseDouble(word[1]);
				if (b == 1.0) {
					id = Integer.parseInt(word[2]);
					x1 = Double.parseDouble(word[3]);
					y1 = Double.parseDouble(word[4]);
					x2 = Double.parseDouble(word[5]);
					y2 = Double.parseDouble(word[6]);
					to_insert = new Shape(x1, y1, x2, y2);
					quad.insert(to_insert, quad, id);
				}
				if (b == 2.0) {
					id = Integer.parseInt(word[2]);
					x1 = Double.parseDouble(word[3]);
					y1 = Double.parseDouble(word[4]);
					x2 = Double.parseDouble(word[5]);
					y2 = Double.parseDouble(word[6]);
					x3 = Double.parseDouble(word[7]);
					y3 = Double.parseDouble(word[8]);
					to_insert = new Shape(x1, y1, x2, y2, x3, y3);
					quad.insert(to_insert, quad, id);
				}
				if (b == 3.0) {
					id = Integer.parseInt(word[2]);
					x1 = Double.parseDouble(word[3]);
					y1 = Double.parseDouble(word[4]);
					x2 = Double.parseDouble(word[5]);
					to_insert = new Shape(x1, y1, x2);
					quad.insert(to_insert, quad, id);
				}
				if (b == 4.0) {
					id = Integer.parseInt(word[2]);
					x1 = Double.parseDouble(word[3]);
					y1 = Double.parseDouble(word[4]);
					x2 = Double.parseDouble(word[5]);
					y2 = Double.parseDouble(word[6]);
					x3 = Double.parseDouble(word[7]);
					y3 = Double.parseDouble(word[8]);
					x4 = Double.parseDouble(word[9]);
					y4 = Double.parseDouble(word[10]);
					to_insert = new Shape(x1, y1, x2, y2, x3, y3, x4, y4);
					quad.insert(to_insert, quad, id);
				}
			}

			if (a == 33.0) {

				counter = 0;
				vai = new int[10000];
				ok = 0;
				x1 = Double.parseDouble(word[1]);
				y1 = Double.parseDouble(word[2]);
				quad2 = quad.finals(x1, y1, quad, 0, writer);
				if (quad2 != null) {
					i = 0;
					while (quad2.form[i] != null) {
						if (quad2.form[i].figura == "dreptunghi") {
							k = quad2.checkRectangle(x1, y1, quad2.form[i]);
							if (k == 1) {
								vai[counter] = quad2.form[i].ID;
								counter++;
								ok = 1;
							}
						}
						if (quad2.form[i].figura == "cerc") {
							k = quad2.checkCircle(x1, y1, quad2.form[i]);
							if (k == 1) {
								vai[counter] = quad2.form[i].ID;
								counter++;
								ok = 1;
							}
						}
						if (quad2.form[i].figura == "triunghi") {
							k1 = quad2.PointInTriangle(x1, y1, quad2.form[i]);
							if (k1 == true) {
								vai[counter] = quad2.form[i].ID;
								counter++;

								ok = 1;
							}
						}
						if (quad2.form[i].figura == "romb") {
							k1 = quad2.checkRomb(x1, y1, quad2.form[i]);
							if (k1 == true) {

								vai[counter] = quad2.form[i].ID;
								counter++;
								ok = 1;
							}
						}
						i++;

					}

					vai = quad.sort(vai, counter);
					for (h = 0; h < counter; h++)
							if(h == counter-1)
								writer.write(vai[h]+ "");
							else
						writer.write(vai[h] + " ");
					if (counter == 0) {
						writer.write("NIL");
						writer.newLine();
					} else
						writer.newLine();
					;
				}

			}

			if (a == 44.0) {

				b = Double.parseDouble(word[1]);
				if (b == 1.0) {
					x1 = Double.parseDouble(word[2]);
					y1 = Double.parseDouble(word[3]);
					x2 = Double.parseDouble(word[4]);
					y2 = Double.parseDouble(word[5]);
					to_insert = new Shape(x1, y1, x2, y2);
					quad.verificare(quad, to_insert, quad3);

				}
				if (b == 2.0) {
					x1 = Double.parseDouble(word[2]);
					y1 = Double.parseDouble(word[3]);
					x2 = Double.parseDouble(word[4]);
					y2 = Double.parseDouble(word[5]);
					x3 = Double.parseDouble(word[6]);
					y3 = Double.parseDouble(word[7]);
					to_insert = new Shape(x1, y1, x2, y2, x3, y3);
					quad.verificare(quad, to_insert, quad3);

				}
				if (b == 3.0) {
					x1 = Double.parseDouble(word[2]);
					y1 = Double.parseDouble(word[3]);
					x2 = Double.parseDouble(word[4]);
					to_insert = new Shape(x1, y1, x2);
					quad.verificare(quad, to_insert, quad3);

				}
				if (b == 4.0) {
					x1 = Double.parseDouble(word[2]);
					y1 = Double.parseDouble(word[3]);
					x2 = Double.parseDouble(word[4]);
					y2 = Double.parseDouble(word[5]);
					x3 = Double.parseDouble(word[6]);
					y3 = Double.parseDouble(word[7]);
					x4 = Double.parseDouble(word[8]);
					y4 = Double.parseDouble(word[9]);
					to_insert = new Shape(x1, y1, x2, y2, x3, y3, x4, y4);
					quad.verificare(quad, to_insert, quad3);

				}

				h = 0;
				int j = 0;
				i = 0;
				v = new int[quad3.form.length];
				if (quad3.form[0] != null) {
					v[0] = quad3.form[0].ID;
					i = 1;
					j = 1;
					while (quad3.form[i] != null) {
						ok = 0;
						h = 0;
						while (h < j) {
							if (v[h] == quad3.form[i].ID) {
								ok = 1;
							}
							h++;
						}
						if (ok == 0) {
							v[h] = quad3.form[i].ID;
							j++;
						}
						i++;
					}
					v = quad.sort(v, j);
					for (h = 0; h < j; h++)
						if (h == j - 1)
							writer.write(v[h] + "");
						else
							writer.write(v[h] + " ");

				} else {
					writer.write("NIL");
				}
				writer.newLine();
				quad3 = new Quadtree(0, new Rectangle(a, b, c, d));
			}
			if (a == 22.0) {
				id = Integer.parseInt(word[1]);

				quad.delete(quad, id);
			}
		}
		br.close();
		writer.close();
	}

}
