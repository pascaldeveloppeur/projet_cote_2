package Interface;



import java.util.Optional;

/**
 * Interface pour la manipulation d'une structure d'éléments
 * Cette interface est utilisée pour la gestion d'une pile ou d'une file
 * @param <T> Elément qui est le cœur de l'information manipulée
 */
public interface IFilePile<T> {
    /**
     * Ajoute un nouvel élément dans la Structure
     * @param element élement à ajouter
     */
    void push(T element);

    /**
     * Retourne et supprime un élément de la Structure
     * @return le premier élément dans le cas d'une file, le dernier élément dans le cas d'une pile
     */
    Optional<T> pop();

    /**
     * Retourne le nombre d'éléments dans la Structure
     * @return le nombre d'éléments dans la Structure
     */
    int size();

    boolean estPleine();

    boolean estVide();

    /**
     * Vide la Structure de tous ses éléments
     * Après l'appel de clear(), size() doit retourner 0
     */
    void clear();

    /**
     * Retourne les éléments de la Structure sous la forme d'un String en tenant compte s'il s'agit d'une file ou d'une pile
     * @return un String qui comprend la liste des éléments de la Structure dans l'ordre qu'il devrait apparaître avec la méthode pop()
     */
    String toString();
}
