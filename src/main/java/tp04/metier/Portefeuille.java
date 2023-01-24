/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {
    
    private Map<Action, LignePortefeuille> mapLignes;
    private ArrayList<Achat> achats;
    private ArrayList<Vente> ventes;

    public Map<Action, LignePortefeuille> getMapLignes() {
        return mapLignes;
    }

    public void setMapLignes(Map<Action, LignePortefeuille> mapLignes) {
        this.mapLignes = mapLignes;
    }

    public ArrayList<Achat> getAchats() {
        return achats;
    }

    public void setAchats(ArrayList<Achat> achats) {
        this.achats = achats;
    }

    public ArrayList<Vente> getVentes() {
        return ventes;
    }

    public void setVentes(ArrayList<Vente> ventes) {
        this.ventes = ventes;
    }

    public Portefeuille(Map<Action, LignePortefeuille> mapLignes, ArrayList<Achat> achats, ArrayList<Vente> ventes) {
        this.mapLignes = mapLignes;
        this.achats = achats;
        this.ventes = ventes;
    }
    
    public Portefeuille() {
        this.mapLignes = new HashMap();
        this.achats = new ArrayList<>();
        this.ventes = new ArrayList<>();
    }
    
    
    // Acheter une certaine quantité d'une action à un jour donné
    public void acheter(Action a, int q, Jour j) {
        
        this.achats.add(new Achat(a, j, q, a.valeur(j)));
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    // Vendre une certaine quantité d'une action à un jour donné
    public void vendre(Action a, int q, Jour j) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
                this.ventes.add(new Vente(a, j, q, a.valeur(j)));
            } 
            else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
                this.ventes.add(new Vente(a, j, q, a.valeur(j)));
            }
            else {
                System.out.println("** La quantité à vendre est supérieure à la quantité que vous possédez **");
            }
        }        
    }
    
    // Avoir la liste des actions qui ont augmenté (filtre_augmentation=True) ou diminué (filtre_augmentation=False)
    public ArrayList<Action> filtre(boolean filtre_augmentation, Jour j1, Jour j2) {
        
        ArrayList<Action> liste_actions = new ArrayList<>();
        for(Action act : this.mapLignes.keySet()) {
            if(filtre_augmentation) {
                if(act.variance(j1, j2)>0) {
                    liste_actions.add(act);
                }    
            }
            else {
                if(act.variance(j1, j2)<0) {
                    liste_actions.add(act);
                } 
            }
        }
        return liste_actions;
    }
    
    
    // Calculer les dépenses entre deux jours
    public float depenses(Jour j1, Jour j2) {
        float somme = 0;
        for (Achat a:this.achats) {
            if(a.getJour_achat().between(j1, j2)) {
                somme = somme + a.getQuantite()*a.getValeur();
            }
        }
        
        return somme;
    }
    
    
    // Calculer les recettes entre deux jours
    public float recettes(Jour j1, Jour j2) {
        float somme = 0;
        for (Vente v:this.ventes) {
            if(v.getJour_vente().between(j1, j2)) {
                somme = somme + v.getQuantite()*v.getValeur();
            }
        }
        
        return somme;
    }
    
    
    @Override
    public String toString() {
        return this.mapLignes.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
}
