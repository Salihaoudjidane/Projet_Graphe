#include <iostream>
#include "PlusCourtChemin.h"
using namespace std;

int main()
{	cout<< endl<<"******************************PROJET LE PLUS COURT CHEMIN EN UTILISANT DIJKSTRA*****************************************"<<endl;
    PlusCourtChemin PCC;
    cout << endl;
    int reponse = 1;
    do{
        PCC.initialiser();
        PCC.Algo_Dijkstra();
        cout << endl;
        //cout << "****Les plus courts chemin partiels et leurs couts  :"<< endl;
        cout<<endl;   
        PCC.Afficher_Resultat();
        cout << "Voulez-vous continuer? tapez 1 pour continuer et 0 pour sortir" << endl;
        cin >> reponse;
   }
    while (reponse == 1);

   return 0;
}
