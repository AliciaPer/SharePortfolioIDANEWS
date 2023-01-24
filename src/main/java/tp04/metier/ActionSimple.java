package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private final Map<Jour, Cours> mapCours;
    
    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap();
    }
    
    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        if(this.mapCours.containsKey(j) == false)
            this.mapCours.put(j, new Cours(j, v));
    }
    
    @Override
    public float valeur(Jour j) {
        if(this.mapCours.containsKey(j) == true)
            return this.mapCours.get(j).getValeur();
        else 
            return 0; // definition d'une constante possible
    }
    
    /**
     *
     * @param j1
     * @param j2
     * @return
     */
    @Override
    public float variance(Jour j1, Jour j2) {
        return this.valeur(j2)-this.valeur(j1);
    }
  
    // encapsulation de la définition de la classe Cours
    private class Cours {
        
        private final Jour jour;
        private final float valeur;

        public float getValeur() {
            return valeur;
        }
        
        public Jour getJour() {
            return jour;
        }

        public Cours(Jour jour, float valeur) {
            this.jour = jour;
            this.valeur = valeur;
        }

    }
}
