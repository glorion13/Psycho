package main;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static StartingGui startingGui = new StartingGui();
	public static MainGui mainGui = new MainGui();
	public static CorrectGui correctGui = new CorrectGui();
	public static BlankGui blankGui = new BlankGui();
	public static EndGui endGui = new EndGui();
	public static Output output = new Output();
	public static Subject subject = new Subject(0, "");
	public static ArrayList<Page> book = new ArrayList<Page>();
	public static ArrayList<Page> trials = new ArrayList<Page>();
	public static boolean audio = false;
	
	public static void main(String args[]) throws IOException {
		
		// Initiate the subject's base as -1 to show that no base has been established
		subject.setBase(-1);

		startingGui.launchFrame();
	}

}
