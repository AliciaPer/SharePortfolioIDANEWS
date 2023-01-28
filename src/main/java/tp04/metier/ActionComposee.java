/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionComposee extends Action {
    // Une action composée est une liste d'actions simples et leurs pourcentages
    Map<ActionSimple, Float> mapPanier;

    // Constructeur
    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap();
    }
    
    // Enregistrer une action simple et son pourcentage dans l'action composée
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }

    // Calculer la valeur d'une action composée un jour donnée
    @Override
    public float valeur(Jour j) {
        float valeur = 0;
        // Pour chaque action simple de l'action composée
        for(ActionSimple as : this.mapPanier.keySet()) {
            // Ajouter sa valeur multipliée par le pourcentage
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }
        
        return valeur;
    }
    
    // Calculer la variance d'une action composée entre deux jours
    @Override
    public float variance(Jour i, Jour j) {
        return this.valeur(j)-this.valeur(i);
    }
    
}
