package tp04.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author perussel
 */
public class Portefeuille {
    
    private Map<Action, LignePortefeuille> mapLignes;
    private ArrayList<Achat> achats;
    private ArrayList<Vente> ventes;
    private ArrayList<User> users;

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    // Constructeur
    public Portefeuille(Map<Action, LignePortefeuille> mapLignes, ArrayList<Achat> achats, ArrayList<Vente> ventes, ArrayList<User> users) {
        this.mapLignes = mapLignes;
        this.achats = achats;
        this.ventes = ventes;
        this.users = users;
    }
    
    // Constructeur
    public Portefeuille() {
        this.mapLignes = new HashMap();
        this.achats = new ArrayList<>();
        this.ventes = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    
    // Ajouter un utilisateur à un portefeuille
    public void addUser(User u) {
        // Si l'utilisateur u n'exsite pas déjà dans le portefeuille
        if(this.users.contains(u)==false) {
            // L'ajouter
            this.users.add(u);
        } 
    }
    
    // Supprimer un utilisateur d'un portefeuille
    public void removeUser(User u) {
        // Si l'utilisateur u existait 
        if(this.users.contains(u)) {
            // Le retirer
            this.users.remove(u);
        }     
    }
    
    // Acheter une certaine quantité d'une action à un jour donné par un utilisateur
    public void acheter(Action a, int q, Jour j, User u) {
        // Si l'utilisateur u figure dans la liste des users du portefeuille
        if(this.users.contains(u)) {
            // Enregistrer l'achat
            this.achats.add(new Achat(u, a, j, q, a.valeur(j)));
            // Si l'action existait pas dans le porteuille
            if (this.mapLignes.containsKey(a) == false) {
                // Ajouter l'action et sa quantité
                this.mapLignes.put(a, new LignePortefeuille(a, q));
            } else {
                // Sinon juste rajouter la quantité achetée
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
            }
        }
        // Afficher un message d'erreur si l'utilisateur n'a pas le droit d'effectuer cette opération
        else {
            System.out.println("\n** L'utilisateur "+ u.getName() +" n'a pas le droit d'effectuer cette operation **");
        }
        
    }

    // Vendre une certaine quantité d'une action à un jour donné par un utilisateur
    public void vendre(Action a, int q, Jour j, User u) {
        // Si l'utilisateur u figure dans la liste des users du portefeuille
        if(this.users.contains(u)) {
            // Si l'action existe dans le portefeuille
            if(this.mapLignes.containsKey(a) == true) {
                // Si on a assez de quantité de cette action pour vendre
                if (this.mapLignes.get(a).getQte() > q) {
                    // Effectuer la vente
                    this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
                    // Et l'enregistrer
                    this.ventes.add(new Vente(u, a, j, q, a.valeur(j)));
                }
                // Si la quantité restante est égale à la quantité à vendre
                else if (this.mapLignes.get(a).getQte() == q) {
                    // On effectue la vente et on supprime l'action (quantité=0)
                    this.mapLignes.remove(a);
                    // Et on enregistre la vente
                    this.ventes.add(new Vente(u, a, j, q, a.valeur(j)));
                }
                else {
                    // Afficher un message d'erreur so la quantité à vendre est supérieure à la quantité restante
                    System.out.println("\n** La quantite a vendre est superieure a la quantite que vous possedez **");
                }
            }        
        }
        // Afficher un message d'erreur si l'utilisateur n'a pas le droit d'effectuer cette opération
        else {
            System.out.println("\n** L'utilisateur "+ u.getName() +" n'a pas le droit d'effectuer cette operation **");
        } 
    }
    
    // Avoir la liste des actions qui ont augmenté (filtre_augmentation=True) ou diminué (filtre_augmentation=False)
    public ArrayList<Action> filtre(boolean filtre_augmentation, Jour j1, Jour j2) {
        
        ArrayList<Action> liste_actions = new ArrayList<>();
        // Pour chaque action
        for(Action act : this.mapLignes.keySet()) {
            // Si on cherche les actions dont la valeur a augmenté
            if(filtre_augmentation) {
                // Si la variance de la valeur de l'action est positif
                if(act.variance(j1, j2)>0) {
                    // On l'enregistre dans la liste
                    liste_actions.add(act);
                }    
            }
            // Si on cherche les actions dont la valeur a diminué
            else {
                // Si la variance de la valeur de l'action est négatif
                if(act.variance(j1, j2)<0) {
                    // On l'enregistre dans la liste
                    liste_actions.add(act);
                } 
            }
        }
        return liste_actions;
    }
    
    
    // Avoir l'historique des achats entre deux jours donnés
    public ArrayList<Achat> historique_achat(Jour j1, Jour j2) {
        
        ArrayList<Achat> liste_achats = new ArrayList<>();
        // Pour chaque achat
        for(Achat a : this.achats) {
            // Si l'achat a été fait entre les deux jours donnés
            if(a.getJour_achat().between(j1, j2)) {
                // L'enregistrer dans l'historique
                liste_achats.add(a); 
            } 
        }
        return liste_achats;
    }
    
    
    // Avoir l'historique des ventes entre deux jours donnés
    public ArrayList<Vente> historique_vente(Jour j1, Jour j2) {
        
        ArrayList<Vente> liste_ventes = new ArrayList<>();
        // Pour chasue vente
        for(Vente v : this.ventes) {
            // Si la vente a été faite entre les deux jours donnés
            if(v.getJour_vente().between(j1, j2)) {
                // L'enregistrer dans l'historique
                liste_ventes.add(v); 
            } 
        }
        return liste_ventes;
    }
    
    
    // Calculer les dépenses entre deux jours
    public float depenses(Jour j1, Jour j2) {
        float somme = 0;
        // Pour chaque achat
        for (Achat a:this.achats) {
            // Si l'achat a été fait entre les deux jours j1 et j2
            if(a.getJour_achat().between(j1, j2)) {
                // l'ajouter à la somme
                somme = somme + a.getQuantite()*a.getValeur();
            }
        }
        
        return somme;
    }
    
    
    // Calculer les recettes entre deux jours
    public float recettes(Jour j1, Jour j2) {
        float somme = 0;
        // Pour chaque vente
        for (Vente v:this.ventes) {
            // Si la vente a été faite entre les deux jours j1 et j2
            if(v.getJour_vente().between(j1, j2)) {
                // Ajouter la recette à la somme totale
                somme = somme + v.getQuantite()*v.getValeur();
            }
        }
        
        return somme;
    }
    
    
    // Avoir l'historique des achats d'un utilisateur entre deux jours donnés
    public ArrayList<Achat> historique_achat_user(User u, Jour j1, Jour j2) {
        
        ArrayList<Achat> liste_achats = new ArrayList<>();
        // Pour chaque achat
        for(Achat a : this.achats) {
            // Si l'achat a été fait entre les jours j1 et j2 par l'utilisateur u
            if(a.getJour_achat().between(j1, j2) && a.getUser()==u) {
                // Enregistrer cet achat dans la liste
                liste_achats.add(a); 
            } 
        }
        return liste_achats;
    }
    
    
    // Avoir l'historique des ventes entre deux jours donnés
    public ArrayList<Vente> historique_vente_user(User u, Jour j1, Jour j2) {
        
        ArrayList<Vente> liste_ventes = new ArrayList<>();
        // Pour chaque vente
        for(Vente v : this.ventes) {
            // Si la vente a été faite entre les deux jours j1 et j2 par l'utilisateur u
            if(v.getJour_vente().between(j1, j2) && v.getUser()==u) {
                // Enregistrer cette vente dans la liste
                liste_ventes.add(v); 
            } 
        }
        return liste_ventes;
    }
    
    
    @Override
    public String toString() {
        return this.mapLignes.toString();
    }

    // Avoir la valeur totale de mon portefeuille à un jour donné
    public float valeur(Jour j) {
        float total = 0;
        // Pour chaque action du portefeuille
        for (LignePortefeuille lp : this.mapLignes.values()) {
            // Rajouter la somme de la valeur et la quantité au total 
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
    
    // Cette fonction permet de calculer le profit réalisé pour un portefeuille entre deux jours
    public float profit(Jour debut, Jour fin) {
        // Calculer les dépenses
        float depenses = this.depenses(debut, fin);
        // Calculer les recettes
        float recettes = this.recettes(debut, fin);
        // Retourner la différence
        return recettes - depenses;
    }
    
    // Cette fonction permet de classer les acttions selon leurs profits entre deux jours
    public SortedMap<Float, Action> actions_ranking(Jour debut, Jour fin) {
        SortedMap<Float, Action> ranking = new TreeMap<>(Collections.reverseOrder()); // Ordre ascendant
        // Pour chaque action
        for (LignePortefeuille lp : this.mapLignes.values()) {
            // ajouter l'action et sa variance à la liste triée
            ranking.put(lp.getAction().variance(debut, fin), lp.getAction());
        }
        return ranking;
    }
    
    // Cette fonction permet de classer l'efficacité des opérations des utilisateurs entre deux jours
    public SortedMap<Float, User> users_ranking(Jour debut, Jour fin) {
        SortedMap<Float, User> ranking = new TreeMap<>(Collections.reverseOrder()); // Ordre descendant
        float score = 0F;
        // Pour chaque utilisateur
        for (User u : this.getUsers()) {
            // On déduit les dépenses de cet utilisateur
            for (Achat a : this.historique_achat_user(u, debut, fin)) {
                score = score - a.getValeur()*a.getQuantite();
            }
            // Puis on rajoute ses recettes
            for (Vente v : this.historique_vente_user(u, debut, fin)) {
                score = score + v.getValeur()*v.getQuantite();
            }
            // Et à la fin on rajoute la somme des variances des actions qu'il a acheté multiplées par la quantité
            for (Achat a : this.historique_achat_user(u, debut, fin)) {
                score = score + a.getAction().variance(debut, fin)*a.getQuantite();
            }
            // On ajoute l'utilisateur et son score à la liste triée
            ranking.put(score, u);
        }
        return ranking;
    }

    
}
