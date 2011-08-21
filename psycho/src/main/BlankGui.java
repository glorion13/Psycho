package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlankGui {		

	// Initialize all swing objects
	private JFrame f = new JFrame("Experiment");
	private JPanel pnlTop = new JPanel(); // Top quadrant
	private JPanel pnlBottom = new JPanel(); // Bottom quadrant
	private JPanel pnlLeft = new JPanel(); // Left quadrant
	private JPanel pnlRight = new JPanel(); // Right quadrant

	/** Constructor for the GUI */
	public BlankGui(){

		// Add buttons

		// Setup main frame
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		f.getContentPane().add(pnlLeft, BorderLayout.WEST);
		f.getContentPane().add(pnlRight, BorderLayout.EAST);
		f.getContentPane().add(pnlTop, BorderLayout.NORTH);

		// Allow the App to be closed
		f.addWindowListener(new ListenCloseWdw());

	}

	public class ListenCloseWdw extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);         
		}
	}

	public class ListenButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			closeFrame();

			Main.mainGui.returnToFrame();
		}    	
	}

	public void launchFrame() {

		// Display Frame

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack(); // Adjust panel to components for display
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.dispose();
		f.setUndecorated(true);
		f.setVisible(true);
	}
	public void closeFrame() {
		f.setVisible(false);
	}

}