package at.badkey.form;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import at.badkey.formen.Deltoid;
import at.badkey.formen.Paralellogramm;
import at.badkey.formen.Rechteck;
import at.badkey.formen.Shapes;
import at.badkey.formen.Trapez;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frm_creationMenu {

	private Shapes shape;
	private frm_Drawing drawInstance;
	private Color color = Color.black;

	public JFrame frame;
	private JTextField txt_a;
	private JTextField txt_b;
	public JTextField txt_alpha;
	public JLabel lbl_alpha;
	public JLabel lbl_a;
	public JLabel lbl_b;
	public JTextField txt_c;
	public JLabel lbl_beta;
	public JTextField txt_beta;
	public JLabel lbl_c;

	/**
	 * Create the application.
	 */
	public frm_creationMenu(Shapes Shape, frm_Drawing instance) {
		initialize();
		shape = Shape;
		drawInstance = instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 339, 214);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lbl_a = new JLabel("Seite a");
		lbl_a.setBounds(70, 14, 69, 14);
		lbl_a.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lbl_a);

		lbl_b = new JLabel("Seite b");
		lbl_b.setBounds(70, 39, 69, 14);
		lbl_b.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lbl_b);

		lbl_alpha = new JLabel("\u03B1");
		lbl_alpha.setBounds(70, 89, 69, 14);
		lbl_alpha.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lbl_alpha);

		txt_a = new JTextField();
		txt_a.setBounds(149, 11, 86, 20);
		txt_a.setColumns(10);
		frame.getContentPane().add(txt_a);

		txt_b = new JTextField();
		txt_b.setBounds(149, 36, 86, 20);
		txt_b.setColumns(10);
		frame.getContentPane().add(txt_b);

		txt_alpha = new JTextField();
		txt_alpha.setBounds(149, 86, 86, 20);
		txt_alpha.setColumns(10);
		frame.getContentPane().add(txt_alpha);

		// Buttons
		JButton bnt_farbe = new JButton("Farbe");
		bnt_farbe.setBounds(122, 141, 89, 23);
		bnt_farbe.setBackground(color);
		bnt_farbe.setForeground(Color.WHITE);
		bnt_farbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				color = JColorChooser.showDialog(frame, "Select a color:", color);
				bnt_farbe.setBackground(color);
			}
		});
		frame.getContentPane().add(bnt_farbe);

		JButton bnt_ok = new JButton("O.K");
		bnt_ok.setBounds(224, 141, 89, 23);
		bnt_ok.addActionListener(new ActionListener() {

			// Generate shape in drawing form
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						if (shape == Shapes.Rechteck) {
							Rechteck rechteck = new Rechteck(0, 0, Integer.parseInt(txt_a.getText()),
									Integer.parseInt(txt_b.getText()), color);
							drawInstance.toDraw = rechteck;
						}
						else if (shape == Shapes.Paralellogramm) {
							Paralellogramm paralellogramm = new Paralellogramm(0, 0, Integer.parseInt(txt_a.getText()),
									Integer.parseInt(txt_b.getText()), Integer.parseInt(txt_alpha.getText()), color);
							drawInstance.toDraw = paralellogramm;
						}
						else if (shape == Shapes.Deltoid) {
							Deltoid deltoid = new Deltoid(0, 0, Integer.parseInt(txt_a.getText()),
									Integer.parseInt(txt_b.getText()), Integer.parseInt(txt_alpha.getText()), color);
							drawInstance.toDraw = deltoid;
						}
						else if (shape == Shapes.Trapez) {
							Trapez trapez = new Trapez(0, 0, Integer.parseInt(txt_a.getText()),
									Integer.parseInt(txt_b.getText()), Integer.parseInt(txt_c.getText()), Integer.parseInt(txt_alpha.getText()), Integer.parseInt(txt_beta.getText()), color);
							drawInstance.toDraw = trapez;
						}
					}
				});
				frame.dispose();

			}
		});
		frame.getContentPane().add(bnt_ok);
		
		lbl_c = new JLabel("Seite c");
		lbl_c.setBounds(70, 64, 69, 14);
		lbl_c.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lbl_c);
		
		txt_c = new JTextField();
		txt_c.setBounds(149, 61, 86, 20);
		txt_c.setColumns(10);
		frame.getContentPane().add(txt_c);
		
		lbl_beta = new JLabel("\u03B2");
		lbl_beta.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_beta.setBounds(70, 110, 69, 14);
		frame.getContentPane().add(lbl_beta);
		
		txt_beta = new JTextField();
		txt_beta.setColumns(10);
		txt_beta.setBounds(149, 110, 86, 20);
		frame.getContentPane().add(txt_beta);

	}
}
