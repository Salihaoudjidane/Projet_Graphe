package ProjetGraphe;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class DessinObjets {
		
	    private Graphics2D g;
	    private static int radius = 20;

	    public DessinObjets(Graphics2D graphics2D){
	        g = graphics2D;
	    }

//----------------------------------------------------------------------------------------------------------------------
// Teste si le sommet est superposé
	   public static boolean sommetsuperpose(MouseEvent e, Point p) {
	        int x = e.getX();
	        int y = e.getY();

	        int boundX = (int) p.getX();
	        int boundY = (int) p.getY();

	        return (x <= boundX +3.0*radius && x >= boundX - 2.5*radius) && (y <= boundY + 2.5*radius && y >= boundY - 2.5*radius);
	    }
	   
//------------------------------------------------------------------------------------------------------------------------
// selectionner l'arc à valuer quand on clic dessus
	    
	   public static boolean isOnEdge(MouseEvent e, Arc arc) {

	        int distance = DistanceSeg(e.getPoint(),
	                                  arc.get_sommet1().getCoord(),
	                                  arc.get_sommet2().getCoord() );
	        if (distance<10)
	            return true;
	        return false;
	    }
	    
//-----------------------------------------------------------------------------------------------
//
	
	   
//-----------------------------------------------------------------------------------------------------	   
	    public static Color parseColor(String colorStr) {
	        return new Color(
	                Integer.valueOf(colorStr.substring(1, 3), 16),
	                Integer.valueOf(colorStr.substring(3, 5), 16),
	                Integer.valueOf(colorStr.substring(5, 7), 16));
	    }
//---------------------------------------------------------------------------------------------------------------------
//	    
	    public static boolean isWithinBounds(MouseEvent e, Point p) {
	        int x = e.getX();
	        int y = e.getY();

	        int boundX = (int) p.getX();
	        int boundY = (int) p.getY();

	        return (x <= boundX + radius && x >= boundX - radius) && (y <= boundY + radius && y >= boundY - radius);
	    }
//--------------------------------------------------------------------------------------------------
	    
//
	    public void DessinValeur(Arc arc) {
	    	
	    	
	        Point sommet1 = arc.get_sommet1().getCoord();
	        Point sommet2 = arc.get_sommet2().getCoord();
	        int x = (sommet1.x + sommet2.x)/2;
	        int y = (sommet1.y + sommet2.y)/2;

	        int rad = radius/2;
	        g.fillOval(x-rad, y-rad, 2*rad, 2*rad);
	        DessinValArc(String.valueOf(arc.getWeight()), x, y);
	       
	    }
//-------------------------------------------------------------------------------------------------------
//
	    public void DessinChemin(java.util.List<Sommet> chemin) {
	    	List<Arc> arcs = new ArrayList<>();
	        for(int i = 0; i < chemin.size()-1; i++) {
	            arcs.add(new Arc(chemin.get(i), chemin.get(i+1)));
	        }

	        for(Arc arc : arcs) {
	        	DessinChemin(arc);
	        }
	    }
//--------------------------------------------------------------------------------------------------------
//Dessiner le plus court chemin entre 2 sommets  
	   public void DessinChemin(Arc arc) {
	        g.setColor(parseColor("#3fd400"));
	        DessinArc(arc);
	    }
	    
//--------------------------------------------------------------------------------------------------------
// 
	  
	    public void ArcProv(Arc arc) {
	        g.setColor(parseColor("#000000"));
	        DessinArc(arc);
	    }
	    
//----------------------------------------------------------------------------------------------------------
//Dessiner le rond qui comporte la valeur d'un arc 
	    
	    private void DessinArc(Arc arc){
	        Point sommet1= arc.get_sommet1().getCoord(); // récuperer les coordonées d'un sommet
	        Point sommet2= arc.get_sommet2().getCoord();
	        g.setStroke(new BasicStroke(5));			//Contour de l'arc en passant la souris dessus
	        g.drawLine(sommet1.x, sommet1.y, sommet2.x, sommet2.y); // dessiner le ligne reliant les deux points
	        int x = (sommet1.x + sommet2.x)/2;
	        int y = (sommet1.y + sommet2.y)/2;                   // dessiner le le rond au milieu de l'arc
	        int rad = 13;
	        g.fillOval(x-rad, y-rad, 2*rad, 2*rad);
	    
	    }
//-------------------------------------------------------------------------------------------------------------
//Dessiner l'arc en lui affectant la couleur arc+le rond de la valeur
	   
	    public void drawEdge(Arc arc) {
	        g.setColor(parseColor("#555555"));
	        drawBaseEdge(arc);     
	        DessinValeur(arc);    // appel de la methode pour dessiner le rond de la valeur d'un arc
	    }
//---------------------------------------------------------------------------------------------------------------
// Dessiner la droite entre les deux sommets
	   private void drawBaseEdge(Arc arc){
	        Point from = arc.get_sommet1().getCoord();
	        Point to = arc.get_sommet2().getCoord();
	        g.setStroke(new BasicStroke(3));
	        g.drawLine(from.x, from.y, to.x, to.y);
	    }
//-----------------------------------------------------------------------------------------------------------------
// Dessin du premier sommet
	    public void DessinSommetSource(Sommet som){
	        radius-=5;
	        g.setColor(parseColor("#00d42d"));          // premier sommet
	        g.fillOval(som.getX() - radius, som.getY() - radius, 2 * radius, 2 * radius);

	        radius+=5;
	        g.setColor(parseColor("#f2f7f3"));          // couleur du texte a l'interieur du sommet
	        EcrireTexte(String.valueOf(som.getId()), som.getX(), som.getY());
	    }
//----------------------------------------------------------------------------------------------------------------
// Dessin du sommet destination
	    public void DessinSommetDest(Sommet som){
	        radius-=5;									// pour que le sommet n'agrandit pas
	        g.setColor(parseColor("#FFCDD2"));			// sommet de destination
	        g.fillOval(som.getX() - radius, som.getY() - radius, 2 * radius, 2 * radius);

	        radius+=5;
	        g.setColor(parseColor("#F44336"));
	        EcrireTexte(String.valueOf(som.getId()), som.getX(), som.getY()); // texte du sommet 
	    }
//--------------------------------------------------------------------------------------------------------------------
//Dessin des autres sommets
	    public void DessinSommets(Sommet som){
	        radius-=5;
	        g.setColor(parseColor("#E1BEE7"));		
	        g.fillOval(som.getX() - radius, som.getY() - radius, 2 * radius, 2 * radius);	//sommets

	        radius+=5;
	        g.setColor(parseColor("#f2f7f3"));		//texte dans le sommet
	        EcrireTexte(String.valueOf(som.getId()), som.getX(), som.getY());
	    }
//-----------------------------------------------------------------------------------------------------------------------
//Dessiner la valeur de l'arc 
	    public void DessinValArc(String text, int x, int y) {
	        g.setColor(parseColor("#FFFFFF"));	    	// texte du rond d'un arc
	        FontMetrics font_met = g.getFontMetrics();
	        double valeur = font_met.getStringBounds(text, g).getWidth();
	        g.drawString(text, (int) (x - valeur / 2), (y + font_met.getMaxAscent() / 2));    // afficher cette valeur avec ses coordonnées
	    }
//-----------------------------------------------------------------------------------------------------------------------
// Ecrire du texte dans les sommets:
	   public void EcrireTexte(String text, int x, int y) {
	        FontMetrics font_met = g.getFontMetrics();
	        	        
	        double val = font_met.getStringBounds(text, g).getWidth();
	       
	        g.drawString(text, (int) (x - val / 2), (y + font_met.getMaxAscent() / 2));
	    }

//----------------------------------------------------------------------------------------------------------------------
//Calcul du sqr
	    private static int sqr(int x) {
	        return x * x;
	    }
//-----------------------------------------------------------------------------------------------------------------------
//
	    private static int distance(Point v, Point w) {
	        return sqr(v.x - w.x) + sqr(v.y - w.y);
	    }
//---------------------------------------------------------------------------------------------------------------------
//
	    private static int CalculDistance(Point p, Point v, Point w) {
	        double longueur  = distance(v, w);
	        if (longueur == 0) 
	        	return distance(p, v);
	        double var = ((p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y)) / longueur;
	        if (var < 0) return distance(p, v);
	        if (var > 1) return distance(p, w);
	        return distance(p, new Point(
	                (int)(v.x + var * (w.x - v.x)),
	                (int)(v.y + var * (w.y - v.y))
	        ));
	    }
//---------------------------------------------------------------------------------------------------------------------
//
	   private static int DistanceSeg(Point p, Point v, Point w) {
	        return (int) Math.sqrt(CalculDistance(p, v, w));
	    }

	}
