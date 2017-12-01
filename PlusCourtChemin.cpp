#include "PlusCourtChemin.h"
#include <iostream>
#include<istream>
using namespace std;

PlusCourtChemin::PlusCourtChemin()
{   int i,j;
	cout<<endl<<"****SAISIE DU GRAPHE :"<<endl;
    cout <<endl<< "      Le nombre de sommets:  ";
    cin >> Nb_Sommet ;
    graphe = new int * [Nb_Sommet];
    
    for(i = 0 ; i < Nb_Sommet ; i++)
        graphe[i] = new int [Nb_Sommet];
     
    cout << endl<<"        -Saisir 9999 en cas d'absence de distance directe entre 2 sommets differents"<<endl;
    cout <<endl<< "        -Saisir la valeur 0 pour la distance d'un sommet 0 a meme sommet" << endl;
    
    cout<<endl<<"****LES VALEURS DES ARCS :"<<endl;
    for (i = 0;i < Nb_Sommet ; i++)
        for(j = 0 ; j < Nb_Sommet ; j++)
    {
    	
        cout << endl<<"      Arc  " << "(" <<i<<","<<j<<")  :";
        cin >> graphe[i][j];
    }
    distance_minimale = new int [Nb_Sommet];
    distance_sommets = new bool[Nb_Sommet];
    predecesseur = new int [Nb_Sommet];
        }
//*****************************************************************************************************************************************
void controle_Nb_Sommet(){
	
	// a faire après	
	
}
//*****************************************************************************************************************************************
void PlusCourtChemin::initialiser(){
    
    cout << "****Entrez le Sommet source :    "; 
    cin >> sommet_source;
    cout << endl;
    for(int i = 0;i < Nb_Sommet ; i++)
        distance_minimale[i] = 9999; // distance infini,on considere que y a pas de chemin entre les differents sommets.
    for(int i = 0; i < Nb_Sommet ; i++)
        distance_sommets[i] = false;// aucune distance minimale n'a pas été trouvée.
    for(int i = 0; i < Nb_Sommet ; i++)
        predecesseur[i] = -1;
}
//*****************************************************************************************************************************************
void PlusCourtChemin::Algo_Dijkstra(){
    int i,j;
    for(j=0; j < Nb_Sommet;j++)
    {
        distance_minimale[j] = graphe[sommet_source][j];
        predecesseur[j] = sommet_source;
    }
   	distance_sommets[sommet_source] = true;
    distance_minimale[sommet_source] = 0;
    predecesseur[sommet_source] = sommet_source;
    for(i=0; i < Nb_Sommet-1;i++) //Nb_somment-1 par rpport au sommet source
    {
        int valeur_min = 9999;
        int k = sommet_source;
        for(j = 0; j < Nb_Sommet ; j++)
            if(!distance_sommets[j])
            if (distance_minimale[j] < valeur_min)
        	{
            	k = j;
            	valeur_min = distance_minimale[k];
       		}
        distance_sommets[k] = true;
        for(j = 0; j < Nb_Sommet ; j++)
            if(!distance_sommets[j])
            if (valeur_min + graphe[k][j] < distance_minimale[j])
            {
                distance_minimale[j] = valeur_min + graphe[k][j];
                predecesseur[j] = k;
            }
    }
}
//***********************************************************************************************************************************************
void PlusCourtChemin::Afficher_Resultat(){
    int i;
    for(i = 0 ; i < Nb_Sommet ; i++)
    {
        if(i == sommet_source)
            cout << sommet_source <<"  "<<sommet_source;
        else
			 Afficher_chemin(i);
        if(distance_minimale[i] != 9999)
        {
            cout << "         Distance minimale  :"<< distance_minimale[i]<<endl<<endl;
    	}
        else
	
            cout << "         Pas de chemin   "<< endl;
        
    }
}
//*************************************************************************************************************************************************
void PlusCourtChemin::Afficher_chemin (int  sommet)
    {
    if (sommet == sommet_source)
        cout << sommet << "  ";
    else
        {
            Afficher_chemin(predecesseur[sommet]);
            cout << sommet<< "  ";
        }
    }
