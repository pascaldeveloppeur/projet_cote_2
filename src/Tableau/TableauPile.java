package Tableau;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class TableauPile<T> extends StructureTableau<T>{
    /**
     * Constructs an instance of the Pile class with a specified size.
     * The size determines the initial capacity of the stack's internal array.
     *
     * @param taille the desired size of the internal array for the Pile
     */
    public TableauPile(int taille) {
        super(taille);
    }

    /**
     * Default constructor for the Pile class.
     * Initializes the Pile instance with default properties and behaviors inherited from the FilePile class.
     */
    public TableauPile() {
        super();
    }

    /**
     * Removes and returns the top element of the stack, if present.
     * If the stack is empty, an empty {@link OptionalInt} is returned instead.
     *
     * @return an {@link OptionalInt} containing the top element of the stack
     * if it is not empty, otherwise an empty {@link OptionalInt}.
     */
    @Override
    public Optional<T> pop() {
        if (estVide()) {
            return Optional.empty();
        }
        var monTableau = getTableau();
        var nbElement = getNbElement();
        var pointeurPush = getPointeurPush();
        setNbElement(--nbElement);
        setPointeurPush(--pointeurPush);
        var retour = Optional.of(monTableau[getPointeurPush()]);

        if (getNbElement() < (monTableau.length + 1) / 2 && monTableau.length * 3 / 4 >= getTailleDepart()) {
            monTableau = Arrays.copyOf(monTableau, monTableau.length * 3 / 4);
            pointeurPush = getNbElement();
        }

        return retour;
    }
}
