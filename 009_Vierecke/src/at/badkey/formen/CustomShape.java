package at.badkey.formen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.badkey.api.MousePosition;

public class CustomShape {

	private Color c;
	private ArrayList<Integer> xpoints = new ArrayList<Integer>();
	private ArrayList<Integer> ypoints = new ArrayList<Integer>();
	private int npoints = 0;
	private boolean polylineMode = true;
	private ArrayList<MousePosition> mouseClicks = new ArrayList<MousePosition>();
	protected float area;
	protected float scope;

	public CustomShape(Color C) {
		this.c = C;
	}

	public float getScope() {
		return this.scope;
	}
	
	public float getArea() {
		return this.area;
	}
	
	public boolean getMode() {
		return polylineMode;
	}

	public void setMode(boolean polylineMode) {
		this.polylineMode = polylineMode;
	}

	public void addMouseClick(MousePosition p) {
		mouseClicks.add(p);
	}

	public void updatePolyline(Graphics g) {
		g.setColor(Color.BLACK);
		xpoints.clear();
		ypoints.clear();
		npoints = mouseClicks.size();
		for (MousePosition m : mouseClicks) {
			xpoints.add(m.getX());
			ypoints.add(m.getY());
		}
		g.drawPolyline(convertIntegers(xpoints), convertIntegers(ypoints), npoints);
	}

	public void generatePolygon(Graphics g) {
		g.setColor(c);
		Polygon polygon = new Polygon(convertIntegers(xpoints), convertIntegers(ypoints), npoints);
		g.fillPolygon(polygon);
		g.drawPolygon(polygon);
		// Calculate scope and area
		for (int i = 0; i < npoints / 2; i++) {
			// Scope
			int x1 = convertIntegers(xpoints)[i * 2];
			int y1 = convertIntegers(ypoints)[i * 2];
			int x2 = convertIntegers(xpoints)[i * 2 + 1];
			int y2 = convertIntegers(ypoints)[i * 2 + 1];

			int DeltaX = x2 - x1;
			int DeltaY = y2 - y1;

			scope += (float) Math.sqrt(DeltaX ^ 2 + DeltaY ^ 2);

			// Area
			area += ((x1 * y2 - y1 * x2));
		}

		area /= 2;
		area = Math.abs(area);

	}

	private int[] convertIntegers(List<Integer> integers) {
		int[] ret = new int[integers.size()];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

}
