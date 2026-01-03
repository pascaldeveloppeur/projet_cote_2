package Liste;

public class ListeElement<T> {
    private T info ;
    private ListeElement<T> suivant ;

    public ListeElement() {

    }
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
