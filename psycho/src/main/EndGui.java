package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndGui {		
		
		// Initialize all swing objects
	    private JFrame f = new JFrame("Experiment");
	    private JPanel pnlTop = new JPanel(); // Top quadrant
	    private JPanel pnlBottom = new JPanel(); // Bottom quadrant
	    private JPanel pnlLeft = new JPanel(); // Left quadrant
	    private JPanel pnlRight = new JPanel(); // Right quadrant
	    
		// Button containing the image
	    private JButton btn = new JButton("Exit!");
	    
	    private JLabel thanks = new JLabel("Thank you very much for your participation in this experiment! Please press the 'Exit!' button below.");
	    
	    /** Constructor for the GUI */
	    public EndGui(){
	        
	        // Add buttons
	        pnlTop.add(thanks);
	        pnlBottom.add(btn);

	        // Add action listener to button
	        btn.addActionListener(new ListenButtonPressed());

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
		    	Main.output.printOutput();
		    	closeFrame();
			}    	
	    }
		
	    public void launchFrame() {
	    	
	        // Display Frame
	    	
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.pack(); // Adjust panel to components for display
	        f.setVisible(true);
	    }
	    public void closeFrame() {
	    	f.setVisible(false);
	    }
	    
}