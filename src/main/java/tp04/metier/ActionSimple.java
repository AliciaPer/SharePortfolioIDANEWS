package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // Une action simple est définie par une liste de paires (jour, cours)
    private final Map<Jour, Cours> mapCours;
    
    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap();
    }
    
    // enregistrement possible si on a pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        if(this.mapCours.containsKey(j) == false)
            this.mapCours.put(j, new Cours(j, v));
    }
    
    // Calculer la valeur d'une action  à un jour donné
    @Override
    public float valeur(Jour j) {
        // Si un cours a été enregistré pour le jour donné
        if(this.mapCours.containsKey(j) == true)
            // Retourner la valeur
            return this.mapCours.get(j).getValeur();
        else 
            // Sinon retourner 0
            return 0;
    }
    
    /**
     *
     * @param j1
     * @param j2
     * @return
     */
    // Calculer la variance des valeurs d'une action entre deux jours
    @Override
    public float variance(Jour j1, Jour j2) {
        return this.valeur(j2)-this.valeur(j1);
    }
  
}
