package com.vishnu.colourmemory.model;

/**
 * This class is an Object for database handling.
 * 
 * @author Vishnupriyan
 * 
 */
public class ScoreModel {

	public String name = "";
	public String score = "";
	public String rank = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}