package at.badkey.formen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Paralellogramm extends Rechteck {
	protected int alpha;


	public Paralellogramm(int X, int Y, int A, int B, int Alpha, Color Color) {
		super(X, Y, A, B, Color);
		alpha = Alpha;

	}

	public void generate(Graphics g) {
		g.setColor(color);
		int x1 = x;
		int y1 = y;

		int x2 = (int) (x1 + a);
		int y2 = (int) (y1);

		int x3 = (int) (x2 - (b * Math.cos(2 * Math.PI - degreeToRad(alpha))));
		int y3 = (int) (y2 + (b * Math.sin(2 * Math.PI - degreeToRad(alpha))));

		int x4 = (x3 - a);
		int y4 = y3;

		int xpoints[] = { x1, x2, x3, x4 };
		int ypoints[] = { y1, y2, y3, y4 };
		int npoints = 4;

		Polygon polygon = new Polygon(xpoints, ypoints, npoints);
		g.fillPolygon(polygon);
		g.drawPolygon(polygon);
		
		//Calculate scope and area
		for(int i = 0; i < npoints/2; i++) {
			//Scope
			x1 = xpoints[i*2];
			y1 = ypoints[i*2];
			x2 = xpoints[i*2+1];
			y2 = ypoints[i*2+1];
			
			int DeltaX = x2 - x1;
			int DeltaY = y2 - y1;

			scope += (float) Math.sqrt(DeltaX ^ 2 + DeltaY ^ 2);
			 
			//Area
			area += ((x1*y2-y1*x2));
		}
		
		area /=2;
		area = Math.abs(area);
	}

}
