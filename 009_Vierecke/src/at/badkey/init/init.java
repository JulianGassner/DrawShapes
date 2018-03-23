package at.badkey.init;

import java.awt.EventQueue;

import at.badkey.form.frm_Drawing;

public class init {
	public static String version = "1.0";

	public static void main(String[] args) {
		initMSG();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_Drawing window = new frm_Drawing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void initMSG() {
		System.out.println("----------------Viereck-Generator----------------");
		System.out.println("Author: Julian Gassner");
		System.out.println("Version: " + version);
		System.out.println("----------------Viereck-Generator----------------");
	}

}
