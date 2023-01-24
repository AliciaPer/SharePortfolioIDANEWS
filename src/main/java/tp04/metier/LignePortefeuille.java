package tp04.metier;

/**
 *
 * @author Sidali
 */

public class LignePortefeuille {

    private final Action action;
    private int qte;

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Action getAction() {
        return this.action;
    }

    public LignePortefeuille(Action action, int qte) {
        this.action = action;
        this.qte = qte;
    }

    @Override
    public String toString() {
        return action.toString() + Integer.toString(qte);
    }
}
