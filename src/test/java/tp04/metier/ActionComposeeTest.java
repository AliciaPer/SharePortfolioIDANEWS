package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alicia Perez
 */
public class ActionComposeeTest {
    
    public ActionComposeeTest() {
    }

    @Test
    public void testEnregistrementComposition() {
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        
        float result = bqAss.valeur(j1);
        
        assertEquals(130F, result);
    }
    
    
    @Test
    public void testVariance() {
        final ActionSimple as = new ActionSimple("BNP");
        final Jour j1 = new Jour(2023,23);
        final Jour j2 = new Jour(2023,24);
        final float value1 = 1.05F;
        final float value2 = 1.3F;
        
        as.enrgCours(j1, value1);
        as.enrgCours(j2, value2);
        
        final float result = as.variance(j1, j2);
        
        assertEquals(0.25F, result);
    }
    
}
