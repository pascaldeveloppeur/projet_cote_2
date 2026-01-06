package Liste;


import Interface.IFilePile;

import java.util.Optional;

public class ListeFile<T> extends StructureListe<T> implements IFilePile<T> {


    public ListeFile(T info) {
        super(info);
    }

    public ListeFile() {


    }


    public Optional<T> pop() {
        Optional<T> retour;
        if (getNoeud().getSuivant() == null) {
            retour = Optional.ofNullable(getNoeud().getInfo());
            getNoeud().setInfo(null);
//            return retour;
        } else {

            retour = Optional.ofNullable(getNoeud().getInfo());
            getNoeud().setInfo(getNoeud().getSuivant().getInfo());
            getNoeud().setSuivant(getNoeud().getSuivant());
            //
            //
             //je dois vérifier ici
             //
        }
        setNombre(getNombre() -1);

        return retour;
    }










}
