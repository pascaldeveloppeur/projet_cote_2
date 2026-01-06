package Liste;

import Interface.IFilePile;

public abstract class StructureListe<T> implements IFilePile<T> {

    private ListeElement<T> noeud;
    private int nombre ;

    public StructureListe(T info) {
        this.noeud = new ListeElement<>(info);
        this.nombre = 0;
    }

    public StructureListe() {}

    public ListeElement<T> getNoeud() {
        return noeud;
    }
    public void setNoeud(ListeElement<T> noeud) {
        this.noeud = noeud;
    }
    public void push(T element) {
        if (element != null) {
            if (noeud.getInfo() == null) {
                noeud.setInfo(element);
            } else {
                var temp = new ListeElement<>(element);
                var dernier = trouveDernier();
                dernier.setSuivant(temp);
            }
        }
    }

    public int getNombre() {
        return nombre;
    }
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public ListeElement<T> trouveLeReferent(ListeElement<T> reference) {
        ListeElement<T> p = this.getNoeud();
        while (p.getSuivant() != reference) {
            p = p.getSuivant();
        }
        return p;
    }

    public int size() {
        ListeElement<T> p = this.noeud;
        //var nombre = 0;
        //if (p.getInfo() != null) nombre++;
        while (p.getSuivant() != null) {
            p = p.getSuivant();
            nombre++;
        }
        return nombre;
    }

    public ListeElement<T> trouveDernier() {
        // Utilisation d'un pointeur p qui va parcourir chaque paire <info, suivant>
        ListeElement<T> p = this.noeud;
        // Parcourt toues les éléments de la liste pour s'arrêter sur le dernier
        // car le pointeur "suivant" du dernier vaut null
        while (p.getSuivant() != null) {
            p = p.getSuivant();
        }
        return p;
    }

    @Override
    public boolean estPleine() {
        return false;
    }

    @Override
    public boolean estVide() {
        return noeud.getInfo() == null;
    }

    public void clear() {
        noeud.setInfo(null);
        noeud.setSuivant(null);
    }

}
