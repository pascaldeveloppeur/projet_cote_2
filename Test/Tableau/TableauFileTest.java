package Tableau;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TableauFileTest {
    private StructureTableau<Integer> tableauFile;
    @BeforeEach
    void setUp() {
        tableauFile = new TableauFile<>();
    }

    @AfterEach
    void afterEach() {
        tableauFile.clear();

    }



    @Test
    @DisplayName("Pop après push sur File - comportement FIFO")
    void popApresPushFileFIFO() {
        tableauFile.push(10);
        tableauFile.push(20);
        tableauFile.push(30);

        // FIFO : First In First Out
        Optional<Integer> result1 = tableauFile.pop();
        assertTrue(result1.isPresent());
        assertEquals(10, result1.get(), "Doit retourner le premier élément (10)");
        assertEquals(2, tableauFile.size());

        Optional<Integer> result2 = tableauFile.pop();
        assertEquals(20, result2.get(), "Doit retourner 20");

        Optional<Integer> result3 = tableauFile.pop();
        assertEquals(30, result3.get(), "Doit retourner 30");
        assertTrue(tableauFile.estVide());
    }



    @Test
    @DisplayName("Pop sur File vide doit retourner Optional.empty()")
    void popSurFileVide() {
        Optional<Integer> result = tableauFile.pop();

        assertTrue(result.isEmpty(), "Pop sur file vide doit retourner Optional vide");
        assertEquals(0, tableauFile.size());
    }
}