package tp04.metier;

import java.util.Objects;

/**
 *
 * @author Sidali
 */

public class Achat {
    // Un achat est définie par un utilisateur, un jour, une action, une quantité et une valeur
    private User user;
    private Action action;
    private Jour jour_achat;
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

    public Achat(User user, Action action, Jour jour_achat, int quantite, float valeur) {
        this.user = user;
        this.action = action;
        this.jour_achat = jour_achat;
        this.quantite = quantite;
        this.valeur = valeur;
    }  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + Objects.hashCode(this.action);
        hash = 17 * hash + Objects.hashCode(this.jour_achat);
        hash = 17 * hash + this.quantite;
        hash = 17 * hash + Float.floatToIntBits(this.valeur);
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
        final Achat other = (Achat) obj;
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
        return Objects.equals(this.jour_achat, other.jour_achat);
    }
    
    
}
