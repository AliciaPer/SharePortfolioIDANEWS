package tp04.metier;

import java.util.Objects;

/**
 *
 * @author perussel
 */
public abstract class Action {
    
    private final String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    public Action(String libelle) {
        this.libelle = libelle;
    }

    public abstract float valeur(Jour j);
    
    public abstract float variance(Jour i, Jour j);
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }
}
