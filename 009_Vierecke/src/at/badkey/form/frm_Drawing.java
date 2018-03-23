package at.badkey.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import at.badkey.api.DrawingAPI;
import at.badkey.api.MousePosition;
import at.badkey.formen.CustomShape;
import at.badkey.formen.Deltoid;
import at.badkey.formen.Paralellogramm;
import at.badkey.formen.Rechteck;
import at.badkey.formen.Shapes;
import at.badkey.formen.Trapez;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class frm_Drawing implements MouseListener {
	boolean customMode = false;
	Color c = Color.BLACK;
	public JFrame frame;
	public JLabel lbl_area;
	public JLabel lbl_scope;
	public float currentScope = 0;
	public float currentArea = 0;
	private frm_Drawing instance;
	public ArrayList<JPanel> jpanels = new ArrayList<JPanel>();
	public Object toDraw = null;
	DrawingAPI drawingAPI = new DrawingAPI(this);
	CustomShape customShape;
	public ArrayList<Object> currentShapes = new ArrayList<Object>();

	/** * Create the application. */
	public frm_Drawing() {
		instance = this;
		initialize();
	}

	/** * Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 779, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		frame.addMouseListener(this);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 763, 29);
		toolBar.setToolTipText("");
		JButton rechteck = new JButton("Rechteck");
		rechteck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCreationMenu(Shapes.Rechteck);
			}
		});
		JButton paralellogramm = new JButton("Paralellogramm");
		paralellogramm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCreationMenu(Shapes.Paralellogramm);
			}
		});
		JButton deltoid = new JButton("Deltoid");
		deltoid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCreationMenu(Shapes.Deltoid);
			}
		});
		JButton trapez = new JButton("Trapez");
		trapez.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCreationMenu(Shapes.Trapez);
			}
		});
		JButton custom = new JButton("Custom");
		custom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!customMode) {
					customMode = true;
					customShape = new CustomShape(JColorChooser.showDialog(frame, "Select a color:", c));
				} else {
					customMode = false;
					customShape.setMode(false);
					drawingAPI.rerender();
				}
			}
		});
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingAPI.clearShapes();
			}
		});
		toolBar.add(rechteck);
		toolBar.add(paralellogramm);
		toolBar.add(deltoid);
		toolBar.add(trapez);
		toolBar.add(custom);
		toolBar.add(reset);
		toolBar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		frame.getContentPane().add(toolBar);
		lbl_area = new JLabel("A=");
		lbl_area.setBounds(631, 392, 102, 14);
		frame.getContentPane().add(lbl_area);
		lbl_scope = new JLabel("U=");
		lbl_scope.setBounds(631, 411, 102, 14);
		frame.getContentPane().add(lbl_scope);
	}

	private void createCreationMenu(Shapes shape) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_creationMenu window = new frm_creationMenu(shape, instance);
					if (!(shape == Shapes.Paralellogramm || shape == Shapes.Deltoid || shape == Shapes.Trapez)) {
						window.txt_alpha.setVisible(false);
						window.lbl_alpha.setVisible(false);
					}
					if (shape == Shapes.Deltoid) {
						window.lbl_a.setText("f");
						window.lbl_b.setText("e");
					}
					if (shape != Shapes.Trapez) {
						window.lbl_c.setVisible(false);
						window.txt_c.setVisible(false);
						window.lbl_beta.setVisible(false);
						window.txt_beta.setVisible(false);
					}
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		if (toDraw != null) {
			if (toDraw instanceof Rechteck) {
				Rechteck rechteck = (Rechteck) toDraw;
				rechteck.setX(mouse.getX() - 8);
				rechteck.setY(mouse.getY() - 31);
				currentShapes.add(rechteck);
			}
			if (toDraw instanceof Paralellogramm) {
				Paralellogramm paralellogramm = (Paralellogramm) toDraw;
				paralellogramm.setX(mouse.getX() - 8);
				paralellogramm.setY(mouse.getY() - 31);
				currentShapes.add(paralellogramm);
			}
			if (toDraw instanceof Deltoid) {
				Deltoid deltoid = (Deltoid) toDraw;
				deltoid.setX(mouse.getX() - 8);
				deltoid.setY(mouse.getY() - 31);
				currentShapes.add(deltoid);
			}
			if (toDraw instanceof Trapez) {
				Trapez trapez = (Trapez) toDraw;
				trapez.setX(mouse.getX() - 8);
				trapez.setY(mouse.getY() - 31);
				currentShapes.add(trapez);
			}
			drawingAPI.rerender();
			toDraw = null;
		} else if (customMode) {
			MousePosition mousePosition = new MousePosition();
			mousePosition.setX(mouse.getX() - 8);
			mousePosition.setY(mouse.getY() - 31);
			customShape.addMouseClick(mousePosition);
			if (currentShapes.contains(customShape)) {
				currentShapes.remove(customShape);
			}
			currentShapes.add(customShape);
			drawingAPI.rerender();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}