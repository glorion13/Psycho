package main;

import java.io.IOException;

public class Initiate {


	public Initiate() {
	}

	public void populate() throws IOException {

		// Populate 'trials' list
		
		System.out.println(Main.subject.getAge());
		if (Main.subject.getAge() < 8) {
			for (int i=0;i<3;i++) {
				int pageNumber = i+1;
				System.out.println(getClass().getResource("trials/"+pageNumber+"/"));
				Main.trials.add(new Page(getClass().getResource("trials/"+pageNumber+"/"),pageNumber));
			}
		}
		if (Main.subject.getAge() >= 8) {
			for (int i=3;i<5;i++) {
				int pageNumber = i+1;
				System.out.println(getClass().getResource("trials/"+pageNumber+"/"));
				Main.trials.add(new Page(getClass().getResource("trials/"+pageNumber+"/"),pageNumber));
			}
		}

		// Populate 'book' list (the actual test)
		for (int i=0;i<20;i++) {
			int pageNumber = i+1;
			System.out.println("book/"+pageNumber+"/");
			Main.book.add(new Page(getClass().getResource("book/"+pageNumber+"/"),pageNumber));
		}
	}

}
