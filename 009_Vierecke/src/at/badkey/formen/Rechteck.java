package at.badkey.formen;

import java.awt.Color;
import java.awt.Graphics;

public class Rechteck {
	protected int x;
	protected int y;
	protected int a;
	protected int b;
	protected Color color;
	protected float area;
	protected float scope;

	public Rechteck(int X, int Y, int A, int B, Color Color) {
		x = X;
		y = Y;
		a = A;
		b = B;
		color = Color;
	}

	public void setX(int X) {
		x = X;
	}

	public void setY(int Y) {
		y = Y;
	}
	
	public float getScope() {
		return this.scope;
	}
	
	public float getArea() {
		return this.area;
	}

	public void generate(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, a, b);
		g.drawRect(x, y, a, b);
		
		area = a*b;
		scope = 2*b+2*a;
	}

	public double degreeToRad(float degree) {
		return (Math.PI * degree / 180);
	}
}
