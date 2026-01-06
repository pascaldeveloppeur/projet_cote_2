package Liste;

import Interface.IFilePile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StructureListeTest {
    StructureListe<Integer> listePile;
    StructureListe<Integer> listeFile;
     ListeElement<Integer> listeElement;

    @BeforeEach
    void setUp() {
        listePile = new ListePile<>(2);
        listeFile = new ListeFile<>(2);
        listeElement = new ListeElement<>(4);
    }
    @AfterEach
    void tearDown() {
        if (listePile != null) listePile.clear();
        if (listeFile != null) listeFile.clear();
    }

    @Test
    @DisplayName("getNoeud sur liste initialiser à null")
    void getNoeudSurListeVide() {
        assertNotNull(listePile.getNoeud(), "Le noeud n'est pas null sur la liste vide");
    }

    @Test
    @DisplayName("getNoeud après ajout d'éléments")
    void getNoeudApresAjout() {
        listePile.push(10);
        listePile.push(20);


        assertNotNull(listePile.getNoeud(), "Le noeud ne doit pas être null");

    }

    @Test
    @DisplayName("setNoeud doit modifier le noeud de tête")
    void setNoeudModifieTete() {

            // Créer un nouveau noeud
            ListeElement<Integer> nouveauNoeud = new ListeElement<>(100);
            listePile.setNoeud(nouveauNoeud);

            assertEquals(nouveauNoeud, listePile.getNoeud(),
                    "Le noeud doit être celui qui a été défini");

    }



    @Test
    @DisplayName("Push sur liste Pile vide")
    void pushSurPileVide() {
        listePile.push(42);

        assertEquals(2, listePile.size(), "Doit contenir 2 élément");
        assertFalse(listePile.estVide(), "Ne doit pas être vide");
    }

    @Test
    @DisplayName("Push sur liste File vide")
    void pushSurFileVide() {
        listeFile.push(100);

        assertEquals(2, listeFile.size(), "Doit contenir 2 élément");
        assertFalse(listeFile.estVide(), "Ne doit pas être vide");
    }

    @Test
    @DisplayName("Push plusieurs éléments sur Pile")
    void pushPlusieursElementsPile() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);
        listePile.push(4);

        assertEquals(5, listePile.size(), "Doit contenir 5 éléments");
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("Push plusieurs éléments sur File")
    void pushPlusieursElementsFile() {
        listeFile.push(10);
        listeFile.push(20);
        listeFile.push(30);

        assertEquals(4, listeFile.size(), "Doit contenir 4 éléments");
    }

    @Test
    @DisplayName("Push de valeurs null")
    void pushValeurNull() {
        listePile.push(null);

        assertEquals(1, listePile.size(), "Doit accepter null");
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("Push de valeurs négatives")
    void pushValeursNegatives() {
        listePile.push(-5);
        listePile.push(-10);
        listePile.push(-15);

        assertEquals(4, listePile.size());
    }


    @Test
    @DisplayName("Push ne doit jamais rendre la liste pleine")
    void pushNePeutPasRemplir() {
        for (int i = 0; i < 1000; i++) {
            listePile.push(i);
        }

        assertFalse(listePile.estPleine(),
                "Une liste chaînée ne peut pas être pleine");
    }




    @Test
    @DisplayName("Push construit une chaîne correcte")
    void testPushConstruitChaine() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        ListeElement<Integer> courant = listePile.getNoeud();
        assertEquals(2, courant.getInfo());
        assertEquals(1, courant.getSuivant().getInfo());
        assertEquals(2, courant.getSuivant().getSuivant().getInfo());
        assertNull(courant.getSuivant().getSuivant().getSuivant().getSuivant());
    }



    @Test
    @DisplayName("Alternance push et pop")
    void alternancePushPop() {
        listePile.push(1);
        listePile.push(2);
        assertEquals(2, listePile.pop().get());

        listePile.push(3);
        listePile.push(4);
        assertEquals(4, listePile.pop().get());
        assertEquals(3, listePile.pop().get());
        assertEquals(1, listePile.pop().get());
        assertEquals(2, listePile.pop().get());

        assertTrue(listePile.estVide());
    }

    @Test
    @DisplayName("trouveLeReferent du dernier élément")
    void testTrouveReferentDuDernier() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        ListeElement<Integer> dernier = listePile.trouveDernier();
        ListeElement<Integer> referent = listePile.trouveLeReferent(dernier);

        assertNotNull(referent);
        assertEquals(2, referent.getInfo());
        assertSame(dernier, referent.getSuivant());
    }

    @Test
    @DisplayName("trouveLeReferent d'un élément au milieu")
    void testTrouveReferentDuMilieu() {
        listePile.push(1);
        listePile.push(2);

        ListeElement<Integer> milieu = listePile.getNoeud().getSuivant();
        ListeElement<Integer> referent = listePile.trouveLeReferent(milieu);

        assertEquals(2, referent.getInfo());
        assertSame(milieu, referent.getSuivant());
    }

    @Test
    @DisplayName("trouveLeReferent avec liste de 2 éléments")
    void testTrouveReferentDeuxElements() {
        listePile.push(10);
        listePile.push(20);

        ListeElement<Integer> dernier = listePile.trouveDernier();
        ListeElement<Integer> referent = listePile.trouveLeReferent(dernier);

        assertEquals(10, referent.getInfo());
    }

    @Test
    @DisplayName("trouveLeReferent parcourt correctement la liste")
    void testTrouveReferentParcourt() {
        for (int i = 1; i <= 5; i++) {
            listePile.push(i);
        }

        ListeElement<Integer> dernier = listePile.trouveDernier();
        ListeElement<Integer> referent = listePile.trouveLeReferent(dernier);

        assertEquals(4, referent.getInfo(), "Le référent du dernier (5) doit être 4");
    }


    @Test
    @DisplayName("trouveDernier sur liste vide")
    void trouveDernierSurListeNonVide() {

            assertNotNull(listePile.trouveDernier(),
                    "Le dernier doit être null sur liste vide");

    }

    @Test
    @DisplayName("trouveDernier avec un seul élément")
    void trouveDernierAvecUnElement() {
        listePile.push(42);


        ListeElement<Integer> dernier = listePile.trouveDernier();

        assertNotNull(dernier);
        assertEquals(42, dernier.getInfo());
        assertNull(dernier.getSuivant(),
                    "Le dernier noeud ne doit pas avoir de suivant");

    }

    @Test
    @DisplayName("trouveDernier avec plusieurs éléments")
    void trouveDernierAvecPlusieursElements() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);
        listePile.push(4);


        ListeElement<Integer> dernier = listePile.trouveDernier();

        assertNotNull(dernier);
        assertNull(dernier.getSuivant(),
                    "Le dernier noeud ne doit pas avoir de suivant");

    }

    @Test
    @DisplayName("trouveDernier retourne toujours le dernier noeud")
    void trouveDernierRetourneToujoursLeDernier() {
        for (int i = 1; i <= 5; i++) {
            listePile.push(i * 10);
        }


        ListeElement<Integer> dernier = listePile.trouveDernier();

        assertNotNull(dernier);
        assertNull(dernier.getSuivant(),
                    "Le dernier ne doit jamais avoir de suivant");

    }


    @Test
    @DisplayName("Size sur liste vide")
    void sizeSurListeVide() {
        assertEquals(1, listePile.size());
        assertEquals(1, listeFile.size());
    }

    @Test
    @DisplayName("Size après ajouts")
    void sizeApresAjouts() {
        listePile.push(1);
        assertEquals(2, listePile.size());

        listePile.push(2);
        assertEquals(3, listePile.size());

        listePile.push(3);
        assertEquals(4, listePile.size());
    }

    @Test
    @DisplayName("Size après ajouts et suppressions")
    void sizeApresAjoutsEtSuppressions() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);
        assertEquals(4, listePile.size());

        listePile.pop();
        assertEquals(3, listePile.size());

        listePile.pop();
        assertEquals(2, listePile.size());

        listePile.pop();
        assertEquals(1, listePile.size());
        listePile.pop();
        assertEquals(0, listePile.size());
    }

    @Test
    @DisplayName("Size après clear")
    void sizeApresClear() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        listePile.clear();
        assertEquals(0, listePile.size());
    }



    @Test
    @DisplayName("Size avec type String")
    void sizeAvecString() {
        IFilePile<String> listeString = new ListePile<>("p");
        listeString.push("A");
        listeString.push("B");
        listeString.push("C");

        assertEquals(4, listeString.size());
    }


    @Test
    @DisplayName("la methode doit toujours retourner false pour une liste")
    void estPleineToujoursFalse() {
        assertFalse(listePile.estPleine(), "Une liste vide ne peut pas être pleine");

        listePile.push(1);
        assertFalse(listePile.estPleine(), "Une liste avec un élément ne peut pas être pleine");
    }

    @Test
    @DisplayName("la methode même avec beaucoup d'éléments")
    void estPleineAvecBeaucoupElements() {
        for (int i = 0; i < 1000; i++) {
            listePile.push(i);
        }

        assertFalse(listePile.estPleine(),
                "Une liste chaînée ne peut jamais être pleine");
    }

    @Test
    @DisplayName("la methode sur File")
    void estPleineSurFile() {
        assertFalse(listeFile.estPleine());

        for (int i = 0; i < 50; i++) {
            listeFile.push(i);
        }

        assertFalse(listeFile.estPleine(),
                "Une liste file ne peut jamais être pleine");
    }


    @Test
    @DisplayName("Vide sur liste vide")
    void estVideSurListeVide() {
        assertFalse(listePile.estVide());
        assertFalse(listeFile.estVide());
    }

    @Test
    @DisplayName("estVide après push")
    void estVideApresPush() {
        listePile.push(10);
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("estVide après pop complet")
    void estVideApresPopComplet() {
        listePile.push(1);
        listePile.push(2);

        listePile.pop();
        assertFalse(listePile.estVide());

        listePile.pop();
        assertFalse(listePile.estVide());

        listePile.pop();
        assertTrue(listePile.estVide());
    }

    @Test
    @DisplayName("estVide après clear")
    void estVideApresClear() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        assertFalse(listePile.estVide());

        listePile.clear();
        assertTrue(listePile.estVide());
    }

    @Test
    @DisplayName("estVide alternance")
    void estVideAlternance() {
        assertFalse(listePile.estVide());

        listePile.push(1);
        assertFalse(listePile.estVide());

        listePile.pop();
        assertFalse(listePile.estVide());

        listePile.push(2);
        assertFalse(listePile.estVide());
    }


    @Test
    @DisplayName("Clear sur liste vide")
    void clearSurListeVide() {
        listePile.clear();

        assertEquals(0, listePile.size());
        assertTrue(listePile.estVide());
    }


    @Test
    @DisplayName("Clear remet info à null")
    void testClearRemetInfoNull() {
        listePile.push(10);
        listePile.push(20);

        listePile.clear();

        assertNull(listePile.getNoeud().getInfo());
    }

    @Test
    @DisplayName("Clear remet suivant à null")
    void testClearRemetSuivantNull() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        assertNotNull(listePile.getNoeud().getSuivant());

        listePile.clear();

        assertNull(listePile.getNoeud().getSuivant());
    }

    @Test
    @DisplayName("Clear rend la liste vide")
    void testClearRendVide() {
        listePile.push(1);
        listePile.push(2);

        listePile.clear();

        assertTrue(listePile.estVide());
        assertEquals(0, listePile.size());
    }

    @Test
    @DisplayName("Clear sur liste déjà vide")
    void testClearSurListeVide() {
        listePile.clear();

        assertTrue(listePile.estVide());
        assertNull(listePile.getNoeud().getInfo());
        assertNull(listePile.getNoeud().getSuivant());
    }

    @Test
    @DisplayName("Push après clear fonctionne")
    void testPushApresClear() {
        listePile.push(1);
        listePile.push(2);
        listePile.clear();

        listePile.push(99);

        assertEquals(1, listePile.size());
        assertEquals(99, listePile.getNoeud().getInfo());
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("Clear répétés")
    void testClearRepetes() {
        listePile.push(1);

        for (int i = 0; i < 5; i++) {
            listePile.clear();
            assertTrue(listePile.estVide());
        }
    }

    @Test
    @DisplayName("Clear sur File")
    void clearSurFile() {
        listeFile.push(10);
        listeFile.push(20);
        listeFile.push(30);

        listeFile.clear();

        assertTrue(listeFile.estVide());
        assertEquals(0, listeFile.size());
    }

    @Test
    @DisplayName("Push après clear")
    void pushApresClear() {
        listePile.push(1);
        listePile.push(2);
        listePile.clear();

        listePile.push(99);

        assertEquals(1, listePile.size());
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("Clear répétés")
    void clearRepetes() {
        listePile.push(1);

        for (int i = 0; i < 5; i++) {
            listePile.clear();
            assertTrue(listePile.estVide());
            assertEquals(0, listePile.size());
        }
    }

    @Test
    @DisplayName("Clear puis pop doit retourner empty")
    void clearPuisPopRetourneEmpty() {
        listePile.push(1);
        listePile.push(2);
        listePile.clear();

        Optional<Integer> result = listePile.pop();
        assertTrue(result.isEmpty());
    }


    @Test
    @DisplayName("Scénario complet Pile : push, pop, clear")
    void scenarioCompletPile() {
        // Phase 1 : Ajouts
        listePile.push(10);
        listePile.push(20);
        listePile.push(30);
        assertEquals(4, listePile.size());

        // Phase 2 : Pop LIFO
        assertEquals(30, listePile.pop().get());
        assertEquals(20, listePile.pop().get());
        assertEquals(2, listePile.size());

        // Phase 3 : Ajouts supplémentaires
        listePile.push(40);
        listePile.push(50);
        assertEquals(4, listePile.size());

        // Phase 4 : Clear
        listePile.clear();
        assertTrue(listePile.estVide());
    }



    @Test
    @DisplayName("Comparaison Pile vs File")
    void comparaisonPileFile() {
        // Ajout identique
        for (int i = 1; i <= 3; i++) {
            listePile.push(i);
            listeFile.push(i);
        }

        // Pop différent
        int popPile = listePile.pop().get();
        int popFile = listeFile.pop().get();

        assertEquals(3, popPile, "Pile : dernier entré (LIFO)");
        assertEquals(2, popFile, "File : premier entré (FIFO)");
    }

    @Test
    @DisplayName("Test de stress : 1000 opérations")
    void testStress() {
        for (int i = 0; i < 500; i++) {
            listePile.push(i);
        }

        assertEquals(501, listePile.size());

        for (int i = 0; i < 250; i++) {
            listePile.pop();
        }

        assertEquals(251, listePile.size());
        assertFalse(listePile.estVide());
    }

    @Test
    @DisplayName("Opérations mixtes complexes")
    void operationsMixtesComplexes() {
        listePile.push(1);
        listePile.push(2);
        assertEquals(2, listePile.pop().get());

        listePile.push(3);
        listePile.push(4);
        listePile.push(5);

        assertEquals(5, listePile.pop().get());
        assertEquals(4, listePile.pop().get());

        listePile.clear();
        assertTrue(listePile.estVide());

        listePile.push(100);
        assertEquals(1, listePile.size());
    }


    @Test
    @DisplayName("Grande quantité d'éléments")
    void grandeQuantite() {
        for (int i = 0; i < 10000; i++) {
            listePile.push(i);
        }

        assertEquals(10001, listePile.size());
        assertFalse(listePile.estPleine());
    }

    @Test
    @DisplayName("Pop répétés sur liste vide")
    void popRepetesSurVide() {
        Optional<Integer> result = listePile.pop();
        for (int i = 0; i < 10; i++) {
            Optional<Integer> result1 = listePile.pop();
            assertTrue(result1.isEmpty());
        }

        assertEquals(0, listePile.size());
        assertTrue(listePile.estVide());
    }

    @Test
    @DisplayName("Alternance rapide push/pop")
    void alternanceRapide() {
        for (int i = 0; i < 100; i++) {
            listePile.push(i);
            listePile.pop();
        }

        assertFalse(listePile.estVide());
        assertEquals(1, listePile.size());
    }

    @Test
    @DisplayName("Utilisation avec différents types")
    void utilisationAvecDifferentsTypes() {
        IFilePile<String> listeString = new ListePile<>("salut");
        listeString.push("Hello");
        listeString.push("World");
        assertEquals(3, listeString.size());
        assertEquals("World", listeString.pop().get());

        IFilePile<Double> listeDouble = new ListePile<>(2.23);
        listeDouble.push(3.14);
        listeDouble.push(2.71);
        assertEquals(3, listeDouble.size());
    }

}