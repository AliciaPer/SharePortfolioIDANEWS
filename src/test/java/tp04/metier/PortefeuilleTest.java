package tp04.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alicia Perez
 */
public class PortefeuilleTest {
    // Constructeur vide
    public PortefeuilleTest() {
    }

    // Tester le calcul de la valeur d'un portefeuille
    @Test
    public void testPortefeuilleValeur() {

       final Portefeuille p = new Portefeuille();
       // Déclaration des variables
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
        
        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        p.addUser(sidali);
        
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
       
        // Effectuer des opérations d'achat
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, sidali);
        p.acheter(bqAss, 10, j2, sidali);

        float somme_total = p.valeur(j1);

        // Test
        assertEquals(4300.0F, somme_total);
        
    }
    
    // Tester le filtre des actions
    @Test
    public void testFiltre() {
        // Déclaration des variables
        final Portefeuille p = new Portefeuille();
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);
        
        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        p.addUser(sidali);

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
       
        // Effectuer des achats
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, sidali);
        p.acheter(bqAss, 10, j2, sidali);

        // Récupérer les actions dont la valeurs a augmenté
        ArrayList<Action> actions_up = p.filtre(true, j1, j2);
        // Le résultat attendu est une liste avec les actions bnp et bqAss
        ArrayList<Action> expected_actions_up = new ArrayList<>();
        expected_actions_up.add(bnp);
        expected_actions_up.add(bqAss);
        
        // Test
        assertEquals(expected_actions_up, actions_up); 
    }
    
    // Tester l'ajout et la suppression des users
    @Test
    public void testAddRemove() { 
        // Déclaration des variables
        final Portefeuille p = new Portefeuille();
        
        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        // ajouter sidali et yurui comme utilisateur
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(yurui);
        // Retirer sidali et ajouter alicia
        p.removeUser(sidali);
        p.addUser(alicia);
        p.removeUser(sidali);
        
        ArrayList<User> result = p.getUsers();
        
        // Le résultat attendu est la liste des utilisateurs yurui et alicia
        ArrayList<User> expected_result = new ArrayList<>();
        expected_result.add(yurui);
        expected_result.add(alicia);
        // Test
        assertEquals(expected_result, result);
    }
    
    // Tester le calcul des dépenses
    @Test
    public void testDepenses() {
        // Déclarations
        final Portefeuille p = new Portefeuille();
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);
        
        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        User emir = new User(4, "Emir");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);

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
       
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
        // emir n'est pas dans la liste des utilisateurs du portefeuille
        p.acheter(bqAss, 10, j2, emir);
        
        
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        // emir n'est pas dans la liste des utilisateurs du portefeuille
        p.vendre(axa, 2, j2, emir);

        float result = p.depenses(j1, j2);
        // Le résultat attendu est 6500e de dépenses
        float expected_result = 6500F;
        // Test
        assertEquals(expected_result, result);
        
    }
    
    // Tester le calcul des recettes
    @Test
    public void testRecettes() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        User emir = new User(4, "Emir");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
        // emir n'est pas dans la liste des utilisateurs du portefeuille
        p.acheter(bqAss, 10, j2, emir);
        
        
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        // emir n'est pas dans la liste des utilisateurs du portefeuille
        p.vendre(axa, 2, j2, emir);

        float result = p.recettes(j1, j2);
        // Le résultat attendu est 3000e de recettes
        float expected_result = 3000F;
        // Test
        assertEquals(expected_result, result);
        
    }
    
    // TEster l'historique d'achat
    @Test
    public void testHistoriqueAchat() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
   
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        
        ArrayList<Achat> result = p.historique_achat(j1, j2);
        
        ArrayList<Achat> expected_result = new ArrayList<>();
        // Le résultat attendu est la liste des 3 achats qu'on a effectué
        expected_result.add(new Achat(sidali, axa, j1, 10, axa.valeur(j1)));
        expected_result.add(new Achat(alicia, bnp, j2, 10, bnp.valeur(j2)));
        expected_result.add(new Achat(yurui, bqAss, j2, 10, bqAss.valeur(j2)));
        // Test
        assertEquals(expected_result, result);

        
    }
    
    // Tester l'historique de ventes
    @Test
    public void testHistoriqueVente() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
   
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        
        ArrayList<Vente> result = p.historique_vente(j1, j2);
        
        ArrayList<Vente> expected_result = new ArrayList<>();
        // Le résultat attendu est la liste des 3 achats qu'on a effectué
        expected_result.add(new Vente(sidali, axa, j2, 10, axa.valeur(j2)));
        expected_result.add(new Vente(yurui, bqAss, j2, 5, bqAss.valeur(j2)));
        // Test
        assertEquals(expected_result, result);
    
    }
    
    // Tester l'historique des achats par utilisateur
    @Test
    public void testHistoriqueAchatUser() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
   
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        
        ArrayList<Achat> result = p.historique_achat_user(sidali, j1, j2);
        
        // Le résultat attendu est une liste avec l'achat que sidali a effectué
        ArrayList<Achat> expected_result = new ArrayList<>();
        expected_result.add(new Achat(sidali, axa, j1, 10, axa.valeur(j1)));
        // Test
        assertEquals(expected_result, result);   
    }
    
    // Tester l'historique des ventes effectuées par un user
    @Test
    public void testHistoriqueVenteUser() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
   
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        
        ArrayList<Vente> result = p.historique_vente_user(sidali, j1, j2);
        // Le résultat attendu est la liste avec la vente que sidali a effectué
        ArrayList<Vente> expected_result = new ArrayList<>();
        expected_result.add(new Vente(sidali, axa, j2, 10, axa.valeur(j2)));
        // Test
        assertEquals(expected_result, result);
    }
    
    // Tester le calcul de profit entre deux jours
    @Test
    public void testProfit() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        
        p.addUser(sidali);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, sidali);
        p.acheter(bqAss, 10, j2, sidali);
   
        p.vendre(axa, 10, j2, sidali);
        p.vendre(bnp, 40, j2, sidali);
        p.vendre(bqAss, 5, j2, sidali);
        
        float result = p.profit(j1, j2);
        float expected_result = -3500F;
        // Test
        assertEquals(expected_result, result);
    }
    
    // Tester le classement des actions selon leurs profits apportés
    @Test
    public void testActionsRanking() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali"); 
        p.addUser(sidali);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, sidali);
        p.acheter(bqAss, 10, j2, sidali);
   
        p.vendre(axa, 5, j2, sidali);
        p.vendre(bnp, 40, j2, sidali);
        p.vendre(bqAss, 5, j2, sidali);
        
        SortedMap<Float, Action> result = p.actions_ranking(j1, j2);
        
        // Le résultat attendu est la liste de nos 3 actions triée 
        SortedMap<Float, Action> expected_result = new TreeMap<>(Collections.reverseOrder());
        expected_result.put(axa.variance(j1, j2), axa);
        expected_result.put(bnp.variance(j1, j2), bnp);
        expected_result.put(bqAss.variance(j1, j2), bqAss);

        // Afficher le classement obtenu
        System.out.println("\n** Classement des actions **");
        for(float a : result.keySet()) {
            System.out.println(result.get(a) + " : " + Float.toString(a) + " euros");
        }
        
        // Test
        assertEquals(expected_result, result);
    }
    
    
    // Tester le classement des utilisateurs selon leurs scores entre deux jours
    @Test
    public void testUsersRanking() {

        // Initialisation du portefeuille
        final Portefeuille p = new Portefeuille();

        // Initialisation des utilisateurs
        User sidali = new User(1, "Sidali");
        User yurui = new User(2, "Yu Rui");
        User alicia = new User(3, "Alicia");
        
        p.addUser(sidali);
        p.addUser(yurui);
        p.addUser(alicia);
        
        // Initialisation des jours
        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 5);

        // Initialisation des actions simples et composées
        ActionSimple bnp, axa;
        ActionComposee bqAss;
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
       
        // Effectuer des opérations
        p.acheter(axa, 10, j1, sidali);
        p.acheter(bnp, 10, j2, alicia);
        p.acheter(bqAss, 10, j2, yurui);
   
        p.vendre(axa, 5, j2, sidali);
        p.vendre(bnp, 40, j2, alicia);
        p.vendre(bqAss, 5, j2, yurui);
        
        SortedMap<Float, User> result = p.users_ranking(j1, j2);
        
        // Le résultat attendu est la liste de nos 3 actions triée 
        SortedMap<Float, User> expected_result = new TreeMap<>(Collections.reverseOrder());
        expected_result.put(-2000F, sidali);
        expected_result.put(-3450F, alicia);
        expected_result.put(-2450F, yurui);
        
        // Afficher le classement obtenu
        System.out.println("\n** Classement des utilisateurs **");
        for(float u : result.keySet()) {
            System.out.println(result.get(u).getName() + " : " + Float.toString(u));
        }
        
        // Test
        assertEquals(expected_result, result);
    }
    
}
