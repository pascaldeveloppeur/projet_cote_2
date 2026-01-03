package Tableau;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TableauPileTest {
    private StructureTableau<Integer> tableauPile;

    @BeforeEach
    void setUp() {
        tableauPile = new TableauPile<>();
    }

    @AfterEach
    void afterEach() {
        tableauPile.clear();
    }

    @Test
    @DisplayName("Pop jusqu'à vider complètement")
    void popJusquAVider() {
        tableauPile.push(1);
        tableauPile.push(2);

        tableauPile.pop();
        assertFalse(tableauPile.estVide());

        tableauPile.pop();
        assertTrue(tableauPile.estVide(), "Doit être vide après tous les pops");

        Optional<Integer> result = tableauPile.pop();
        assertTrue(result.isEmpty(), "Pop sur pile vide");
    }

    @Test
    @DisplayName("Alternance push et pop")
    void alternancePushPop() {
        tableauPile.push(1);
        tableauPile.push(2);

        assertEquals(2, tableauPile.pop().get());

        tableauPile.push(3);
        tableauPile.push(4);

        assertEquals(4, tableauPile.pop().get());
        assertEquals(3, tableauPile.pop().get());
        assertEquals(1, tableauPile.pop().get());
        assertTrue(tableauPile.estVide());
    }
    @Test
    @DisplayName("Pop sur Pile vide doit retourner Optional.empty()")
    void popSurPileVide() {
        Optional<Integer> result = tableauPile.pop();

        assertTrue(result.isEmpty(), "Pop sur pile vide doit retourner Optional vide");
        assertEquals(0, tableauPile.size());
    }

    @Test
    @DisplayName("Pop après push sur Pile - comportement LIFO")
    void popApresPushPileLIFO() {
        tableauPile.push(1);
        tableauPile.push(2);
        tableauPile.push(3);

        // LIFO : Last In First Out
        Optional<Integer> result1 = tableauPile.pop();
        assertTrue(result1.isPresent());
        assertEquals(3, result1.get(), "Doit retourner le dernier élément (3)");
        assertEquals(2, tableauPile.size());

        Optional<Integer> result2 = tableauPile.pop();
        assertEquals(2, result2.get(), "Doit retourner 2");
        assertEquals(1, tableauPile.size());

        Optional<Integer> result3 = tableauPile.pop();
        assertEquals(1, result3.get(), "Doit retourner 1");
        assertTrue(tableauPile.estVide());
    }
}