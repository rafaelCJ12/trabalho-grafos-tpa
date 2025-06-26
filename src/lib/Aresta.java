package lib;

public class Aresta<T> {
    private Vertice<T> origem;
    private Vertice<T> destino;
    private double peso;

    public Aresta(Vertice<T> o, Vertice<T> d, double p) {
        this.origem = o;
        this.destino = d;
        this.peso = p;
    }

    public void setOrigem(Vertice<T> o) {
        this.origem = o;
    }

    public Vertice<T> getOrigem() {
        return this.origem;
    }

    public void setDestino(Vertice<T> d) {
        this.destino = d;
    }

    public Vertice<T> getDestino() {
        return this.destino;
    }

    public void setPeso(double p) {
        this.peso = p;
    }

    public double getPeso() {
        return this.peso;
    }
    
}
