package main.java.graphe.implems;

import main.java.graphe.core.IGraphe;
import main.java.graphe.core.Arc;
import java.util.*;

public class GrapheLArcs implements IGraphe {
	private List<Arc> arcs;
    public static final int NO_VALUATION = -1;
	
	public GrapheLArcs() {
		arcs = new ArrayList<>();
	}
	
	public GrapheLArcs(String liste) {
		this();
		this.peupler(liste);
	}

	@Override
	public List<String> getSommets() {
		List <String> Sommet  = new ArrayList <>();
		for (int i = 0 ; i < arcs.size();  ++i) {
			if(Sommet.contains(arcs.get(i).get_source())==false)
				Sommet.add(arcs.get(i).get_source());		
			if(Sommet.contains(arcs.get(i).get_dest())== false && (!(arcs.get(i).get_dest()).equals("")))
				Sommet.add(arcs.get(i).get_dest());		
		}
		return Sommet;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List <String> Sommet  = new ArrayList <>();
		for(int i = 0; i < arcs.size() ; ++i) {
			if(arcs.get(i).get_source().equals(sommet) && (!(arcs.get(i).get_dest().equals(("")))))
				Sommet.add(arcs.get(i).get_dest());
		}
			return Sommet;
	}

	@Override
	public int getValuation(String src, String dest) {
		for(int i = 0 ; i < arcs.size() ; ++i) {
			if (arcs.get(i).is_equal(new Arc (src, dest, 0)))
					return arcs.get(i).get_valu();
		}	
		return NO_VALUATION;
	}

	@Override
	public boolean contientSommet(String sommet) {
		for(int i = 0 ; i < arcs.size() ; ++i) {
			if (arcs.get(i).get_source().equals(sommet))
				return true;
		}
		return false;
	}

	@Override
	public boolean contientArc(String src, String dest) {
		for(int i = 0 ; i < arcs.size() ; ++i) {
			if (arcs.get(i).is_equal(new Arc (src, dest, 0)))
					return true;
		}
		return false;
	}

	

	@Override
	public void ajouterSommet(String noeud) {
		if(!(contientSommet(noeud))) {
			Arc a = new Arc (noeud , "", 0);
			arcs.add(a);
		}	
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(contientArc(source,destination) || valeur<0) {
			throw new IllegalArgumentException();
		}
		else {
			Arc a = new Arc (source, destination, valeur);
			arcs.add(a);
			
			if(contientArc(source,"")) {
				oterArc(source, "");
			}
			if(contientArc(destination,"")) {
				oterArc(destination, "");
			}
			
			
		}
	}

	@Override
	public void oterSommet(String noeud) {
		for(int i = 0 ; i < arcs.size() ; ++i) {
			if (arcs.get(i).get_source().equals(noeud))
					arcs.remove(i);
				
		}
		
	}

	@Override
	public void oterArc(String source, String destination) {
		if(!(contientArc(source, destination))) {
			throw new IllegalArgumentException();
		}
		else {
			for(int i = 0 ; i < arcs.size() ; ++i) {
				if (arcs.get(i).is_equal(new Arc (source, destination, 0)))
						arcs.remove(i);
			}
			if(contientSommet(source)==false) {
				ajouterSommet(source);
			}
				
		}
	}
	
	public String toString() {
        return toAString();
    }

}
