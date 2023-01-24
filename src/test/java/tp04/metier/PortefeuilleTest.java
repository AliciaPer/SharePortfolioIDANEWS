package tp04.metier;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alicia Perez
 */
public class PortefeuilleTest {
    
    public PortefeuilleTest() {
    }

    @Test
    public void testPortefeuilleValeur() {

       final Portefeuille p = new Portefeuille();
       
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
       
        p.acheter(axa, 10, j1);
        p.acheter(bnp, 10, j2);
        p.acheter(bqAss, 10, j2);

        float somme_total = p.valeur(j1);

        assertEquals(4300.0F, somme_total);
        
    }
    
    
    @Test
    public void testFiltre() {

       final Portefeuille p = new Portefeuille();
       
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
        axa.enrgCours(j1, 250);
        axa.enrgCours(j2, 200);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
       
        p.acheter(axa, 10, j1);
        p.acheter(bnp, 10, j2);
        p.acheter(bqAss, 10, j2);

        ArrayList<Action> actions_up = p.filtre(true, j1, j2);
        
        ArrayList<Action> expected_actions_up = new ArrayList<>();
        expected_actions_up.add(bnp);
        expected_actions_up.add(bqAss);
                
        assertEquals(expected_actions_up, actions_up);
        
    }
    
    
    @Test
    public void testDepenses() {

       final Portefeuille p = new Portefeuille();
       
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 250);
        axa.enrgCours(j2, 200);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
       
        p.acheter(axa, 10, j1);
        p.acheter(bnp, 10, j2);
        p.acheter(bqAss, 10, j2);
        
        p.vendre(axa, 10, j2);
        p.vendre(bnp, 40, j2);
        p.vendre(bqAss, 5, j2);

        float result = p.depenses(j1, j2);
        float expected_result = 6500F;
                
        assertEquals(expected_result, result);
        
    }
    
    
    @Test
    public void testRecettes() {

       final Portefeuille p = new Portefeuille();
       
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 250);
        axa.enrgCours(j2, 200);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
       
        p.acheter(axa, 10, j1);
        p.acheter(bnp, 10, j2);
        p.acheter(bqAss, 10, j2);
        
        p.vendre(axa, 10, j2);
        p.vendre(bnp, 40, j2);
        p.vendre(bqAss, 5, j2);

        float result = p.recettes(j1, j2);
        float expected_result = 3000F;
                
        assertEquals(expected_result, result);
        
    }
    
}
