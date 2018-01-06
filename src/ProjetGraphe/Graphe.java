package ProjetGraphe;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graphe {
	
	    private int count = 1;
	    private List<Sommet> sommets = new ArrayList<>();
	    private List<Arc> arcs = new ArrayList<>();

	    private Sommet source;
	    private Sommet destination;

	    private boolean solved = false;

	    public void setSolved(boolean solved) {
	        this.solved = solved;
	    }

	    public boolean isSolved() {
	        return solved;
	    }

	    public void setsommets(List<Sommet> sommets){
	        this.sommets = sommets;
	    }

	    public  List<Sommet> getSommets(){
	        return sommets;
	    }

	    public void setArcs(List<Arc> arcs){
	        this.arcs = arcs;
	    }

	    public List<Arc> getArcs(){
	        return arcs;
	    }
//----------------------------------------------------------------------------------------------------------------------
// Tester si le sommet n'est pas isolé
	    public boolean SommetAccessible(Sommet sommet){
	        for(Arc arc : arcs)
	            if(sommet == arc.get_sommet1() || sommet == arc.get_sommet2())
	                return true;

	        return false;
	    }

//----------------------------------------------------------------------------------------------------------------------
//Selectionner le sommet source
	    public void setSource(Sommet sommet){
	        if(sommets.contains(sommet))
	            source = sommet;
	    }
	   
	    public Sommet getSource(){
	        return source;
	    }
//---------------------------------------------------------------------------------------------------------------------
//selectionner le sommet dist
	    public void setDestination(Sommet sommet){
	        if(sommets.contains(sommet))
	            destination = sommet;
	    }

	    public Sommet getDestination(){
	        return destination;
	    }
//-----------------------------------------------------------------------------------------------------
//Teste si un sommet peut etre un sommet source
	    public boolean isSource(Sommet sommet){
	        return sommet == source;
	    }
//------------------------------------------------------------------------------------------------------
//Teste si un sommet peut etre un sommet destinataire 
	    public boolean isDestination(Sommet sommet){
	        return sommet == destination;
	    }
//------------------------------------------------------------------------------------------------------
	    public void addsommet(Point coord){
	        Sommet sommet = new Sommet(coord);
	        addsommet(sommet);
	    }
//-------------------------------------------------------------------------------------------------------
// Ajout d'un sommet
	    public void addsommet(Sommet sommet){
	        sommet.setId(count++);
	        sommets.add(sommet);
	        if(sommet.getId()==1)// identifiant du sommet source
	            source = sommet;
	    }
//--------------------------------------------------------------------------------------------------------
// Ajout d'un arc
	    public void addEdge(Arc new_arc){
	        boolean ajouter = false;
	        for(Arc arc: arcs){
	            if(arc.equals(new_arc)){
	                ajouter = true;
	                break;
	            }
	        }
	        if(!ajouter)
	            arcs.add(new_arc);
	    }
//-------------------------------------------------------------------------------------------------------
// Suppressio d'un sommet
	    public void deletesommet(Sommet sommet){
	        List<Arc> delete = new ArrayList<>();
	        for (Arc edge : arcs){
	            if(edge.hasNode(sommet)){
	                delete.add(edge);
	            }
	        }
	        for (Arc arc : delete){
	            arcs.remove(arc);
	        }
	        sommets.remove(sommet);
	    }
//---------------------------------------------------------------------------------------------------
//
	    public void clear(){
	        count = 1;
	        sommets.clear();
	        arcs.clear();
	        solved = false;

	        source = null;
	        destination = null;
	    }

	}
