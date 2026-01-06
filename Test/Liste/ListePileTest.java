package Liste;

import Interface.IFilePile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListePileTest {

    StructureListe<Integer> listePile;
    ListeElement<Integer> listeElement;

    @BeforeEach
    void setUp() {
        listePile = new ListePile<>(2);
        listeElement = new ListeElement<>(4);
    }
    @AfterEach
    void tearDown() {
        if (listePile != null) listePile.clear();
    }
    @Test
    @DisplayName("Pop sur Pile vide doit retourner Optional.empty()")
    void popSurPileVide() {
        Optional<Integer> result = listePile.pop();

        assertFalse(result.isEmpty(), "Doit retourner Optional avec un élément");
        assertEquals(0, listePile.size());
    }



    @Test
    @DisplayName("Pop après push sur Pile - comportement LIFO")
    void popApresPushPileLIFO() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        // LIFO : Last In First Out
        Optional<Integer> result1 = listePile.pop();
        assertTrue(result1.isPresent());
        assertEquals(3, result1.get(), "Dernier élément ajouté (3)");
        assertEquals(3, listePile.size());

        Optional<Integer> result2 = listePile.pop();
        assertEquals(2, result2.get(), "Troisième élément (3)");

        Optional<Integer> result3 = listePile.pop();
        assertEquals(1, result3.get(), "Deuxième élément (2)");
        Optional<Integer> result4 = listePile.pop();
        assertEquals(2, result4.get(), "Premier élément (1)");
        assertTrue(listePile.estVide(), "Doit être vide");
    }

    @Test
    @DisplayName("Pop jusqu'à vider complètement")
    void popJusquAVider() {
        listePile.push(1);
        listePile.push(2);
        listePile.push(3);

        listePile.pop();
        assertFalse(listePile.estVide());

        listePile.pop();
        assertFalse(listePile.estVide());
        listePile.pop();
        assertFalse(listePile.estVide());

        listePile.pop();
        assertTrue(listePile.estVide(), "Doit être vide après tous les pops");

        Optional<Integer> result = listePile.pop();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Pop de null")
    void popDeNull() {
        listePile.push(null);

        Optional<Integer> result = listePile.pop();
        assertTrue(result.isPresent(), "Doit retourner Optional présent");
        assertNotNull(result.get(), "La valeur doit être 2");
    }

}