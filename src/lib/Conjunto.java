package lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conjunto<T> {

    private Map<Vertice<T>, Vertice<T>> map;

    public Conjunto() {
        this.map = new HashMap<>();
    }

    public void preencheConjunto(List<Vertice<T>> vertices) {
        int i = 0;

        for(i = 0; i < vertices.size(); i++) {
            this.map.put(vertices.get(i), vertices.get(i));
        }
    }

    public Vertice<T> representante(Vertice<T> v) {
        if(!map.get(v).equals(v)) {
            map.put(v, this.representante(map.get(v)));
        }

        return this.map.get(v);
    }

    public void uniao(Vertice<T> a, Vertice<T> b) {
        Vertice<T> repA = this.representante(a);
        Vertice<T> repB = this.representante(b);

        if(!repA.equals(repB)) {
            this.map.put(repA, repB);
        }
    }

    public boolean verticesConectados(Vertice<T> a, Vertice<T> b) {
        return this.representante(a).equals(representante(b)); 
    }
    
}
