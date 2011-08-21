package main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGui {

	// Initialize all swing objects
	private JFrame f = new JFrame("Experiment");
	private JPanel pnlTop = new JPanel(); // Top quadrant 

	private int page;
	private boolean isTrial = true;
	private boolean exception = false;
	private int exceptionGotoPage;
	private int startingPage;
	private boolean notFirstReverse = true;
	int correct = 0;
	private boolean first = true;

	// Image for button
	//private ImageIcon icon = createImageIcon("trials/1/1.jpg", "bear");

	// Button containing the image
	private JLabel btnTop = new JLabel();

	/** Constructor for the GUI */
	public MainGui(){

		btnTop.setFocusable(true);

		// Add buttons
		pnlTop.add(btnTop);

		btnTop.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 32) {
					playSound();
				}
			}

			public void keyTyped(KeyEvent e) {
			}			

		});
		// Add action listener to image

		btnTop.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int mouse_x = MouseInfo.getPointerInfo().getLocation().x;
					int mouse_y = MouseInfo.getPointerInfo().getLocation().y;
					int pic_x = btnTop.getX();
					int pic_y = btnTop.getY();
					int clicked = 0;

					// click on topLeft of image
					if ((mouse_x-pic_x < btnTop.getWidth()/2) && (mouse_y-pic_y < btnTop.getHeight()/2)) {
						if (!isTrial) { Main.subject.getAnswers()[page] = 1; }
						clicked = 1;
					}
					// click on topRight of image
					else if ((mouse_x-pic_x > btnTop.getWidth()/2) && (mouse_y-pic_y < btnTop.getHeight()/2)) {
						if (!isTrial) { Main.subject.getAnswers()[page] = 2; }
						clicked = 2;
					}
					// click on bottomLeft of image
					else if ((mouse_x-pic_x < btnTop.getWidth()/2) && (mouse_y-pic_y > btnTop.getHeight()/2)) {
						if (!isTrial) { Main.subject.getAnswers()[page] = 3; }
						clicked = 3;
					}
					// click on bottomRight of image
					else if ((mouse_x-pic_x > btnTop.getWidth()/2) && (mouse_y-pic_y > btnTop.getHeight()/2)) {
						if (!isTrial) { Main.subject.getAnswers()[page] = 4; }
						clicked = 4;
					}
					System.out.println("Clicked:"+clicked);
					// MAIN LOGIC OF THE PROGRAM

					if (isTrial) {
						correct = Main.trials.get(page).getCorrect();
						System.out.println(Main.trials.get(page).getPageNo()+"");

						if (clicked == correct) {
							Main.correctGui.setLabel("Correct! Please press SPACEBAR to continue!");
							page++;
							try {
								showPage(page);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else {
							Main.correctGui.setLabel("Incorrect. The correct response was "+Main.trials.get(page).getCorrect()+". Please press SPACEBAR to try again!");
							try {
								showPage(page);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (!isTrial) {
						// For real stuff

						correct = Main.book.get(page).getCorrect();

						if (clicked != 0) {
							if (clicked == correct) {
								Main.subject.incCorrectStreak();
								Main.subject.setWrongStreak(0);
								if (exception) { page--; }
								if (!exception) { page++; }
								if (Main.subject.getCorrectStreak() > 7) {
									Main.subject.setBase(page-8); 
									if (exception) {
										page = exceptionGotoPage;
										exception = false;
									}
								}
								if (page < 0) {
									page = exceptionGotoPage;
									exception = false;
									Main.subject.setBase(0);
								}
								if ((Main.subject.getBase() > -1) && (Main.subject.getCeiling() > -1)) { endExperiment(); }
								else { try {
									showPage(page);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} }
							}
							else {
								System.out.println(clicked);
								Main.subject.incWrongStreak();
								Main.subject.incMistakes();
								if (exception) { page--; }
								if (!exception) { page++; }
								if ((Main.subject.getBase() == -1) && (!exception)) { notFirstReverse = false; System.out.println("CHANGING EXCEPTION!"); exception = true; exceptionGotoPage = page; page = startingPage-1; }
								if (notFirstReverse) { Main.subject.setCorrectStreak(0); }
								notFirstReverse = true;
								Main.subject.setCeiling(findCeiling());
								System.out.println("Ceiling:"+findCeiling());
								for (int i=0;i<Main.subject.getAnswers().length;i++) {

								}
								if (page < 0) { page = exceptionGotoPage; exception = false; Main.subject.setBase(0); }
								if ((Main.subject.getBase() > -1) && (Main.subject.getCeiling() > -1)) { endExperiment(); }
								else { try {
									showPage(page);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} }
							}
						}
					}
					//////////////////////////////////////////////////

				}

			}
		});

		// Setup main frame
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(pnlTop, BorderLayout.NORTH);

		// Allow the App to be closed
		f.addWindowListener(new ListenCloseWdw());
	}

	public void returnToFrame() {
		f.setVisible(true);
	}
	// Returns an ImageIcon, or null if the path was invalid.
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	public class ListenCloseWdw extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);         
		}
	}
	public void endExperiment() {
		Main.mainGui.closeFrame();
		Main.endGui.launchFrame();
	}
	public void Wait() {
		int seconds = 5;
		System.out.println("Waiting: " + seconds);
		try {
			Thread[] threads = new Thread[Thread.activeCount()];
			Thread.enumerate(threads);
			for (int i=0;i<threads.length;i++) {
				threads[i].sleep(500);
			}
		} catch (InterruptedException e) {}
		System.out.println("Done");
	}

	public void trialShowPage(int page) throws IOException {
		if (!isTrial) { showPage(this.page); }
		else {
			this.page = page;
			BufferedImage image = ImageIO.read(new File(Main.trials.get(page).getImagePath()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gs = ge.getScreenDevices();
			int screenWidth=0;
			int screenHeight=0;
			// Get size of each screen
			for (int i=0; i<gs.length; i++) {
				DisplayMode dm = gs[i].getDisplayMode();
				screenWidth = dm.getWidth();
				screenHeight = dm.getHeight();
			}
			Image scaled = image.getScaledInstance(
					/*(int)f.getContentPane().getSize().getWidth(),
				(int)f.getContentPane().getSize().getHeight(),*/
					screenWidth,
					screenHeight,
					Image.SCALE_DEFAULT);
			btnTop.setIcon(new ImageIcon(scaled));
			//btnTop.setIcon(new ImageIcon(Main.trials.get(page).getImagePath()));
			playSound();
		}
	}

	@SuppressWarnings("static-access")
	public void showPage(int page) throws IOException {
		if (isTrial) {
			if (page < Main.trials.size()) {
				if (page == 0) { trialShowPage(page); }
				if (!first) { Main.correctGui.launchFrame(page); Main.mainGui.closeFrame(); }
				first = false;
			}
			else {
				
				Main.correctGui.launchFrame(page);
				isTrial = false;
				page = startingPage;
				this.page = startingPage;
				Main.mainGui.closeFrame();
				//showPage(this.page);
				
			}
		}
		else if (!isTrial) {
			if (page > 124) {
				// End program
				Main.subject.setCeiling(124);
				endExperiment();
			}
			Main.blankGui.launchFrame();
			Main.mainGui.closeFrame();

			this.page = page;
			System.out.println(Main.book.get(page).getImagePath());
			BufferedImage image = ImageIO.read(new File(Main.book.get(page).getImagePath()));
			Image scaled = image.getScaledInstance(
					(int)f.getContentPane().getSize().getWidth(),
					(int)f.getContentPane().getSize().getHeight(),
					Image.SCALE_DEFAULT);
			btnTop.setIcon(new ImageIcon(scaled));
			//btnTop.setIcon(new ImageIcon(Main.book.get(page).getImagePath()));
			System.out.println(Main.book.get(page).getImagePath());
			try {
				Thread.currentThread().sleep(500);

			} catch (InterruptedException e) {
				System.out.println("ITS waiting doesn't happen!");
			}
			Main.mainGui.returnToFrame();
			Main.blankGui.closeFrame();
			playSound();
		}
	}

	public int findCeiling() {
		for(int i = 0; i < Main.subject.getAnswers().length; i ++) {
			int incorrect = 0;
			for(int j = i; j < Main.subject.getAnswers().length && j < i+8; j ++) {
				if((Main.subject.getAnswers()[j] > 0) && (Main.subject.getAnswers()[j] != Main.book.get(j).getCorrect())) incorrect ++;
				if(incorrect >= 6) {
					return j;
				}
			}
		}
		return -1;
	}
	public void playSound() {
		//play.stop();
		if (isTrial) {
			new AePlayWave(Main.trials.get(page).getAudioPath()).start();
		}
		else {
			new AePlayWave(Main.book.get(page).getAudioPath()).start();
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

		page = 0;

		try {
			showPage(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Main.subject.getAge() <= 4) { startingPage = 0; }
		if (Main.subject.getAge() == 5) { startingPage = 9; }
		if (Main.subject.getAge() == 6) { startingPage = 25; }
		if (Main.subject.getAge() == 7) { startingPage = 37; }
		if (Main.subject.getAge() == 8) { startingPage = 49; }
		if (Main.subject.getAge() == 9) { startingPage = 59; }
		if (Main.subject.getAge() == 10) { startingPage = 9; }
		if (Main.subject.getAge() == 11) { startingPage = 76; }
		if (Main.subject.getAge() == 12) { startingPage = 81; }
		if (Main.subject.getAge() == 13) { startingPage = 85; }
		if (Main.subject.getAge() >= 14) { startingPage = 89; }
	}
	public void closeFrame() {
		f.setVisible(false);
	}

}