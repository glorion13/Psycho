package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CorrectGui {		

	// Initialize all swing objects
	private JFrame f = new JFrame("Experiment");
	private JPanel pnlTop = new JPanel(); // Top quadrant
	private JPanel pnlBottom = new JPanel(); // Bottom quadrant
	private JPanel pnlLeft = new JPanel(); // Left quadrant
	private JPanel pnlRight = new JPanel(); // Right quadrant
	private int page = 0;

	private JLabel txtCorrect = new JLabel();

	/** Constructor for the GUI */
	public CorrectGui(){

		// Add buttons
		pnlLeft.add(txtCorrect);


		Font curFont = txtCorrect.getFont();

		txtCorrect.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 40));
		txtCorrect.setFocusable(true);

		txtCorrect.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 32) {

					try {
						Main.mainGui.trialShowPage(page);
					} catch (IOException e1) {
						System.out.println("ERROR in correctGui");
					}
					Main.mainGui.returnToFrame();
					closeFrame();
				}
			}

			public void keyTyped(KeyEvent e) {
			}			

		});


		// Setup main frame
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		f.getContentPane().add(pnlLeft, BorderLayout.CENTER);
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

	public void launchFrame(int page) {

		// Display Frame

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack(); // Adjust panel to components for display
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.dispose();
		f.setUndecorated(true);
		f.setVisible(true);
		this.page = page;
	}

	public void setLabel(String label) {
		txtCorrect.setText(label);
	}

	public void closeFrame() {
		f.setVisible(false);
	}

}