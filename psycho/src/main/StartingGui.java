package main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class StartingGui {		

	// Initialize all swing objects
	private JFrame f = new JFrame("Experiment");
	private JPanel all = new JPanel();
	private JPanel pnlTop = new JPanel(); // Top quadrant
	private JPanel pnlBottom = new JPanel(); // Bottom quadrant
	private JPanel pnlLeft = new JPanel(); // Left quadrant
	private JPanel pnlRight = new JPanel(); // Right quadrant

	// Button containing the image
	private JButton btn = new JButton("Start experiment!");

	// TextBox for age
	private JTextField ageField = new JTextField(10);

	// TextBox for ID
	private JTextField idField = new JTextField(10);

	private JLabel txtAge = new JLabel("Age:");
	private JLabel txtID = new JLabel("ID:");

	/** Constructor for the GUI */
	public StartingGui(){

		// Add buttons
		pnlBottom.add(btn);
		pnlLeft.add(txtAge);
		pnlRight.add(txtID);
		pnlLeft.add(ageField);
		pnlRight.add(idField);

		// Add action listener to button
		btn.addActionListener(new ListenButtonPressed());

		// Setup main frame

		//all.setLayout(new GridBagLayout());
		//all.add(pnlBottom);
		//all.add(pnlLeft);
		//all.add(pnlRight);
		//all.add(pnlTop);

		all.setLayout(new GridBagLayout());

		{
			GridBagConstraints c = new GridBagConstraints();

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 1;
			c.ipadx = 10;

			all.add(txtAge, c);

			c.gridx ++;
			c.ipadx = 0;

			all.add(ageField, c);
		}

		{
			GridBagConstraints c = new GridBagConstraints();

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 2;
			c.ipadx = 10;

			all.add(txtID, c);

			c.gridx ++;
			c.ipadx = 0;

			all.add(idField, c);
		}

		{
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 3;
			c.gridwidth = 2;
			c.ipady = 20;

			all.add(Box.createGlue(), c);

			c.gridy ++;
			c.ipady = 0;

			all.add(btn, c);
		}

		//f.setLayout(new BorderLayout());
		//f.getContentPane().add(all, BorderLayout.CENTER);

		// Allow the App to be closed
		
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(all, BorderLayout.CENTER);
		
		f.addWindowListener(new ListenCloseWdw());
	}

	public class ListenCloseWdw extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);         
		}
	}

	public class ListenButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Integer.parseInt(ageField.getText());
				if ((ageField.getText().compareTo("") != 0) && (idField.getText().compareTo("") != 0)) {

					Main.subject.setAge(Integer.parseInt(ageField.getText()));
					Main.subject.setID(idField.getText());
					closeFrame();
					Main.mainGui.launchFrame();

				}   
			} catch (NumberFormatException nfs) {
			}
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
		try {
			Initiate init = new Initiate();
			init.populate();
		} catch (IOException e1) {
			System.out.println("ERROR"+Main.subject.getAge());
		}
		f.setVisible(false);
	}

}
