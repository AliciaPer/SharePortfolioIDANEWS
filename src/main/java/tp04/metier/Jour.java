/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

/**
 *
 * @author perussel
 */
public class Jour {
    // Un jour est défini par l'année et le numéro du jour dans l'année
    private final int annee;
    private final int noJour;

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public int getAnnee() {
        return annee;
    }


    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    public int getNoJour() {
        return noJour;
    }

    // Constructeur
    public Jour(int annee, int noJour) {
        this.annee = annee;
        this.noJour = noJour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.annee;
        hash = 61 * hash + this.noJour;
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
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        if (this.noJour != other.noJour) {
            return false;
        }
        return true;
    }
    
    // Cette méthode permet de vérifier si un jour est entre les deux jours j1 et j2
    public boolean between(Jour j1, Jour j2) {
        // Si l'année du jour donnée est inférieure à celle du premier jour de l'intervalle
        if(this.annee < j1.annee) {
            // Alors il est pas entre les deux jours
            return false;
        }
        // S'il a la meme année que le premier jour d'intervalle
        else if(this.annee==j1.annee) {
            // S'il vient avant le jour j1
            if(this.noJour < j1.noJour) {
                // Alors il est pas entre les deux jours
                return false;
            }
        }
        // Si l'année du jour donnée est supérieure à celle du deuxieme jour de l'intervalle
        if(this.annee > j2.annee) {
            // Alors il est pas entre les deux jours
            return false;
        }
        // Sinon s'il a la meme année que le deuxieme jour
        else if(this.annee==j2.annee) {
            // Et qu'il vient aprés le deuxieme jour
            if(this.noJour > j2.noJour) {
                // Alors il est pas entre les deux jours
                return false;
            }
        }
        // S'il est pas dans tous les cas déjà traités, c'est qu'il est entre les deux jours j1 et j2
        return true;
    }
  
}
