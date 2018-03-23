package at.badkey.formen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Deltoid extends Rechteck {
	protected int alpha;

	public Deltoid(int X, int Y, int A, int B, int Alpha, Color Color) {
		super(X, Y, A, B, Color);
		alpha = Alpha;

	}

	public void generate(Graphics g) {
		g.setColor(color);
		int x1 = x;
		int y1 = y;

		int x2 = (int) (x1 + a / 2);
		int y2 = (int) (y1 + (a / 2) / Math.tan(degreeToRad(alpha)));

		int x3 = (int) (x1);
		int y3 = (int) (y1 + b);

		int x4 = x2 - a;
		int y4 = y2;

		int xpoints[] = { x1, x2, x3, x4 };
		int ypoints[] = { y1, y2, y3, y4 };
		int npoints = 4;

		Polygon polygon = new Polygon(xpoints, ypoints, npoints);
		g.fillPolygon(polygon);
		g.drawPolygon(polygon);
		
		scope += (float) (Math.sqrt((x2-x1)^2 + (y2-y1)^2) + Math.sqrt((x3-x2)^2 + (y3-y1)^2) + Math.sqrt((x4-x3)^2+(y4-y3)^2) + Math.sqrt((x1-x4)^2+(y1-y4)^2));
		
		//Calculate scope and area
		for(int i = 0; i < (npoints/2); i++) {
			//Area
			area += ((x1*y2-y1*x2));
		}
				
		area /=2;
		area = Math.abs(area);
	}

}
