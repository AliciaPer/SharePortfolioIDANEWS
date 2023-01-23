/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Alicia Perez
 */
public class ActionTest {
    
    public ActionTest() {
    }

    @Test
    public void testSomeMethod() {
        final Action action = new Action("Toto") {
            @Override
          public float valeur(Jour aJ) {
                return -1.0F;
            }
        };
        
        final String lib = action.getLibelle();
        Assertions.assertSame("Toto", lib);
    }
    
}