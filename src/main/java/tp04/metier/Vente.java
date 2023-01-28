package tp04.metier;

import java.util.Objects;

/**
 *
 * @author Sidali
 */
public class Vente {
    // Une vente est définie par un utilisateur, un jour, une action, une quantité et une valeur
    private User user;
    private Action action;
    private Jour jour_vente;
    private int quantite;
    private float valeur;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    // Constructeur
    public Vente(User user, Action action, Jour jour_vente, int quantite, float valeur) {
        this.user = user;
        this.action = action;
        this.jour_vente = jour_vente;
        this.quantite = quantite;
        this.valeur = valeur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.user);
        hash = 71 * hash + Objects.hashCode(this.action);
        hash = 71 * hash + Objects.hashCode(this.jour_vente);
        hash = 71 * hash + this.quantite;
        hash = 71 * hash + Float.floatToIntBits(this.valeur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vente other = (Vente) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        if (Float.floatToIntBits(this.valeur) != Float.floatToIntBits(other.valeur)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        return Objects.equals(this.jour_vente, other.jour_vente);
    }
    
    
    
    
}
