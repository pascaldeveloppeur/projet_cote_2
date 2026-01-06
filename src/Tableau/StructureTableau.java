package Tableau;

import Interface.IFilePile;

import java.util.Arrays;
import java.util.OptionalInt;

public abstract class StructureTableau<T> implements IFilePile<T> {

    static private final int tailleDefaut = 4;
    private int tailleDepart;
    private T[] tableau;
    private int nbElement = 0;
    private int pointeurPush = 0;
    private int pointeurPop = 0;


    /**
     * Default constructor for the FilePile class.
     * Initializes the internal array with the default size.
     */
    public StructureTableau() {
        this(tailleDefaut);
    }

    /**
     * Constructs an instance of the FilePile class with a specified size.
     * If the provided size is less than or equal to zero, the default size will be used instead.
     *
     * @param taille the desired size of the internal array for the FilePile
     */
    public StructureTableau(int taille) {
        if (taille <= 0) {
            System.out.println("Taille de la pile est invalide, on utilisera donc la taille par défaut");
            tailleDepart = tailleDefaut;
        } else
            tailleDepart = taille;
        tableau = (T[]) new Object[taille];
    }
    public T[] getTableau() {
        return tableau;
    }

    public int getTailleDepart() {
        return tailleDepart;
    }

    public int getNbElement() {
        return nbElement;
    }

    public int getPointeurPush() {
        return pointeurPush;
    }

    public int getPointeurPop() {
        return pointeurPop;
    }

    public void setTableau(T[] tableau) {
        this.tableau = tableau;
    }


    public void setTailleDepart(int tailleDepart) {
        this.tailleDepart = tailleDepart;
    }

    public void setNbElement(int nbElement) {
        this.nbElement = nbElement;
    }

    public void setPointeurPush(int pointeurPush) {
        this.pointeurPush = pointeurPush;
    }

    public void setPointeurPop(int pointeurPop) {
        this.pointeurPop = pointeurPop;
    }


    /**
     * Pushes an integer element onto the data structure. If the underlying array is full, it dynamically
     * increases the size of the array. For queue-specific functionality, it rearranges the array
     * elements to accommodate new entries at the appropriate position.
     *
     * @param element the integer value to be added to the data structure
     */
    public void push(T element) {
        if (estPleine()) {
            tableau = Arrays.copyOf(tableau, tableau.length + tailleDefaut);
        } else {
            // Spécifique FILE
            if (pointeurPush == tableau.length) {
                System.arraycopy(tableau, pointeurPop, tableau, 0, nbElement);
                pointeurPush = nbElement;
                pointeurPop = 0;
            }
        }
        tableau[pointeurPush++] = element;
        nbElement++;
    }

    /**
     * Removes and returns the top element of the stack, if present.
     * If the stack is empty, an empty {@link OptionalInt} is returned instead.
     *
     * @return an {@link OptionalInt} containing the top element of the stack
     *         if it is not empty, otherwise an empty {@link OptionalInt}.
     */
//    public abstract OptionalInt pop();

    /**
     * Clears the current stack or queue by resetting its internal array to its initial capacity.
     * All elements in the structure are discarded and the count of elements is reset to zero.
     */
    public void clear() {
        tableau = (T[]) new Object[tailleDepart];
        nbElement = 0;
        pointeurPush = 0;
        pointeurPop = 0;
    }

    /**
     * Returns the number of elements currently present in the structure.
     * @return nombre d'éléments dans la structure
     */
    public int size() {
        return nbElement;
    }

    /**
     * Checks if the data structure is empty.
     * @return true si le tableau est vide, false sinon
     */
    public boolean estVide() {
        return nbElement == 0;
    }

    /**
     * Checks if the data structure is currently full.
     * @return true si le tableau est plein, false sinon
     */
    public boolean estPleine() {
        return nbElement == tableau.length;
    }

    /**
     * Returns a string representation of the current state of the data structure.
     * The output includes the elements of the structure in their respective order,
     * enclosed within square brackets and separated by commas.
     *
     * @return a string representation of the elements in the data structure
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nbElement; i++) {
            sb.append(tableau[pointeurPush - nbElement + i]).append(", ");
        }
        if (nbElement > 0)
            sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
