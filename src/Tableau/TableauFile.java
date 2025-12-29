package Tableau;

import java.util.Optional;
import java.util.OptionalInt;

public class TableauFile<T> extends StructureTableau<T> {
    /**
     * Default constructor for the FilePile class.
     * Initializes the internal array with the default size.
     */
    public TableauFile() {
    }

    /**
     * Constructs an instance of the File class with a specified size.
     * Initializes the internal array with the given size.
     *
     * @param taille the desired size of the internal array for the File
     */
    public TableauFile(int taille) {
        super(taille);
    }



    /**
     * Removes and returns the next element in the queue. If the queue is empty, an empty {@link OptionalInt} is returned.
     * The method dynamically reduces the internal array size if the number of elements falls below half the current capacity,
     * ensuring efficient memory usage.
     *
     * @return an {@link OptionalInt} containing the next element of the queue if it is not empty, otherwise an empty {@link OptionalInt}.
     */
    @Override
    public Optional<T> pop() {
        if (estVide()) {
            return Optional.empty();
        }
        var monTableau = getTableau();
        var nbElement = getNbElement();
        var pointeurPop = getPointeurPop();
        setNbElement(--nbElement);
        setPointeurPop(++pointeurPop);
        var retour = Optional.of(monTableau[getPointeurPop()]);

        if (nbElement < (monTableau.length + 1) / 2 && monTableau.length * 3 / 4 >= getTailleDepart()) {
            var tempTableau =(T[]) new Object[monTableau.length * 3 / 4];
            System.arraycopy(monTableau, pointeurPop, tempTableau, 0, nbElement);
            monTableau = tempTableau;
            pointeurPop = 0;
            setPointeurPush(nbElement);
        }

        return retour;
    }
}
