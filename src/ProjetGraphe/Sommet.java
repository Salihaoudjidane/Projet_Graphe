package ProjetGraphe;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Sommet {
    private Point coord = new Point();
    private int id;
    private java.util.List<Sommet> chemin;
    
    public Sommet(){}
    
    public Sommet(int id){
        this.id = id;
    }
//------------------------------------------------------------
    public Sommet(Point p){
        this.coord = p;
    }
//------------------------------------------------------------
    public void setId(int id){
        this.id = id;
    }
//-----------------------------------------------------------
    public void setCoord(int x, int y){
        coord.setLocation(x, y);
    }
//------------------------------------------------------------
    public Point getCoord(){
        return coord;
    }
//------------------------------------------------------------
    public void setPath(List<Sommet> path) {
        this.chemin = path;
    }
//------------------------------------------------------------
    public List<Sommet> getPath() {
        return chemin;
    }
//-------------------------------------------------------------
    public int getX(){
        return (int) coord.getX();
    }
//-------------------------------------------------------------
    public int getY(){
        return (int) coord.getY();
    }
//-------------------------------------------------------------
    public int getId(){
        return id;
    }
//-------------------------------------------------------------
    @Override
    public String toString() {
        return "Sommet " + id;
    }
}
