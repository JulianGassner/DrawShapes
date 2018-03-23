package at.badkey.formen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Trapez extends Rechteck{
	protected int alpha;
	protected int beta;
	protected int c;
	

	public Trapez(int X, int Y, int A, int B, int C, int Alpha, int Beta, Color Color) {
		super(X, Y, A, B, Color);
		alpha = Alpha;
		beta = Beta;
		c = C;

	}

	public void generate(Graphics g) {
		g.setColor(color);
		float b = (float)((a * Math.sin(degreeToRad(alpha)) - c * Math.sin(degreeToRad(alpha))) / Math.sin(degreeToRad(180 - alpha - beta)));
        float h = b * (float)Math.sin(degreeToRad(beta));
		
		int x1 = x;
		int y1 = y;

		int x2 = (int) (x1 + c);
		int y2 = (int) (y1);

		int x3 = (int) (x + c + (h / (float)Math.tan(degreeToRad(beta))));
		int y3 = (int) (y1 + h);

		int x4 = (int) (x - (h / (float)Math.tan(degreeToRad(alpha))));
		int y4 = (int) (y1 + h);

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
