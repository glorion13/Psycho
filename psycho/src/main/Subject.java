package main;

public class Subject {
	
	private int age;
	private String ID;
	private int[] answers = new int[125];
	private int base;
	private int ceiling;
	private int correctStreak;
	private int wrongStreak;
	private int lowCeiling;
	private int mistakes;
	
	public void setLowCeiling(int number) {
		lowCeiling = number;
	}
	public int getLowCeiling() {
		return lowCeiling;
	}
	public void incMistakes() {
		mistakes++;
	}
	public int getMistakes() {
		return mistakes;
	}
	public void setMistakes(int number) {
		mistakes = number;
	}
	
	public void incCorrectStreak() {
		correctStreak++;
	}
	public int getCorrectStreak() {
		return correctStreak;
	}
	public void setCorrectStreak(int number) {
		correctStreak = number;
	}
	
	public void incWrongStreak() {
		wrongStreak++;
	}
	public int getWrongStreak() {
		return wrongStreak;
	}
	public void setWrongStreak(int number) {
		wrongStreak = number;
	}
	
	public Subject(int age, String ID) {
		this.age = age;
		this.ID = ID;
		for (int i=0;i<answers.length;i++) {
			answers[i] = 0;
		}
		this.base = -1;
		this.ceiling = -1;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getID() {
		return ID;
	}
	public int[] getAnswers() {
		return answers;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public int getBase() {
		return base;
	}
	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
	}
	public int getCeiling() {
		return ceiling;
	}

}
