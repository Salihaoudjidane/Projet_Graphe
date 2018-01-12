package ProjetGraphe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {

    private DessinObjets DessinObjets;
    private Graphe graph;
    private Sommet selectSommet = null;
    private Sommet hoveredNode = null;
    private Arc hoveredEdge = null;
    private java.util.List<Sommet> path = null;
    private Point cursor;

    public GraphPanel(Graphe graph){
        this.graph = graph;

        addMouseListener(this);
        addMouseMotionListener(this);
    }
 
//-----------------------------------------------------------------------------------------------------------
//Dessiner les differents composants : sommets,arcs 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D composant= (Graphics2D) g;
        composant.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        composant.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        DessinObjets = new DessinObjets(composant);

        if(graph.isSolved()){
            DessinObjets.DessinChemin(path);
        }

        if(selectSommet != null && cursor != null){
            Arc e = new Arc(selectSommet, new Sommet(cursor));
            DessinObjets.drawEdge(e); 
        }

       for(Arc arc : graph.getArcs()){
            if(arc == hoveredEdge)
                DessinObjets.ArcProv(arc);
            DessinObjets.drawEdge(arc);      // dessiner un arc
       }
        for(Sommet sommet : graph.getSommets()){
            
            if(graph.isSource(sommet))
                DessinObjets.DessinSommetSource(sommet);
            else if(graph.isDestination(sommet))
                DessinObjets.DessinSommetDest(sommet);
            else
                DessinObjets.DessinSommets(sommet);
        }
    }
//------------------------------------------------------------------------------------------------------
    
    public void setPath(List<Sommet> path) {
        this.path = path;
        hoveredEdge = null;
        repaint();
    }
//-----------------------------------------------------------------------------------------------------------
//Méthode appelée lors du clic de souris
    @Override
    public void mouseClicked(MouseEvent event) {

        Sommet som_selected = null; // sommet selectionné
       for(Sommet sommet : graph.getSommets()) {
            if(DessinObjets.isWithinBounds(event, sommet.getCoord())){
                som_selected = sommet;
                break;
            }
        }
       //JButton boutonsup = new JButton("Supprimer");

        if(som_selected!=null) {
        	//if(event.isControlDown() && event.isShiftDown())
        	if(event.isControlDown()){   		//supprimer un sommet
                graph.deletesommet(som_selected);
                graph.setSolved(false);
                repaint();
                return;
            } 
        	else 
        		if(event.isControlDown() && graph.isSolved()){
                path = som_selected.getPath();
                //repaint();
                return;
            } else if(event.isShiftDown()){
                if(SwingUtilities.isLeftMouseButton(event)){
                    if(!graph.isDestination(som_selected))
                        graph.setSource(som_selected);
                    else
                        JOptionPane.showMessageDialog(null, "Le sommet ne peut pas être un sommet source ");
                } else if(SwingUtilities.isRightMouseButton(event)) {
                    if(!graph.isSource(som_selected))
                        graph.setDestination(som_selected);
                    else
                        JOptionPane.showMessageDialog(null, "Le sommet source ne peut être un sommet de destination");
                }else
                    return;

                graph.setSolved(false);
                //repaint();
                return;
            }
        }

        if(hoveredEdge!=null){
            //if(event.isControlDown() && event.isShiftDown()){
            	 if(event.isControlDown()){
                graph.getArcs().remove(hoveredEdge);
                hoveredEdge = null;
                graph.setSolved(false);
               // repaint();
                return;
            }

            String input = JOptionPane.showInputDialog(hoveredEdge.toString()
                                                        + " : ");
            try {
                int valeur = Integer.parseInt(input);
                if (valeur > 0) {
                    hoveredEdge.setWeight(valeur);
                    graph.setSolved(false);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "La valeur doit être positive");
                }
            } catch (NumberFormatException nfe) {}
            return;
        }

       for(Sommet node : graph.getSommets()) {
            if(DessinObjets.sommetsuperpose(event, node.getCoord())){    // Un sommet superposé sur un autre sommet
                JOptionPane.showMessageDialog(null, "Impossible de créer un sommet superposé");
                return;
            }
        }

        graph.addsommet(event.getPoint());
        graph.setSolved(false);
        //repaint();
    }
//------------------------------------------------------------------------------------------------------------
//Méthode appelée lorsque l'on presse le bouton gauche de la souris
    //@Override
    public void mousePressed(MouseEvent e) {

    }
//-------------------------------------------------------------------------------------------------------------
//Méthode appelée lorsque l'on relâche le clic de souris pour creer les atcs entre les sommets
    //@Override
    public void mouseReleased(MouseEvent e) {
        for (Sommet sommet : graph.getSommets()) {
            if(selectSommet !=null && sommet!= selectSommet && DessinObjets.isWithinBounds(e, sommet.getCoord())){
                Arc new_arc = new Arc(selectSommet, sommet);
                graph.addEdge(new_arc);
                graph.setSolved(false);
            }
        }
        selectSommet = null;
        hoveredNode = null;
        repaint();
    }
   
//---------------------------------------------------------------------------------------------------------
//Méthode appelée lors du survol de la souris
    @Override
    public void mouseEntered(MouseEvent e) {

    }
//---------------------------------------------------------------------------------------------------------
//Méthode appelée lorsque la souris sort de la zone du bouton
    @Override
    public void mouseExited(MouseEvent e) {

    }
//--------------------------------------------------------------------------------------------------------
//
    @Override
    public void mouseDragged(MouseEvent e) {
        hoveredNode = null;

        for (Sommet node : graph.getSommets()) {
            if(selectSommet ==null && DessinObjets.isWithinBounds(e, node.getCoord())){
            	selectSommet = node;
            } else if(DessinObjets.isWithinBounds(e, node.getCoord())) {
                hoveredNode = node;
            }
        }

        if(selectSommet !=null){
            if(e.isControlDown()){
            	selectSommet.setCoord(e.getX(), e.getY());
                cursor = null;
                repaint();
                return;
            }

            cursor = new Point(e.getX(), e.getY());
            repaint();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------
//
    @Override
    public void mouseMoved(MouseEvent e) {

        /*if(e.isControlDown()){
            hoveredNode = null;
            Graphe graph = new Graphe();
            for (Sommet node : graph.getSommets()) {
                if(DessinObjets.isWithinBounds(e, node.getCoord())) {
                    hoveredNode = node;
                }
            }
        }*/

        hoveredEdge = null;

        for (Arc arc : graph.getArcs()) {    // Modifier les valeurs des arcs avec un clic dessus
            if(DessinObjets.isOnEdge(e, arc)) {
                hoveredEdge = arc;
            }
        }

        repaint();
      
    }
//------------------------------------------------------------------------------------------------------------------------
//
    public void reset(){
        graph.clear();
        selectSommet = null;
        hoveredNode = null;
        hoveredEdge = null;
        repaint();
    }
}
