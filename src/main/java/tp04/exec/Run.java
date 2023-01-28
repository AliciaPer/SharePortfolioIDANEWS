/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.exec;

import tp04.metier.ActionComposee;
import tp04.metier.ActionSimple;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;
import tp04.metier.User;

public class Run {

    public static void main(String[] args) {
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        // affichage des cours - comme 1 action simple et 1 action
        System.out.println("Action simple *bnp* à j1 : " + bnp.valeur(j1));
        System.out.println("Action *Banque-Assurance* à j2 : " + bqAss.valeur(j2));

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        
        Portefeuille p;
        p = new Portefeuille();
        p.acheter(axa, 15, j1, sidali);
        System.out.println("Portefeuille : " + p);
        p.acheter(bnp, 20, j1, sidali);
        System.out.println("Portefeuille : " + p);
        p.acheter(bqAss, 5, j1, yurui);
        System.out.println("Portefeuille : " + p);
        p.vendre(axa, 5, j1, yurui);
        System.out.println("Portefeuille : " + p);
        p.vendre(bnp, 50, j1, sidali);
        System.out.println("Portefeuille : " + p);
        System.out.println("Portefeuille à j1 : " + p.valeur(j1));
        
        p.acheter(bqAss, 15, j2, sidali);
        System.out.println("Portefeuille : " + p);
        p.vendre(axa, 5, j2, sidali);
        System.out.println("Portefeuille : " + p);
        p.vendre(axa, 5, j2, sidali);
        System.out.println("Portefeuille : " + p);
        System.out.println("Portefeuille à j2 : " + p.valeur(j2));

 
    }

}
