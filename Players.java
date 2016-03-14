package hangman;

import java.io.Serializable;

public class Players implements Serializable{
	
	private static final long serialVersionUID =1L;
	private String name;
	private int scores;

	public Players(String name, int scores) {
		this.name = name;
		this.scores = scores;
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public int getScores() {
		return scores;
	}

	

}
