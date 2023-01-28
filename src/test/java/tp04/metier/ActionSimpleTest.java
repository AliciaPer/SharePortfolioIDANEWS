package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alicia Perez
 */public class ActionSimpleTest {
    
    public ActionSimpleTest() {
    }

    @Test
    //tester si l'enregistrement d'un cours se fait correctement
    public void testEnregistrementCours() {
        // Déclarations et initialisations
        final ActionSimple as = new ActionSimple("Toto");
        final Jour j1 = new Jour(0,0);
        
        // Le résultat attendu
        final float value = 1.37F;
        
        // Enregistrer value dans le cours puis la récupérer dans result
        as.enrgCours(j1, value);
        final float result = as.valeur(j1);
        
        // Test
        assertEquals(value, result);
    }
    
    // Tester la variance d'une action simple
    @Test
    public void testVariance() {
        // Déclarations et initialisations
        final ActionSimple as = new ActionSimple("BNP");
        final Jour j1 = new Jour(2023,23);
        final Jour j2 = new Jour(2023,24);
        
        // Enregistrement des cours
        as.enrgCours(j1, 1.05F);
        as.enrgCours(j2, 1.3F);
        
        // Calculer la variance de l'action
        final float result = as.variance(j1, j2);
        
        // Test
        assertEquals(0.25F, result);
    }
    
}





