package tp04.metier;

/**
 *
 * @author Sidali
 */

public class Achat {
    private Action action;
    private Jour jour_achat;
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

    public Jour getJour_achat() {
        return jour_achat;
    }

    public void setJour_achat(Jour jour_achat) {
        this.jour_achat = jour_achat;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Achat(Action action, Jour jour_achat, int quantite, float valeur) {
        this.action = action;
        this.jour_achat = jour_achat;
        this.quantite = quantite;
        this.valeur = valeur;
    }
    
    
}
