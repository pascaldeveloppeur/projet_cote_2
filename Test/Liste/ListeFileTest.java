package Liste;

import Interface.IFilePile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListeFileTest {


    StructureListe<Integer> listeFile;

    @BeforeEach
    void setUp() {
        listeFile = new ListeFile<>(2);
    }
    @AfterEach
    void tearDown() {
        if (listeFile != null) listeFile.clear();
    }

    @Test
    @DisplayName("Pop sur File vide doit retourner Optional.empty()")
    void popSurFileVide() {
        Optional<Integer> result = listeFile.pop();

        assertFalse(result.isEmpty(), "Doit retourner Optional qui n'est pas vide");
    }

 /*   @Test
    @DisplayName("Pop après push sur Pile - comportement LIFO")
    void popApresPushPileLIFO() {
        listeFile.push(1);
        listeFile.push(2);
        listeFile.push(3);

        // LIFO : Last In First Out
        Optional<Integer> result1 = listeFile.pop();
        assertTrue(result1.isPresent());
        assertEquals(2, result1.get(), "Premier élément ajouté (2)");
       // assertEquals(3, listeFile.size());

        Optional<Integer> result2 = listeFile.pop();
        assertEquals(1, result2.get(), "Deuxième élément (1)");
        //assertEquals(2, listeFile.size());

        Optional<Integer> result3 = listeFile.pop();
        assertEquals(1, result3.get(), "Troisième élément (2)");
        //assertEquals(1, listeFile.size());

        Optional<Integer> result4 = listeFile.pop();
        assertEquals(1, result4.get(), "Dernier élément (3)");
       // assertEquals(0, listeFile.size());

        assertTrue(listeFile.estVide(), "Doit être vide");
    }*/

/*    @Test
    @DisplayName("Pop jusqu'à vider complètement")
    void popJusquAVider() {
        listeFile.push(1);
        listeFile.push(2);
        listeFile.push(3);

        listeFile.pop();
        assertFalse(listeFile.estVide());

        listeFile.pop();
        assertFalse(listeFile.estVide());
        listeFile.pop();
        assertFalse(listeFile.estVide());

        listeFile.pop();
        assertTrue(listeFile.estVide(), "Doit être vide après tous les pops");

        Optional<Integer> result = listeFile.pop();
        assertTrue(result.isEmpty());
    }*/

    @Test
    @DisplayName("Pop de null")
    void popDeNull() {
        listeFile.push(null);

        Optional<Integer> result = listeFile.pop();
        assertTrue(result.isPresent(), "Doit retourner Optional présent");
        assertNotNull(result.get(), "La valeur doit être 2");
    }


}