package Liste;

import Interface.IFilePile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListeFileTest {

    IFilePile<Integer> listePile;
    IFilePile<Integer> listeFile;

    @BeforeEach
    void setUp() {
        listePile = new ListePile<>();
        listeFile = new ListeFile<>();
    }
    @AfterEach
    void tearDown() {
        if (listePile != null) listePile.clear();
        if (listeFile != null) listeFile.clear();
    }

    @Test
    @DisplayName("Pop sur File vide doit retourner Optional.empty()")
    void popSurFileVide() {
        Optional<Integer> result = listeFile.pop();

        assertFalse(result.isEmpty(), "Doit retourner Optional qui n'est pas vide");
    }
}