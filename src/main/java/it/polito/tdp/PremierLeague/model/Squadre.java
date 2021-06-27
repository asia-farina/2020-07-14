package it.polito.tdp.PremierLeague.model;

public class Squadre implements Comparable<Squadre> {
	Team t;
	int punteggio;
	public Squadre(Team t, int punteggio) {
		super();
		this.t = t;
		this.punteggio = punteggio;
	}
	public Team getT() {
		return t;
	}
	public void setT(Team t) {
		this.t = t;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	@Override
	public int compareTo(Squadre arg0) {
		// TODO Auto-generated method stub
		if(this.punteggio>arg0.punteggio)
			return -1;
		if(this.punteggio<arg0.punteggio)
			return 1;
		return 0;
	}
	@Override
	public String toString() {
		return t.getName()+"-"+punteggio;
	}
	
	

}
