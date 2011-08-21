package main;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.MalformedURLException;

public class Page {

	private String path;
	private int correct;
	private int pageNo;
	
	public void setPageNo(int no) {
		pageNo = no;
	}
	public int getPageNo() {
		return pageNo;
	}

	public Page(java.net.URL path, int pageNumber) throws IOException {
		setPath(path);
		setCorrect();
		setPageNo(pageNumber);
	}
/*	public Page(String path, int pageNumber) throws IOException {
		setPath(path);
		setCorrect();
		setPageNo(pageNumber);
	}*/

	protected java.net.URL findPath(String path) {
		java.net.URL pathURL = getClass().getResource(path);
		if (pathURL != null) {
			return pathURL;
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public String getImagePath() {
		return path+pageNo+".jpg";
	}
	public String getAudioPath() {
		return path+pageNo+".wav";
	}
	public void setPath(java.net.URL path) {
		String file = path+"";
		
		this.path = file.substring(5);
	}
	/*public void setPath(String path) throws MalformedURLException {
		String file = path;

		this.path = file.substring(5);
	}*/
	public String getPath() {
		return path;
	}
	public int getCorrect() {
		return correct;
	}
	private void setCorrect() throws IOException {
		FileReader fr = new FileReader(path+"answer.txt");
		BufferedReader textReader = new BufferedReader(fr);
		this.correct = Integer.parseInt(textReader.readLine());
		textReader.close();
	}

}
