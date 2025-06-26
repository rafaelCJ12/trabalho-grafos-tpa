package lib;

import java.util.Comparator;

public class ComparadorArestas<T> implements Comparator<Aresta<T>>{
    public int compare(Aresta<T> a1, Aresta<T> a2) {
        return Double.compare(a1.getPeso(), a2.getPeso());
    }
    
}
