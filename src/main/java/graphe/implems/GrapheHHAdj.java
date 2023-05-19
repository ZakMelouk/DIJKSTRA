package main.java.graphe.implems;

import main.java.graphe.core.IGraphe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheHHAdj implements IGraphe  {
	private Map<String, Map<String, Integer>> hhadj;
    public static final int NO_VALUATION = -1;


	public GrapheHHAdj() {
		hhadj = new HashMap <>();
	}
	public GrapheHHAdj (String graphe){
		this();
	    peupler(graphe);
	 }
	
	  
	@Override
	public List<String> getSommets() {
		 List<String> sommets = new ArrayList<String>(hhadj.keySet());
	        return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> successeurs = new ArrayList<String>(hhadj.get(sommet).keySet());
        return successeurs;
	}

	@Override
	public int getValuation(String src, String dest) {
		if(contientArc(src,dest)) {
			return hhadj.get(src).get(dest);
		}
		else
			return NO_VALUATION;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return hhadj.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(contientSommet(src)) {
			return hhadj.get(src).containsKey(dest);
		}
		else
			return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!(contientSommet(noeud)))
			hhadj.put(noeud, new HashMap<>());
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(contientArc(source,destination) || valeur<0) {
			throw new IllegalArgumentException();
		}
		else {
			if (!contientSommet(source))
				ajouterSommet(source);
			if (!contientSommet(destination))
				ajouterSommet(destination);
			hhadj.get(source).put(destination, valeur);
		        
		}
		
	}

	@Override
	public void oterSommet(String noeud) {
		if (contientSommet(noeud)){
            hhadj.remove(noeud);
            for(String sommet : getSommets())
            	if(contientArc(sommet,noeud))
                	oterArc(sommet, noeud);
            		
        }
	}

	@Override
	public void oterArc(String source, String destination) {
		if(!(contientArc(source, destination))) {
			throw new IllegalArgumentException();
		}
		else {
			hhadj.get(source).remove(destination);
		}
	}
	
	public String toString() {
        return toAString();
    }

}
