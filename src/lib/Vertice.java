package lib;

public class Vertice<T>{
    private T valor;

    public Vertice(T v) {
        this.valor = v;
    }

    public void setValor(T v) {
        this.valor = v;
    }

    public T getValor() {
        return this.valor;
    }
}