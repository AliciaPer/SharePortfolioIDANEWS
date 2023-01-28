package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alicia Perez
 */
public class JourTest {
    
    public JourTest() {
    }
    // Tests prédéfinis des getters
    @Test
    public void testGetAnnee() {
        Jour j1 = new Jour(2023, 1);
        int expResult = 2023;
        int result = j1.getAnnee();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetNoJour() {
        Jour j1 = new Jour(2023, 1);
        int expResult = 1;
        int result = j1.getNoJour();
        assertEquals(expResult, result);
    }
    
    // Test prédéfini de la méthode equals
    @Test
    public void testEquals() {
        Jour j1 = new Jour(2023, 1);
        Jour j2 = new Jour(2023, 31);
        
        boolean expResult = false;
        boolean result = j1.equals(j2);
        assertEquals(expResult, result);
    }

    // Tester la fonction between 
    @Test
    public void testBetween() {
        System.out.println("between");
        Jour j1 = new Jour(2023, 1);
        Jour j2 = new Jour(2023, 31);
        Jour j3 = new Jour(2023, 25);
        
        boolean result = j3.between(j1, j2);
        // Le résultat attendu est true car j3 est entre j1 et j2
        boolean expResult = true;
        
        
        assertEquals(expResult, result);
    }
}
