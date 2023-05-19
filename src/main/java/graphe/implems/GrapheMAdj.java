package main.java.graphe.implems;

import main.java.graphe.core.IGraphe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheMAdj implements IGraphe{
	
	private int[][] matrice;
	private Map<String, Integer> indices;
    public static final int NO_VALUATION = -1;


	public GrapheMAdj() {
		indices = new HashMap<>();
	}
	public GrapheMAdj(String liste) {
		this();
		peupler(liste);
	}
	@Override
	public List<String> getSommets() {
		 List<String> sommets = new ArrayList<String>(indices.keySet());
	        return sommets;		
	}

	@Override
	public List<String> getSucc(String sommet) {
		 List<String> sommets = new ArrayList<String>();
		 for(String a : getSommets()) {
			 if(matrice[indices.get(sommet)][indices.get(a)]>0)
				 sommets.add(a);
		 }
		 return sommets;		
	}

	@Override
	public int getValuation(String src, String dest) {
		if(contientArc(src,dest))
			return matrice [indices.get(src)][indices.get(dest)];
		else
			return NO_VALUATION;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return indices.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(indices.containsKey(src)&& indices.containsKey(dest)) {
			if ((matrice [indices.get(src)][indices.get(dest)])>0)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!(contientSommet(noeud))) {
			int taille = getSommets().size();
			int [][] matrice_intermédiaire = new int [taille][taille];
			for(int i = 0; i < taille;++i) {
				for(int g = 0; g < taille;++g) {
					matrice_intermédiaire[i][g] = matrice[i][g];
				}
			}
			matrice = new int  [taille + 1][taille + 1];
			for(int i = 0 ; i < taille ; ++i) {
				for(int f = 0; f < taille ; ++f) {
					matrice [i][f] = matrice_intermédiaire[i][f];
				}
			}
			indices.put(noeud, taille );
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(valeur<0) {
			throw new IllegalArgumentException();
		}
		else {
			if (!contientSommet(source))
				ajouterSommet(source);
			if (!contientSommet(destination))
				ajouterSommet(destination);
			if(contientArc(source,destination))
					throw new IllegalArgumentException();

			matrice[indices.get(source)][indices.get(destination)]= valeur;
		}
		
	}
	
	@Override
	 public void oterSommet(String noeud) {
		if(contientSommet(noeud)) {
			int taille = getSommets().size();
			int [][] matrice_intermédiaire = new int [taille][taille];
			for(int i = 0; i < taille;++i) {
				for(int g = 0; g < taille;++g) {
					matrice_intermédiaire[i][g] = matrice[i][g];
				}
			}
			matrice = new int [taille -1][taille -1];
			for(int i = 0 ; i< taille ; ++i) {
				for(int f = 0; f<taille ; ++f) {
					if( i == indices.get(noeud)) {
						++i;
						if(i == taille)
							break;
					}
					
					if( f == indices.get(noeud)) {
						++f;
						if (f==taille)
							break;
					}
					matrice [i][f] = matrice_intermédiaire[i][f];
				}
			}
		}	
		
	}

	@Override
	public void oterArc(String source, String destination) {
		if(!(contientArc(source, destination))) 
			throw new IllegalArgumentException();
		else 
			matrice[indices.get(source)][indices.get(destination)]= 0;
	}
	
	public String toString() {
        return toAString();
    }
}
