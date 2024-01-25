package com.ciodar.scoreboard;

public class Match {
	private int homeScore=0;
	private int awayScore=0;
public Match(int homeScore, int awayScore) {
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}
	//	getters and setters
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
}
