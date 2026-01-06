package Liste;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListeElementTest {

    private ListeElement<Integer> element;

    @BeforeEach
    void setUp() {
        element = new ListeElement<>();
    }



        @Test
        @DisplayName("Constructeur par défaut, il doit créer un élément vide")
        void testConstructeurParDefaut() {
            //ListeElement<Integer> element = new ListeElement<>();

            assertNotNull(element, "L'élément ne doit pas être null");
            assertNull(element.getInfo(), "L'info doit être null");
            assertNull(element.getSuivant(), "Le suivant doit être null");
        }

        @Test
        @DisplayName("Constructeur avec valeur Integer")
        void testConstructeurAvecValeurInteger() {
            ListeElement<Integer> elem = new ListeElement<>(42);

            assertNotNull(elem);
            assertEquals(42, elem.getInfo(), "L'info doit être 42");
            assertNull(elem.getSuivant(), "Le suivant doit être null");
        }

        @Test
        @DisplayName("Constructeur avec valeur String")
        void testConstructeurAvecValeurString() {
            ListeElement<String> elem = new ListeElement<>("Hello");

            assertNotNull(elem);
            assertEquals("Hello", elem.getInfo());
            assertNull(elem.getSuivant());
        }

        @Test
        @DisplayName("Constructeur avec valeur null")
        void testConstructeurAvecNull() {
            ListeElement<Integer> elem = new ListeElement<>(null);

            assertNotNull(elem, "L'élément ne doit pas être null");
            assertNull(elem.getInfo(), "L'info doit être null");
            assertNull(elem.getSuivant(), "Le suivant doit être null");
        }

        @Test
        @DisplayName("Constructeur avec valeur négative")
        void testConstructeurAvecValeurNegative() {
            ListeElement<Integer> elem = new ListeElement<>(-100);

            assertEquals(-100, elem.getInfo());
        }

        @Test
        @DisplayName("Constructeur avec valeur à zéro")
        void testConstructeurAvecZero() {
            ListeElement<Integer> elem = new ListeElement<>(0);

            assertEquals(0, elem.getInfo());
        }

        @Test
        @DisplayName("getInfo sur élément par défaut")
        void testGetInfoSurElementParDefaut() {
            assertNull(element.getInfo(), "L'info doit être null par défaut");
        }

        @Test
        @DisplayName("getInfo après setInfo")
        void testGetInfoApresSetInfo() {
            element.setInfo(100);

            assertEquals(100, element.getInfo());
        }

        @Test
        @DisplayName("getInfo retourne la valeur du constructeur")
        void testGetInfoRetourneValeurConstructeur() {
            ListeElement<Integer> elem = new ListeElement<>(777);

            assertEquals(777, elem.getInfo());
        }

        @Test
        @DisplayName("getInfo avec type String")
        void testGetInfoAvecString() {
            ListeElement<String> elem = new ListeElement<>("Test");

            assertEquals("Test", elem.getInfo());
        }

        @Test
        @DisplayName("getInfo avec type Double")
        void testGetInfoAvecDouble() {
            ListeElement<Double> elem = new ListeElement<>(3.14);

            assertEquals(3.14, elem.getInfo(), 0.001);
        }

        @Test
        @DisplayName("getInfo après modification multiple")
        void testGetInfoApresModificationMultiple() {
            element.setInfo(1);
            element.setInfo(2);
            element.setInfo(3);

            assertEquals(3, element.getInfo(), "Doit retourner la dernière valeur");
        }


        @Test
        @DisplayName("setInfo doit modifier l'information")
        void testSetInfoModifieInfo() {
            element.setInfo(50);

            assertEquals(50, element.getInfo());
        }

        @Test
        @DisplayName("setInfo avec null")
        void testSetInfoAvecNull() {
            element.setInfo(100);
            element.setInfo(null);

            assertNull(element.getInfo(), "L'info doit être null");
        }

        @Test
        @DisplayName("setInfo plusieurs fois")
        void testSetInfoPlusieursFois() {
            element.setInfo(1);
            assertEquals(1, element.getInfo());

            element.setInfo(2);
            assertEquals(2, element.getInfo());

            element.setInfo(3);
            assertEquals(3, element.getInfo());
        }

        @Test
        @DisplayName("setInfo avec valeur négative")
        void testSetInfoAvecValeurNegative() {
            element.setInfo(-999);

            assertEquals(-999, element.getInfo());
        }

        @Test
        @DisplayName("setInfo avec même valeur")
        void testSetInfoAvecMemeValeur() {
            element.setInfo(42);
            element.setInfo(42);

            assertEquals(42, element.getInfo());
        }


        @Test
        @DisplayName("setInfo avec type String")
        void testSetInfoAvecString() {
            ListeElement<String> elem = new ListeElement<>();
            elem.setInfo("Hello");

            assertEquals("Hello", elem.getInfo());

            elem.setInfo("World");
            assertEquals("World", elem.getInfo());
        }




        @Test
        @DisplayName("getSuivant sur élément par défaut")
        void testGetSuivantSurElementParDefaut() {
            assertNull(element.getSuivant(), "Le suivant doit être null par défaut");
        }

        @Test
        @DisplayName("getSuivant après setSuivant")
        void testGetSuivantApresSuivant() {
            ListeElement<Integer> suivant = new ListeElement<>(200);
            element.setSuivant(suivant);

            assertSame(suivant, element.getSuivant(),
                    "Doit retourner la même référence");
        }

        @Test
        @DisplayName("getSuivant retourne null après constructeur")
        void testGetSuivantApresConstructeur() {
            ListeElement<Integer> elem = new ListeElement<>(10);

            assertNull(elem.getSuivant());
        }

        @Test
        @DisplayName("getSuivant sur chaîne d'éléments")
        void testGetSuivantSurChaine() {
            ListeElement<Integer> elem1 = new ListeElement<>(1);
            ListeElement<Integer> elem2 = new ListeElement<>(2);
            ListeElement<Integer> elem3 = new ListeElement<>(3);

            elem1.setSuivant(elem2);
            elem2.setSuivant(elem3);

            assertSame(elem2, elem1.getSuivant());
            assertSame(elem3, elem2.getSuivant());
            assertNull(elem3.getSuivant());
        }




        @Test
        @DisplayName("setSuivant doit lier deux éléments")
        void testSetSuivantLieElements() {
            ListeElement<Integer> suivant = new ListeElement<>(100);
            element.setSuivant(suivant);

            assertSame(suivant, element.getSuivant());
        }

        @Test
        @DisplayName("setSuivant avec null")
        void testSetSuivantAvecNull() {
            ListeElement<Integer> suivant = new ListeElement<>(50);
            element.setSuivant(suivant);

            element.setSuivant(null);
            assertNull(element.getSuivant());
        }

        @Test
        @DisplayName("setSuivant peut être modifié")
        void testSetSuivantPeutEtreModifie() {
            ListeElement<Integer> suivant1 = new ListeElement<>(10);
            ListeElement<Integer> suivant2 = new ListeElement<>(20);

            element.setSuivant(suivant1);
            assertEquals(10, element.getSuivant().getInfo());

            element.setSuivant(suivant2);
            assertEquals(20, element.getSuivant().getInfo());
        }

        @Test
        @DisplayName("setSuivant crée une chaîne")
        void testSetSuivantCreeChaine() {
            ListeElement<Integer> elem1 = new ListeElement<>(1);
            ListeElement<Integer> elem2 = new ListeElement<>(2);
            ListeElement<Integer> elem3 = new ListeElement<>(3);

            elem1.setSuivant(elem2);
            elem2.setSuivant(elem3);

            // Vérifier la chaîne
            assertEquals(1, elem1.getInfo());
            assertEquals(2, elem1.getSuivant().getInfo());
            assertEquals(3, elem1.getSuivant().getSuivant().getInfo());
        }

        @Test
        @DisplayName("setSuivant sur lui-même (boucle)")
        void testSetSuivantSurLuiMeme() {
            element.setInfo(42);
            element.setSuivant(element); // Crée une boucle

            assertSame(element, element.getSuivant(),
                    "Peut créer une référence circulaire");
            assertEquals(42, element.getSuivant().getInfo());
        }




        @Test
        @DisplayName("Créer une liste simple de 3 éléments")
        void testCreerListeSimple() {
            ListeElement<Integer> elem1 = new ListeElement<>(10);
            ListeElement<Integer> elem2 = new ListeElement<>(20);
            ListeElement<Integer> elem3 = new ListeElement<>(30);

            elem1.setSuivant(elem2);
            elem2.setSuivant(elem3);

            // Vérifier la structure
            assertEquals(10, elem1.getInfo());
            assertEquals(20, elem1.getSuivant().getInfo());
            assertEquals(30, elem1.getSuivant().getSuivant().getInfo());
            assertNull(elem1.getSuivant().getSuivant().getSuivant());
        }

        @Test
        @DisplayName("Parcourir une liste chaînée")
        void testParcourirListe() {
            // Créer une liste : 1 -> 2 -> 3 -> 4 -> 5
            ListeElement<Integer> tete = new ListeElement<>(1);
            ListeElement<Integer> courant = tete;

            for (int i = 2; i <= 5; i++) {
                ListeElement<Integer> nouveau = new ListeElement<>(i);
                courant.setSuivant(nouveau);
                courant = nouveau;
            }

            // Parcourir et vérifier
            int valeurAttendue = 1;
            courant = tete;
            while (courant != null) {
                assertEquals(valeurAttendue++, courant.getInfo());
                courant = courant.getSuivant();
            }

            assertEquals(6, valeurAttendue, "Doit avoir parcouru 5 éléments");
        }

        @Test
        @DisplayName("Compter les éléments d'une liste")
        void testCompterElements() {
            ListeElement<Integer> tete = new ListeElement<>(1);
            tete.setSuivant(new ListeElement<>(2));
            tete.getSuivant().setSuivant(new ListeElement<>(3));
            tete.getSuivant().getSuivant().setSuivant(new ListeElement<>(4));

            // Compter
            int compte = 0;
            ListeElement<Integer> courant = tete;
            while (courant != null) {
                compte++;
                courant = courant.getSuivant();
            }

            assertEquals(4, compte, "La liste doit contenir 4 éléments");
        }

        @Test
        @DisplayName("Insérer un élément au milieu")
        void testInsererAuMilieu() {
            ListeElement<Integer> elem1 = new ListeElement<>(1);
            ListeElement<Integer> elem3 = new ListeElement<>(3);
            elem1.setSuivant(elem3);


            ListeElement<Integer> elem2 = new ListeElement<>(2);
            elem2.setSuivant(elem1.getSuivant());
            elem1.setSuivant(elem2);


            assertEquals(1, elem1.getInfo());
            assertEquals(2, elem1.getSuivant().getInfo());
            assertEquals(3, elem1.getSuivant().getSuivant().getInfo());
        }

        @Test
        @DisplayName("Supprimer un élément du milieu")
        void testSupprimerDuMilieu() {
            ListeElement<Integer> elem1 = new ListeElement<>(1);
            ListeElement<Integer> elem2 = new ListeElement<>(2);
            ListeElement<Integer> elem3 = new ListeElement<>(3);

            elem1.setSuivant(elem2);
            elem2.setSuivant(elem3);


            elem1.setSuivant(elem2.getSuivant());


            assertEquals(1, elem1.getInfo());
            assertEquals(3, elem1.getSuivant().getInfo());
            assertNull(elem1.getSuivant().getSuivant());
        }

        @Test
        @DisplayName("Liste avec différents types")
        void testListeAvecDifferentsTypes() {
            ListeElement<String> str1 = new ListeElement<>("A");
            ListeElement<String> str2 = new ListeElement<>("B");
            ListeElement<String> str3 = new ListeElement<>("C");

            str1.setSuivant(str2);
            str2.setSuivant(str3);

            assertEquals("A", str1.getInfo());
            assertEquals("B", str1.getSuivant().getInfo());
            assertEquals("C", str1.getSuivant().getSuivant().getInfo());
        }




        @Test
        @DisplayName("Élément sans info ni suivant")
        void testElementSansInfoNiSuivant() {
            ListeElement<Integer> elem = new ListeElement<>();

            assertNull(elem.getInfo());
            assertNull(elem.getSuivant());
        }

        @Test
        @DisplayName("Chaîne très longue")
        void testChaineTresLongue() {
            ListeElement<Integer> tete = new ListeElement<>(0);
            ListeElement<Integer> courant = tete;

            for (int i = 1; i < 1000; i++) {
                ListeElement<Integer> nouveau = new ListeElement<>(i);
                courant.setSuivant(nouveau);
                courant = nouveau;
            }

            // Compter
            int compte = 0;
            courant = tete;
            while (courant != null) {
                compte++;
                courant = courant.getSuivant();
            }

            assertEquals(1000, compte);
        }

        @Test
        @DisplayName("Modification de l'info ne change pas le suivant")
        void testModificationInfoNeChangePasSuivant() {
            ListeElement<Integer> suivant = new ListeElement<>(200);
            element.setInfo(100);
            element.setSuivant(suivant);

            element.setInfo(999);

            assertSame(suivant, element.getSuivant(),
                    "Le suivant doit rester inchangé");
        }

        @Test
        @DisplayName("Modification du suivant ne change pas l'info")
        void testModificationSuivantNeChangePasInfo() {
            element.setInfo(100);
            ListeElement<Integer> suivant1 = new ListeElement<>(1);
            ListeElement<Integer> suivant2 = new ListeElement<>(2);

            element.setSuivant(suivant1);
            element.setSuivant(suivant2);

            assertEquals(100, element.getInfo(),
                    "L'info doit rester inchangée");
        }


        @Test
        @DisplayName("ListeElement avec Integer")
        void testAvecInteger() {
            ListeElement<Integer> elem = new ListeElement<>(42);
            assertEquals(42, elem.getInfo());
        }

        @Test
        @DisplayName("ListeElement avec String")
        void testAvecString() {
            ListeElement<String> elem = new ListeElement<>("Hello World");
            assertEquals("Hello World", elem.getInfo());
        }

        @Test
        @DisplayName("ListeElement avec Double")
        void testAvecDouble() {
            ListeElement<Double> elem = new ListeElement<>(3.14159);
            assertEquals(3.14159, elem.getInfo(), 0.00001);
        }

        @Test
        @DisplayName("ListeElement avec Boolean")
        void testAvecBoolean() {
            ListeElement<Boolean> elem = new ListeElement<>(true);
            assertTrue(elem.getInfo());
        }


        @Test
        @DisplayName("ListeElement avec Character")
        void testAvecCharacter() {
            ListeElement<Character> elem = new ListeElement<>('A');
            assertEquals('A', elem.getInfo());
        }




        @Test
        @DisplayName("Modifications répétées de l'info")
        void testModificationsRepetees() {
            for (int i = 0; i < 100; i++) {
                element.setInfo(i);
                assertEquals(i, element.getInfo());
            }
        }

        @Test
        @DisplayName("Modifications répétées du suivant")
        void testModificationsSuivantRepetees() {
            for (int i = 0; i < 100; i++) {
                ListeElement<Integer> suivant = new ListeElement<>(i);
                element.setSuivant(suivant);
                assertEquals(i, element.getSuivant().getInfo());
            }
        }

        @Test
        @DisplayName("Alternance setInfo et setSuivant")
        void testAlternanceModifications() {
            for (int i = 0; i < 50; i++) {
                element.setInfo(i);
                element.setSuivant(new ListeElement<>(i * 2));

                assertEquals(i, element.getInfo());
                assertEquals(i * 2, element.getSuivant().getInfo());
            }
        }

        @Test
        @DisplayName("Élément peut être réutilisé après null")
        void testReutilisationApresNull() {
            element.setInfo(100);
            element.setSuivant(new ListeElement<>(200));

            element.setInfo(null);
            element.setSuivant(null);

            assertNull(element.getInfo());
            assertNull(element.getSuivant());

            element.setInfo(300);
            element.setSuivant(new ListeElement<>(400));

            assertEquals(300, element.getInfo());
            assertEquals(400, element.getSuivant().getInfo());
        }

}