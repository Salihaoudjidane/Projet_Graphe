#ifndef GRAPHE_H
#define GRAPHE_H
#include <iostream>
using namespace std;
class PlusCourtChemin
{
    public:
        PlusCourtChemin(); // constructeur 
        void 	initialiser();
        void 	Algo_Dijkstra();// algorithme de dijkstra
        void 	controle_Nb_Sommet();// controler le nombre de sommets saisi
        void 	Afficher_Resultat();//afficher le chemin
        void 	Afficher_chemin(int noeud);
    private:
        int		** graphe; 
        int 	Nb_Sommet;
        int 	* predecesseur;
        int  	* distance_minimale;
        bool 	* distance_sommets;//si la distance minimale pour un sommet est trouvée ou non
        int 	 sommet_source;// chercher le plus court chemin de ce sommet vers les autres sommets
};

#endif // GRAPHE_H
