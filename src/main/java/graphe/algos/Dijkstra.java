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


    
	/*
	public static void dijkstraa(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
		String lasource = source;
		dist = new HashMap <String, Integer>();
		pred = new HashMap <String, String >();
		dist.put(lasource, 0);
		pred.put(lasource, lasource);
		ArrayList haha = new ArrayList <String>(graphe.getSucc("2"));
		
		for(int f = 0 ; f < graphe.getSommets().size() ; ++f) {
			if (graphe.getSucc(lasource).size() == 1) {
				if(dist.containsKey(graphe.getSucc(lasource).get(0))) {
					if(dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(0))< dist.get(graphe.getSucc(lasource).get(0))) {
						pred.replace((graphe.getSucc(lasource).get(0)), lasource);
						dist.replace(graphe.getSucc(lasource).get(0), dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(0)));
					}
						
				}
				else {
					dist.put(graphe.getSucc(lasource).get(0), (dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(0))));
					pred.put(graphe.getSucc(lasource).get(0), lasource);
				}
				lasource = graphe.getSucc(lasource).get(0);
			}	
			else {
				String prochain = graphe.getSucc(lasource).get(0);
				int longueur = 0;
				for(int i = 1 ; i < graphe.getSucc(lasource).size(); ++i) {
					String a = graphe.getSucc(lasource).get(i);
					String b = graphe.getSucc(lasource).get(i-1);
					if(dist.containsKey(graphe.getSucc(lasource).get(i))) {
						if (dist.containsKey(graphe.getSucc(lasource).get(i-1))) {
							if(dist.get(graphe.getSucc(lasource).get(i))< dist.get(graphe.getSucc(lasource).get(i-1))) {
								prochain = graphe.getSucc(lasource).get(i);
								longueur = dist.get(graphe.getSucc(lasource).get(i));
							}
							
						}
						else {
							if(dist.get(graphe.getSucc(lasource).get(i)) > dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1))) {
								prochain = graphe.getSucc(lasource).get(i-1);
								longueur = dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1));	
							}
						}	
					}
					else if (dist.containsKey(graphe.getSucc(lasource).get(i-1))) {
						if(dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i))< dist.get(graphe.getSucc(lasource).get(i-1))) {
							prochain = graphe.getSucc(lasource).get(i);
							longueur = dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i));
						}
						
					}
					else {
						if(graphe.getValuation(lasource, graphe.getSucc(lasource).get(i)) < graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1))){
							prochain = graphe.getSucc(lasource).get(i);
							longueur = dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i));
						}
						else if(graphe.getValuation(lasource, graphe.getSucc(lasource).get(i)) > graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1))) {
							prochain = graphe.getSucc(lasource).get(i-1);
							longueur = dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1));
						}
						else {
							prochain = graphe.getSucc(lasource).get(i-1);
							longueur = dist.get(lasource) + graphe.getValuation(lasource, graphe.getSucc(lasource).get(i-1));
							
						}
						
					}
				}
				if(dist.containsKey(prochain)) {
					dist.replace(prochain, longueur);
					pred.replace(prochain, lasource);
					
				}
				else {
					dist.put(prochain, longueur);
					pred.put(prochain, lasource);
				}
				lasource = prochain;
					
			}
		}
		return;
	}
	public static int calcul (IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
		
		 
		int resultat = 0;
		boolean fin = false;
		do {
			resultat += dist.get(pred.get(source));
			if(pred.get(pred.get(source)).equals(source))
				fin = true;
			else
				source = pred.get(pred.get(source));
		}while(fin == false);
		return  resultat;
	}
	
	
	
	public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
		String lasource = source;
		dist.put(lasource, 0);
		pred.put(lasource, lasource);
		Graphe graphe3 = new GrapheLArcs();
		trouverpluscourt(graphe,source,dist,pred, graphe3);
		String b = "b";

	}
	
	public static void trouverpluscourt(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred, Graphe graphe3) {
		int taille =  graphe.getSucc(source).size();
		for(int i = 0; i < taille ; ++i) {
			if(dist.containsKey(graphe.getSucc(source).get(i))) {
				if(dist.get(graphe.getSucc(source).get(i))> dist.get(source)+graphe.getValuation(source, graphe.getSucc(source).get(i))) {
					int taille_2 = graphe.getSucc(graphe.getSucc(source).get(i)).size();
					for(int f = 0; f < taille_2 ; ++f) {
						if(graphe3.contientArc(graphe.getSucc(source).get(i), graphe.getSucc(graphe.getSucc(source).get(i)).get(f)))
							graphe3.oterArc(graphe.getSucc(source).get(i), graphe.getSucc(graphe.getSucc(source).get(i)).get(f));
					}
					graphe3.ajouterArc(source, graphe.getSucc(source).get(i), 0);
					dist.replace(graphe.getSucc(source).get(i),dist.get(source)+graphe.getValuation(source, graphe.getSucc(source).get(i)));
					pred.replace(graphe.getSucc(source).get(i), source);
				}
			}
			else {
				graphe3.ajouterArc(source, graphe.getSucc(source).get(i), 0);
				dist.put(graphe.getSucc(source).get(i), dist.get(source)+graphe.getValuation(source, graphe.getSucc(source).get(i)));
				pred.put(graphe.getSucc(source).get(i), source);				
			}
			if(graphe.getSucc(graphe.getSucc(source).get(i)).size()>0 && (!(graphe3.contientArc(graphe.getSucc(source).get(i), graphe.getSucc(graphe.getSucc(source).get(i)).get(0)))))
				trouverpluscourt(graphe,graphe.getSucc(source).get(i),dist,pred,graphe3);
		}
	}
	*/
	
}
