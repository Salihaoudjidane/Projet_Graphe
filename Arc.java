package ProjetGraphe;

	public class Arc {
	    private Sommet sommet_1;
	    private Sommet sommet_2;
	    private int weight = 0;
//------------------------------------------------------------------------------------------
//definir un arc
	    public Arc(Sommet sommet_1, Sommet sommet_2){
	        this.sommet_1 = sommet_1;
	        this.sommet_2 = sommet_2;
	    }
//-----------------------------------------------------------------------------------------
//
	    public Sommet get_sommet1(){
	        return sommet_1;
	    }

	    public Sommet get_sommet2(){
	        return sommet_2;
	    }

	    public void setWeight(int weight){
	        this.weight = weight;
	    }

	    public int getWeight(){
	        return weight;
	    }

	    public boolean hasNode(Sommet som){
	        return sommet_1== som || sommet_2== som;
	    }

	    public boolean equals(Arc arc) {
	        return (sommet_1 == arc.sommet_1 && sommet_2 == arc.sommet_2) || (sommet_1 == arc.sommet_2 && sommet_2 == arc.sommet_1) ;
	    }

	    @Override
//---------------------------------------------------------------------------------------------------------------------------------------
//Entrer la valeur d'un arc entre deux sommets
	    public String toString() {
	        return "Arc :"
	                + get_sommet1().getId() + " - "
	                + get_sommet2().getId();
	    }
	}
