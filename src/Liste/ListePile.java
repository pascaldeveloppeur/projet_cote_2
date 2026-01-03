package Liste;

import Interface.IFilePile;

import java.util.Optional;

public class ListePile<T> extends StructureListe<T> implements IFilePile<T> {



    public ListePile() {
    }
    public ListePile(T info) {
        super(info);
    }


    public Optional<T> pop() {
        if (getNoeud().getSuivant() == null) {
            var retour = Optional.ofNullable(getNoeud().getInfo());
            getNoeud().setInfo(null); ;
            return retour;
        }

        var dernier = trouveDernier();
        var retour = Optional.of(dernier.getInfo());
        var referent = trouveLeReferent(dernier);
        referent.setSuivant(null);
//        trouveLeReferent(dernier).suivant = null;

        return retour;
    }








}
