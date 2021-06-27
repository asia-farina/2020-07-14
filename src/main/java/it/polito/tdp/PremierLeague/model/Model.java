package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	private Graph<Team, DefaultWeightedEdge> grafo;
	private Map<Integer, Team> idMap;
	private PremierLeagueDAO dao;
	
	int numReporterMax;
	int numReporterMin;
	int media;
	List<Match> matches;
	List<SquadraEReporter> squadre;
	
	public Model () {
		dao=new PremierLeagueDAO();
		idMap=new HashMap<>();
	}
	
	public void creaGrafo () {
		grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		dao.listAllTeams(idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		dao.getClassifica(idMap);
		for (Adiacenza a:dao.getAdiacenze(idMap)) {
			int peso=a.getT1().getScore()-a.getT2().getScore();
			if (peso>0) {
				Graphs.addEdgeWithVertices(grafo, a.getT1(), a.getT2(), peso);
			} else if (peso<0){
				Graphs.addEdgeWithVertices(grafo, a.getT2(), a.getT1(), (-1)*peso);
			}
		}
	}
	
	public int getNumeroVertici () {
		return grafo.vertexSet().size();
	}
	
	public int getNumeroArchi () {
		return grafo.edgeSet().size();
	}
	
	public Collection<Team> getTeams () {
		return idMap.values();
	}
	
	public List<Squadre> getBattute(Team t) {
		List<Squadre>battute=new ArrayList<>();
		int punt=t.getScore();
		for (Team tt:Graphs.successorListOf(grafo, t)) {
			battute.add(new Squadre(tt, (punt-tt.getScore())));
		}
		Collections.sort(battute);
		return battute;
	}
	
	public List<Squadre> getAltre(Team t) {
		List<Squadre>altre=new ArrayList<>();
		int punt=t.getScore();
		for (Team tt:Graphs.predecessorListOf(grafo, t)) {
			altre.add(new Squadre(tt, (tt.getScore()-punt)));
		}
		Collections.sort(altre);
		return altre;
	}
	
	
}
