package main.java.graphe.ihm;

import java.util.Map;
import java.util.HashMap;

import main.java.graphe.algos.*;
import main.java.graphe.core.*;

import main.java.graphe.implems.*;

public class Main {
    public static final String GRAPHES_REP = "graphes";
    public static final String REPONSES_REP = "reponses";
	private static final IGraphe[] prototypes = {
			new GrapheLArcs(),
            new GrapheLAdj(),
			new GrapheMAdj(),
            new GrapheHHAdj()
	};


	public static void main(String[] args) {
        long duree;
		for (IGraphe g : prototypes) {
			System.out.print(g.getClass().getSimpleName() + " : ");
			//duree = mesurer(g, "graphes", "reponses");
            duree = chronoTousLesGraphesDeType(g);
            System.out.println(duree + " millisecondes" );
		}
	}

    private static long chronoTousLesGraphesDeType(IGraphe g) {
        long dureeTotale = 0;
        GraphDirectoryImporter importer = new GraphDirectoryImporter(GRAPHES_REP, REPONSES_REP, false, g);
        for (CheminATrouver cat : importer) {
            dureeTotale += chrono(cat.getGraph(), cat.getSD_arc().get_source(),
                    cat.getSD_arc().get_dest(), cat.getDistance_attendue());
        }
        return dureeTotale;
    }

    private static long chrono(IGraphe g, String source, String destination, int distanceAttendue) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        return DijkstraTools.time(g, source, dist, prev);
    }

}
