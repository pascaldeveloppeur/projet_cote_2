package Liste;

public class ListeElement<T> {
    private T info = null;
    private ListeElement<T> suivant = null;

    public ListeElement(T info){
        this.info = info;
        suivant = null;

    }

    public T getInfo(){
        return info;
    }
    public ListeElement<T> getSuivant(){
        return suivant;
    }
    public void setSuivant(ListeElement<T> suivant){
        this.suivant = suivant;
    }
    public void setInfo(T info){
        this.info = info;
    }



}
