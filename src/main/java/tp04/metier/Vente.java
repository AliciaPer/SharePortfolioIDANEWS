package tp04.metier;

/**
 *
 * @author Sidali
 */
public class Vente {
    private Action action;
    private Jour jour_vente;
    private int quantite;
    private float valeur;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Jour getJour_vente() {
        return jour_vente;
    }

    public void setJour_vente(Jour jour_vente) {
        this.jour_vente = jour_vente;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Vente(Action action, Jour jour_vente, int quantite, float valeur) {
        this.action = action;
        this.jour_vente = jour_vente;
        this.quantite = quantite;
        this.valeur = valeur;
    }
    
    
    
}
