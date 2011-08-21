package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output {

	public Output() {

	}

	public void printOutput() {

		String id = Main.subject.getID();
		int[] responses = Main.subject.getAnswers();

		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(id + ".csv"));

			file.write("ID, Item#, Acc, Resp, CResp, , RAW,\n");

			int mistakesSoFar = 0;
			int lastRawScore = 0;
			for(int i = 0; i < responses.length; i ++) {
				int theirAnswer = Math.abs(responses[i]);
				int rightAnswer = Main.book.get(i).getCorrect();

				if(theirAnswer == 0) continue;

				if(theirAnswer != rightAnswer && i+1 >= Main.subject.getBase()+7) {
					mistakesSoFar ++;
				}

				int rawScore = Main.subject.getCeiling() - mistakesSoFar;
				lastRawScore = rawScore;
			}

			boolean first = true;
			for(int i = 0; i < responses.length; i ++) {
				int theirAnswer = Math.abs(responses[i]);
				int rightAnswer = Main.book.get(i).getCorrect();

				if(theirAnswer == 0) continue;

				file.write("" + id + ",");
				file.write("" + (i) + ",");
				file.write("" + (theirAnswer == rightAnswer ? "1" : "0") + ",");
				file.write("" + theirAnswer + ",");
				file.write("" + rightAnswer + ",");

				int rawScore = Main.subject.getCeiling() - mistakesSoFar;
				lastRawScore = rawScore;
				if(first) {
					//file.write("," + startingItem + ",");
					file.write("," + lastRawScore + "\n");
					first = false;
				}
				else {
					file.write("\n");
				}
			}

			file.close();
			System.exit(0);
		}
		catch(IOException e) {
			System.exit(0);
		}
	}

}
