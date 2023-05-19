package main.java.graphe.algos;

import main.java.graphe.core.IGrapheConst;
import java.util.*;
public class Dijkstra {
	
    public static final int PAS_DE_CHEMIN_POSSIBLE = -1;
    
public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
    	
        List<String> LesSommets = graphe.getSommets();
        Map<String, Boolean> Sommets_deja_passe = new HashMap <String, Boolean>();
        HashSet<String> sommetmarques = new HashSet<>();
        for(String sommet : LesSommets) {
        	dist.put(sommet,PAS_DE_CHEMIN_POSSIBLE);
            pred.put(sommet,"");
            Sommets_deja_passe.put(sommet, false);
        }
        
        dist.replace(source, 0);
        pred.replace(source, "");
        String Sommet_en_cours = source;
        Comparator<String> MonComparateur = (Value,Value2) -> (Integer) (dist.get(Value) - dist.get(Value2));
        PriorityQueue<String> MaPriorityQueue = new PriorityQueue<>(MonComparateur);
        MaPriorityQueue.offer(Sommet_en_cours);
       
        while(!MaPriorityQueue.isEmpty()){
        	
        	Sommet_en_cours = MaPriorityQueue.poll();
        	LesSommets = graphe.getSucc(Sommet_en_cours);
        	if(!sommetmarques.contains(Sommet_en_cours)) {
        		for(String SommetSucc : LesSommets){
                	
                    int DistancePossible = dist.get(Sommet_en_cours) + graphe.getValuation(Sommet_en_cours,SommetSucc);
                    if(dist.get(SommetSucc) == PAS_DE_CHEMIN_POSSIBLE) {
                    	   dist.replace(SommetSucc,DistancePossible);
                           pred.replace(SommetSucc, Sommet_en_cours);
                    }
                    else if ( (DistancePossible < dist.get(SommetSucc))&& Sommets_deja_passe.get(SommetSucc).equals(true)) {
                    	//MaPriorityQueue.remove(SommetSucc);		
                        dist.replace(SommetSucc,DistancePossible);
                        pred.replace(SommetSucc, Sommet_en_cours);
                    	MaPriorityQueue.offer(SommetSucc);
                    }
                    if(Sommets_deja_passe.get(SommetSucc).equals(false)){
                    	MaPriorityQueue.offer(SommetSucc);
                        Sommets_deja_passe.replace(SommetSucc, true);
                    }
                }
            	sommetmarques.add(Sommet_en_cours);	
        	}
            
        }
    }
	
}
