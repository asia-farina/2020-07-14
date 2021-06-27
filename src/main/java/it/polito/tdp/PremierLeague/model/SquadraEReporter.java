package it.polito.tdp.PremierLeague.model;

public class SquadraEReporter {
	Team t;
	int n;
	public SquadraEReporter(Team t, int n) {
		super();
		this.t = t;
		this.n = n;
	}
	public Team getT() {
		return t;
	}
	public void setT(Team t) {
		this.t = t;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n += n;
	}
	

}
