package main.java.graphe.implems;

import main.java.graphe.core.IGraphe;
import main.java.graphe.core.Arc;
import java.util.*;


public class GrapheLAdj  implements IGraphe {
    private Map<String, List<Arc>> ladj;
    public static final int NO_VALUATION = -1;


    public GrapheLAdj() {
        ladj = new HashMap<>();
    }

    public GrapheLAdj(String graphe){
        this();
        peupler(graphe);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud)){
            ladj.put(noeud,new ArrayList<Arc>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0 || contientArc(source,destination)){
            throw new IllegalArgumentException();
        }
        if (!contientSommet(source))
            ajouterSommet(source);
        if (!contientSommet(destination))
            ajouterSommet(destination);
        ladj.get(source).add(new Arc(source,destination,valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        if(contientSommet(noeud))
            ladj.remove(noeud);
            for(String sommet : getSommets())
                ladj.get(sommet).removeIf(a -> a.equals(new Arc(sommet,noeud,0)));
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientArc(source,destination)){
            ladj.get(source).removeIf(a -> a.equals(new Arc(source,destination,0)));
        }
        else
            throw new IllegalArgumentException();

    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<String>(ladj.keySet());
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<String>();
        if(contientSommet(sommet)){
            List<Arc> arcs = new ArrayList<Arc>(ladj.get(sommet));
            for(int i = 0; i< arcs.size(); ++i) {
                successeurs.add(arcs.get(i).get_dest());
            }
        }
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        if(contientArc(src,dest)){
            List<Arc> arcsSrc = new ArrayList<Arc>(ladj.get(src));
            for(int i = 0; i< arcsSrc.size(); ++i) {
                if (arcsSrc.get(i).get_dest().equals(dest))
                    return arcsSrc.get(i).get_valu();
            }
        }
        return NO_VALUATION;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (contientSommet(src) && contientSommet(dest)) {
            Arc arcCompare = new Arc(src, dest, 0);
            return ladj.get(src).contains(arcCompare);
        }
        return false;
    }

    public String toString() {
        return toAString();
    }
    
}