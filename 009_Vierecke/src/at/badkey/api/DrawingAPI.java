package at.badkey.api;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import at.badkey.form.frm_Drawing;
import at.badkey.formen.CustomShape;
import at.badkey.formen.Deltoid;
import at.badkey.formen.Paralellogramm;
import at.badkey.formen.Rechteck;
import at.badkey.formen.Trapez;

public class DrawingAPI {
	private frm_Drawing drawInstance;

	public DrawingAPI(frm_Drawing instance) {
		drawInstance = instance;
	}

	public void rerender() {
		drawInstance.frame.getContentPane().setLayout(new BorderLayout());
		for (JPanel j : drawInstance.jpanels) {
			drawInstance.frame.remove(j);
		}
		drawInstance.jpanels.clear();
		JPanel j = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				for (Object j : drawInstance.currentShapes) {
					if (j instanceof Rechteck) {
						Rechteck rechteck = (Rechteck) j;
						rechteck.generate(g);
						g.setColor(Color.BLACK);
						drawInstance.lbl_scope.setText("U="+ rechteck.getScope());
						drawInstance.lbl_area.setText("A=" + rechteck.getArea());
					} else if (j instanceof Paralellogramm) {
						Paralellogramm paralellogramm = (Paralellogramm) j;
						paralellogramm.generate(g);
						g.setColor(Color.BLACK);
						drawInstance.lbl_scope.setText("U="+paralellogramm.getScope());
						drawInstance.lbl_area.setText("A=" + paralellogramm.getArea());
					} else if (j instanceof Deltoid) {
						Deltoid deltoid = (Deltoid) j;
						deltoid.generate(g);
						g.setColor(Color.BLACK);
						drawInstance.lbl_scope.setText("U="+deltoid.getScope());
						drawInstance.lbl_area.setText("A=" + deltoid.getArea());
					} else if (j instanceof Trapez) {
						Trapez trapez = (Trapez) j;
						trapez.generate(g);
						drawInstance.lbl_scope.setText("U="+trapez.getScope());
						drawInstance.lbl_area.setText("A=" + trapez.getArea());
					} else if (j instanceof CustomShape) {
						CustomShape custom = (CustomShape) j;
						if (custom.getMode()) { // True = in Polylinemode
							custom.updatePolyline(g);
						} else { // False = generate Polygon out of the polylines
							custom.generatePolygon(g);
							drawInstance.lbl_scope.setText("U="+custom.getScope());
							drawInstance.lbl_area.setText("A=" + custom.getArea());
						}
					}
				}

			}
		};
		drawInstance.jpanels.add(j);
		drawInstance.frame.getContentPane().add(j, BorderLayout.CENTER);
		drawInstance.frame.repaint();
		drawInstance.frame.validate();
	}

	public void clearShapes() {
		for (JPanel j : drawInstance.jpanels) {
			drawInstance.frame.remove(j);
		}
		drawInstance.jpanels.clear();
		drawInstance.currentShapes.clear();
		drawInstance.frame.repaint();
	}
}
