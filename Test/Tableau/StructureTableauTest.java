package Tableau;

import Interface.IFilePile;
import Liste.ListePile;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StructureTableauTest {
    private StructureTableau<Integer> tableauPile;
    private StructureTableau<Integer> tableauFile;

    @BeforeEach
    void setUp() {
        tableauPile = new TableauPile<>();
        tableauFile = new TableauFile<>();
    }

    @AfterEach
     void afterEach() {
        tableauPile = null;
        tableauFile = null;

    }

    @Test
    void testSurLesPileGetter(){
        assertNotNull(tableauPile.getTableau());
        assertEquals(4,tableauPile.getTailleDepart());
        assertEquals(0,tableauPile.getNbElement());
        assertEquals(0,tableauPile.getPointeurPop());
        assertEquals(0,tableauPile.getPointeurPush());
    }

    @Test
    void testSurLesFileGetter(){
        assertNotNull(tableauFile.getTableau());
        assertEquals(4,tableauFile.getTailleDepart());
        assertEquals(0,tableauFile.getNbElement());
        assertEquals(0,tableauFile.getPointeurPop());
        assertEquals(0,tableauFile.getPointeurPush());

    }
    @Test
    @DisplayName("Getters après ajout d'éléments sur la pile")
    void testGettersApresAjoutsPile(){
        tableauPile.push(5);
        tableauPile.push(6);
        assertEquals(2,tableauPile.getNbElement(),"Doit avoir 2 éléments");
        assertEquals(2, tableauPile.getPointeurPush(), "Pointeur push doit être egale à 2");
    }

    @Test
    @DisplayName("Getters après ajout d'éléments dur la File")
    void testGettersApresAjoutsFile(){
        tableauFile.push(5);
        tableauFile.push(6);
        tableauFile.push(7);
        assertEquals(3,tableauFile.getNbElement(),"Doit avoir 3 éléments");
        assertEquals(3,tableauFile.getPointeurPush(), "Pointeur push doit avoir 3");
    }

    @Test
    @DisplayName("Push un élément sur la Pile vide")
    void pushSurPileVide() {
        tableauPile.push(5);
        assertEquals(1,tableauPile.size(),"La taille doit être 1");
        assertEquals(5,tableauPile.getTableau()[0],"le premier élément doit être 5");
        assertFalse(tableauPile.estVide(),"La pile ne doit pas être vide");
    }

    @Test
    @DisplayName("Push un élément sur la file vide")
    void pushSurFileVide() {
        tableauFile.push(5);
        tableauFile.push(6);
        assertEquals(2,tableauFile.size(),"La taille doit avoir 2");
        assertEquals(5,tableauFile.getTableau()[0],"Le premier élément doit avoir 5");
        assertFalse(tableauFile.estVide(),"La file ne doit pas être vide");
    }

    @Test
    @DisplayName("Push plusieurs éléments sur la Pile")
    void pushPlusieursPile() {
        tableauPile.push(5);
        tableauPile.push(6);
        tableauPile.push(7);

        assertEquals(3,tableauPile.size(),"La taille doit avoir 3");
        assertEquals(0,tableauFile.getTableau()[0]);
        assertEquals(5,tableauFile.getTableau()[1]);
        assertEquals(6,tableauFile.getTableau()[2]);
    }

    @Test
    @DisplayName("Push plusieurs éléments sur la file")
    void pushPlusieursFile() {
        tableauFile.push(5);
        tableauFile.push(6);
        tableauFile.push(7);
        tableauFile.push(8);

        assertEquals(4,tableauFile.size(),"La taille doit avoir 4");
        assertEquals(5,tableauFile.getTableau()[0]);
        assertEquals(6,tableauFile.getTableau()[1]);
        assertEquals(7,tableauFile.getTableau()[2]);
        assertTrue(tableauFile.estPleine(),"La file pleine");
    }

    @Test
    @DisplayName("Remplissage de la pile")
    void remplissagePile() {
        for(int i = 1;i<=4;i++) {
            tableauPile.push(i);
        }

        assertEquals(4,tableauPile.size(),"La taille doit avoir 4");
        assertTrue(tableauPile.estPleine(),"La Pile doit être pleine");
    }

    @Test
    @DisplayName("Agrandissement automatique de la pile")
    void grandissementAutomatiquePile() {
        for(int i = 1;i<=5;i++) {
            tableauPile.push(i);
        }
        assertTrue(tableauPile.estPleine(),"la pile est pleine avant l'agrandissement");

        tableauPile.push(25);
        assertEquals(5,tableauPile.size(),"La taille doit avoir 5");
        assertEquals(8,tableauPile.getTableau().length,"La taille doit avoir 8");
        assertFalse(tableauPile.estPleine(),"La Pile n'est pas pleine");
    }
    @Test
    @DisplayName("Agrandissement automatique sur File")
    void agrandissementAutomatiqueFile() {
        for (int i = 1; i <= 5; i++) {
            tableauFile.push(i * 100);
        }

        assertEquals(5, tableauFile.size());
        assertTrue(tableauFile.getTableau().length >= 5, "Tableau doit être agrandi");
        assertFalse(tableauFile.estPleine());
    }

    @Test
    @DisplayName("Push de null")
    void pushValeurNull() {
        tableauPile.push(null);

        assertEquals(1, tableauPile.size());
        assertNull(tableauPile.getTableau()[0], "Doit accepter null");
    }


    @Test
    @DisplayName("Clear sur une pile vide")
    void clearSurPileVide() {
        tableauPile.clear();

        assertEquals(0, tableauPile.size());
        assertEquals(0, tableauPile.getPointeurPush());
        assertEquals(0, tableauPile.getPointeurPop());
        assertTrue(tableauPile.estVide());
    }

    @Test
    @DisplayName("Clear sur une file vide")
    void clearSurFileVide() {
        tableauFile.clear();

        assertEquals(0, tableauFile.size());
        assertTrue(tableauFile.estVide());
    }

    @Test
    @DisplayName("Clear sur une pile avec des éléments")
    void clearPileAvecElements() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.push(3);

        assertEquals(3, tableauPile.size());

        tableauPile.clear();

        assertEquals(0, tableauPile.size(), "Taille doit être 0");
        assertTrue(tableauPile.estVide(), "Doit être vide");
        assertEquals(4, tableauPile.getTableau().length, "Tableau réinitialisé");
        assertEquals(0, tableauPile.getPointeurPush());
        assertEquals(0, tableauPile.getPointeurPop());
    }

    @Test
    @DisplayName("Clear sur une file avec des éléments")
    void clearFileAvecElements() {
        tableauFile.push(10);
        tableauFile.push(20);
        tableauFile.push(30);
        tableauFile.push(40);

        tableauFile.clear();

        assertEquals(0, tableauFile.size());
        assertTrue(tableauFile.estVide());
        assertEquals(4, tableauFile.getTailleDepart());
    }

    @Test
    @DisplayName("Clear après agrandissement et suivi du retour à la taille initiale")
    void clearApresAgrandissement() {
        for (int i = 0; i < 5; i++) {
            tableauPile.push(i);
        }

        assertTrue(tableauPile.getTableau().length > 4);

        tableauPile.clear();

        assertEquals(4, tableauPile.getTableau().length,
                "Doit revenir à la taille initiale");
    }

    @Test
    @DisplayName("Push après clear")
    void pushApresClear() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.clear();

        tableauPile.push(99);

        assertEquals(1, tableauPile.size());
        assertEquals(99, tableauPile.getTableau()[0]);
    }



    @Test
    @DisplayName("Size sur Pune pile vide")
    void sizeSurPileVide() {
        assertEquals(0, tableauPile.size());
    }

    @Test
    @DisplayName("Size sur un file vide")
    void sizeSurFileVide() {
        assertEquals(0, tableauFile.size());
    }

    @Test
    @DisplayName("Size après ajouts")
    void sizeApresAjouts() {
        tableauPile.push(1);
        assertEquals(1, tableauPile.size());

        tableauPile.push(2);
        assertEquals(2, tableauPile.size());

        tableauPile.push(3);
        assertEquals(3, tableauPile.size());
    }

    @Test
    @DisplayName("Size après ajouts et suppressions")
    void sizeApresAjoutsEtSuppressions() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.push(3);
        assertEquals(3, tableauPile.size());

        tableauPile.pop();
        assertEquals(2, tableauPile.size());

        tableauPile.pop();
        assertEquals(1, tableauPile.size());
    }

    @Test
    @DisplayName("Size après clear")
    void sizeApresClear() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.clear();

        assertEquals(0, tableauPile.size());
    }


    @Test
    @DisplayName("estVide doit retourner true sur Pile vide")
    void estVideSurPileVide() {
        assertTrue(tableauPile.estVide());
    }

    @Test
    @DisplayName("estVide doit retourner true sur File vide")
    void estVideSurFileVide() {
        assertTrue(tableauFile.estVide());
    }

    @Test
    @DisplayName("estVide doit retourner false après push")
    void estVideApresPush() {
        tableauPile.push(10);
        assertFalse(tableauPile.estVide());
    }

    @Test
    @DisplayName("estVide après pop complet")
    void estVideApresPopComplet() {
        tableauPile.push(1);
        tableauPile.push(2);

        tableauPile.pop();
        assertFalse(tableauPile.estVide(), "Ne doit pas être vide");

        tableauPile.pop();
        assertTrue(tableauPile.estVide(), "Doit être vide");
    }

    @Test
    @DisplayName("estVide après clear")
    void estVideApresClear() {
        tableauPile.push(1);
        tableauPile.push(2);

        assertFalse(tableauPile.estVide());

        tableauPile.clear();
        assertTrue(tableauPile.estVide());
    }




    @Test
    @DisplayName("estPleine doit retourner false sur structure vide")
    void estPleineSurStructureVide() {
        assertFalse(tableauPile.estPleine());
        assertFalse(tableauFile.estPleine());
    }

    @Test
    @DisplayName("estPleine doit retourner true quand remplie")
    void estPleineQuandRemplie() {
        for (int i = 0; i < 4; i++) {
            tableauPile.push(i);
        }

        assertTrue(tableauPile.estPleine(), "Doit être pleine");
    }

    @Test
    @DisplayName("estPleine après agrandissement")
    void estPleineApresAgrandissement() {
        for (int i = 0; i < 5; i++) {
            tableauPile.push(i);
        }

        assertFalse(tableauPile.estPleine(),
                "Ne doit pas être pleine après agrandissement");
    }

    @Test
    @DisplayName("estPleine après pop")
    void estPleineApresPop() {
        for (int i = 0; i < 4; i++) {
            tableauPile.push(i);
        }
        assertTrue(tableauPile.estPleine());

        tableauPile.pop();
        assertFalse(tableauPile.estPleine(), "Ne doit plus être pleine");
    }



    @Test
    @DisplayName("toString sur Pile vide")
    void testToStringSurPileVide() {
        assertEquals("[]", tableauPile.toString());
    }

    @Test
    @DisplayName("toString sur File vide")
    void testToStringSurFileVide() {
        assertEquals("[]", tableauFile.toString());
    }

    @Test
    @DisplayName("toString avec un élément")
    void testToStringAvecUnElement() {
        tableauPile.push(42);
        assertEquals("[42]", tableauPile.toString());
    }

    @Test
    @DisplayName("toString avec plusieurs éléments sur Pile")
    void testToStringPlusieursElementsPile() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.push(3);

        assertEquals("[1, 2, 3]", tableauPile.toString());
    }

    @Test
    @DisplayName("toString avec plusieurs éléments sur File")
    void testToStringPlusieursElementsFile() {
        tableauFile.push(10);
        tableauFile.push(20);
        tableauFile.push(30);

        assertEquals("[10, 20, 30]", tableauFile.toString());
    }

    @Test
    @DisplayName("toString après clear")
    void testToStringApresClear() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.clear();

        assertEquals("[]", tableauPile.toString());
    }

    @Test
    @DisplayName("toString après pop")
    void testToStringApresPop() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.push(3);

        tableauPile.pop();

        assertEquals("[1, 2]", tableauPile.toString());
    }@Test
    void estVide() {
    }



    @Test
    void testToString() {
    }
}