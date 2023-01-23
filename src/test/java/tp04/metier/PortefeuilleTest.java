/*
 * Copyright 2023 Alicia Perez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

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
       // fail("The test case is a prototype.");
       //verifier si l(action existe
       final Portefeuille p = new Portefeuille();
       final Jour j1 = new Jour(1,1);
       final float value = 0.1F;
       
       ActionSimple a1 = new ActionSimple("action1");
       a1.enrgCours(j1, value);
       
       ActionSimple a2 = new ActionSimple("action2");
       a2.enrgCours(j1, value);
       
       ActionSimple a3 = new ActionSimple("action3");
       a3.enrgCours(j1, value);
       
       p.acheter(a1, 10);
       p.acheter(a2, 10);
       p.acheter(a3, 10);
       
       float somme_total = p.valeur(j1);
       
       assertEquals(3.0F, somme_total);
        
    }
    
}
