package ProjetGraphe;

import java.awt.Dimension;
import java.util.*;

import javax.swing.JFrame;

public class AlgoDijkstra {
    private boolean safe = false;
    private String message = null;

    private Graphe graph;
    private Map<Sommet, Sommet> predecessors;
    private Map<Sommet, Integer> distances;

    private PriorityQueue<Sommet> unvisited;
    private HashSet<Sommet> visited;

    
public class SommetComparator implements Comparator<Sommet>  {
        @Override
        public int compare(Sommet Sommet1, Sommet Sommet2) {
            return distances.get(Sommet1) - distances.get(Sommet2);
        }
    };
//--------------------------------------------------------------------------------------------------
//
    public AlgoDijkstra(Graphe graph){
        this.graph = graph;
        predecessors = new HashMap<>();
        distances = new HashMap<>();

        for(Sommet Sommet : graph.getSommets()){
            distances.put(Sommet, Integer.MAX_VALUE);
        }
        visited = new HashSet<>();

        safe = evaluate();
    }
//----------------------------------------------------------------------------------------------------
//
    private boolean evaluate(){
        if(graph.getSource()==null){
            message = "Choisissez un sommet source";
            return false;
        }

        if(graph.getDestination()==null){
            message = "Choisissez un sommet de destination";
            return false;
        }

        for(Sommet Sommet : graph.getSommets()){
            if(!graph.SommetAccessible(Sommet)){
                message = "Le graphe contient des sommets isolés";
                return false;
            }
        }

        return true;
    }
//--------------------------------------------------------------------------------------------------------
//
    public void run() throws IllegalStateException {
        if(!safe) {
            throw new IllegalStateException(message);
        }
        unvisited = new PriorityQueue<>(graph.getSommets().size(), new SommetComparator());

        Sommet source = graph.getSource();
        distances.put(source, 0);
        visited.add(source);

        for (Arc neighbor : getNeighbors(source)){
            Sommet adjacent = getAdjacent(neighbor, source);
            if(adjacent==null)
                continue;

            distances.put(adjacent, neighbor.getWeight());
            predecessors.put(adjacent, source);
            unvisited.add(adjacent);
        }

        while (!unvisited.isEmpty()){
           Sommet current = unvisited.poll();

            updateDistance(current);

            unvisited.remove(current);
            visited.add(current);
        }

        for(Sommet node : graph.getSommets()) {
            node.setPath(getPath(node));
        }

        graph.setSolved(true);
        
    }
//------------------------------------------------------------------------------------------------------
//

    private void updateDistance(Sommet Sommet){
        int distance = distances.get(Sommet);

        for (Arc neighbor : getNeighbors(Sommet)){
            Sommet adjacent = getAdjacent(neighbor, Sommet);
            if(visited.contains(adjacent))
                continue;

            int current_dist = distances.get(adjacent);
            int new_dist = distance + neighbor.getWeight();

            if(new_dist < current_dist) {
                distances.put(adjacent, new_dist);
                predecessors.put(adjacent, Sommet);
                unvisited.add(adjacent);
            }
        }
    }
//--------------------------------------------------------------------------------------------------------
//
    private Sommet getAdjacent(Arc Arc, Sommet Sommet) {
        if(Arc.get_sommet1()!=Sommet && Arc.get_sommet2()!=Sommet)
            return null;

        return Sommet==Arc.get_sommet2()?Arc.get_sommet1():Arc.get_sommet2();
    }
//---------------------------------------------------------------------------------------------------------
    private List<Arc> getNeighbors(Sommet Sommet) {
        List<Arc> neighbors = new ArrayList<>();

        for(Arc Arc : graph.getArcs()){
            if(Arc.get_sommet1()==Sommet ||Arc.get_sommet2()==Sommet)
                neighbors.add(Arc);
        }

        return neighbors;
    }
//--------------------------------------------------------------------------------------------------------
//
    public Integer getDestinationDistance(){
        return distances.get(graph.getDestination());
    }
//--------------------------------------------------------------------------------------------------------
//
    public Integer getDistance(Sommet Sommet){
        return distances.get(Sommet);
    }
//-------------------------------------------------------------------------------------------
//
    public List<Sommet> getDestinationPath() {
        return getPath(graph.getDestination());
    }
//--------------------------------------------------------------------------------------------
//
    public List<Sommet> getPath(Sommet Sommet){
        List<Sommet> chemin = new ArrayList<>();

        Sommet current = Sommet;
        chemin.add(current);
        while (current!=graph.getSource()){
            current = predecessors.get(current);
            chemin.add(current);
        }

        Collections.reverse(chemin);

        return chemin;
    }


public static void main(String[] args) {
    

    JFrame frame = new JFrame();
    frame.setTitle("Plus Court Chemin - Algorithme Dijkstra");
    frame.setSize(new Dimension(700, 600));
    frame.setResizable(true); 
    frame.setLocationRelativeTo(null); //  centrer la fenetre
    frame.add(new MainWin());
    frame.setVisible(true);
}
}