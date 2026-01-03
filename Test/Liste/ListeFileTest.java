package Liste;

import Interface.IFilePile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        listePile = null;
        listeFile = null;
    }

    @Test
    void pop() {
    }
}