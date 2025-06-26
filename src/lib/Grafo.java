package lib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Grafo<T> {
    private List<Vertice<T>> vertices;
    private List<Aresta<T>> arestas;
    private boolean direcionado;

    public Grafo(boolean d) {
        this.arestas = new LinkedList<Aresta<T>>();
        this.vertices = new LinkedList<Vertice<T>>();
        this.direcionado = d;
    }

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);

        return novo;
    }

    public Vertice<T> obterVertice(T valor) {
        int i = 0;
        Vertice<T> v = null;

        for(i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);

            if(v.getValor().equals(valor)) {
                return v;
            } 
        }

        return null;
    }

    public void adicionarAresta(T origem, T destino, double peso) {
        Vertice<T> verticeOrigem = this.obterVertice(origem);
        Vertice<T> verticeDestino = this.obterVertice(destino);
        Aresta<T> novaAresta = null;

        if(verticeOrigem == null) {
            verticeOrigem = this.adicionarVertice(origem);
        }

        if(verticeDestino == null) {
            verticeDestino = this.adicionarVertice(destino);
        }

        novaAresta = new Aresta<>(verticeOrigem, verticeDestino, peso);
        this.arestas.add(novaAresta);

        if(!this.direcionado) {
            novaAresta = new Aresta<>(verticeDestino, verticeOrigem, peso);
            this.arestas.add(novaAresta);
        }
 
    }

    private List<Aresta<T>> obterDestinos(Vertice<T> v) {
        List<Aresta<T>> destinos = new LinkedList<>();
        Aresta<T> atual = null;
        int i = 0;

        for(i = 0; i < this.arestas.size(); i++) {
            atual = this.arestas.get(i);

            if(atual.getOrigem().equals(v)) {
                destinos.add(atual);
            }
        }
        return destinos;
    }

    public void buscaEmLargura() {
        List<Vertice<T>> marcados = new LinkedList<Vertice<T>>();
        Queue<Vertice<T>> fila = new LinkedList<>();
        Vertice<T> atual = this.vertices.get(0);
        List<Aresta<T>> destinos = null;
        Vertice<T> proximo = null;
        int i = 0;

        fila.add(atual);

        while(!fila.isEmpty()) {
            atual = fila.poll();
            marcados.add(atual);

            System.out.println(atual.getValor());
            destinos = this.obterDestinos(atual);

            for(i = 0; i < destinos.size(); i++) {
                proximo = destinos.get(i).getDestino();

                if(!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }



    public void dijkstra(Vertice<T> partida, Vertice<T> chegada) {
        double distancia = Double.MAX_VALUE;
        double[] distancias = new double[this.vertices.size()];
        int[] predecessores = new int[this.vertices.size()];
        boolean[] noVisitado = new boolean[this.vertices.size()];
        int i = 0;
        int indiceAtual = 0;
        int indiceVizinho = 0;
        boolean controle = true;
        Vertice<T> atual = null;
        List<Aresta<T>> listaArestas = null;

        partida = this.obterVertice(partida.getValor());
        chegada = this.obterVertice(chegada.getValor());

        if (partida == null || chegada == null) {
            System.out.println("\nERRO: vertice nao pertencente ao grafo!");
            return;
        }

        // Inicializa as distancias
        for (i = 0; i < this.vertices.size(); i++) {
            distancias[i] = distancia;
            predecessores[i] = -1;
            noVisitado[i] = false;
        }
        //a distancia do no de partida e igual a zero
        distancias[this.vertices.indexOf(partida)] = 0;

        while (controle) {
            distancia = Double.MAX_VALUE;
            indiceAtual = -1;

            for (i = 0; i < this.vertices.size(); i++) {
                //se o no nao foi visitado ainda e a distancia eh menor que a distancia guardada
                if (!noVisitado[i] && distancias[i] < distancia) {
                    distancia = distancias[i];
                    indiceAtual = i;
                }
            }

            if (indiceAtual == -1 || this.vertices.get(indiceAtual).equals(chegada)) {
                controle = false; // Todos os vértices visitados ou destino alcançado
            }

            else{
                noVisitado[indiceAtual] = true;
                atual = this.vertices.get(indiceAtual);

                listaArestas = this.obterDestinos(atual);

                for(i = 0; i < listaArestas.size(); i++) {
                    indiceVizinho = this.vertices.indexOf(listaArestas.get(i).getDestino());
                    if(!noVisitado[indiceVizinho]) {
                        distancia = distancias[indiceAtual] + listaArestas.get(i).getPeso();

                        if(distancia < distancias[indiceVizinho]) {
                            distancias[indiceVizinho] = distancia;
                            predecessores[indiceVizinho] = indiceAtual;

                        }
                    }
                }

            }

        }

        if(distancias[this.vertices.indexOf(chegada)] == Double.MAX_VALUE) {
            System.out.println("\nNao existe caminho entre os vértices.");
            return;
        }

        i = this.vertices.indexOf(chegada);

        while (i != this.vertices.indexOf(partida)) {
            System.out.print(this.vertices.get(i).getValor().toString() + " <- ");
            i = predecessores[i];
        }

        System.out.print(this.vertices.get(i).getValor().toString());

        System.out.println("\nO custo total do caminho minimo e: " + distancias[this.vertices.indexOf(chegada)]);

    }

    public void kruskal() {
        List<Aresta<T>> listaArestas = null;
        int i = 0;
        List<Aresta<T>> arestasArvoreGeradoraMinima = new LinkedList<>();
        ComparadorArestas<T> comparadorArestas = new ComparadorArestas<>();
        Conjunto<T> conjunto = new Conjunto<>();
        double custo = 0;
        String s1 = "";
        String s2 = "";
        Set<String> conjuntoStrings = null;
        
        if(!this.direcionado) {
            listaArestas = new LinkedList<>();
            conjuntoStrings = new HashSet<>();

            for(i = 0; i < this.arestas.size(); i++) {
                s1 = this.arestas.get(i).getOrigem().getValor().toString() + this.arestas.get(i).getDestino() +
                this.arestas.get(i).getPeso();

                s2 = this.arestas.get(i).getDestino().getValor().toString() + this.arestas.get(i).getOrigem() +
                this.arestas.get(i).getPeso();

                if(!conjuntoStrings.contains(s1) && !conjuntoStrings.contains(s2)) {
                    conjuntoStrings.add(s1);
                    listaArestas.add(this.arestas.get(i));
                }
            }

            
        }

        else{
            listaArestas = this.arestas;
        }

        listaArestas.sort(comparadorArestas);
        conjunto.preencheConjunto(this.vertices);

        for(i = 0; i < listaArestas.size(); i++){
            if(!conjunto.verticesConectados(listaArestas.get(i).getOrigem(), listaArestas.get(i).getDestino())) {
                arestasArvoreGeradoraMinima.add(listaArestas.get(i));
                conjunto.uniao(listaArestas.get(i).getOrigem(), listaArestas.get(i).getDestino());
            }

        }

        for(i = 0; i < arestasArvoreGeradoraMinima.size(); i++) {
            custo += arestasArvoreGeradoraMinima.get(i).getPeso();

            System.out.println(arestasArvoreGeradoraMinima.get(i).getOrigem().getValor().toString() + 
            " -> " + arestasArvoreGeradoraMinima.get(i).getDestino().getValor().toString());
        }

        System.out.println("\nO custo total da arvore geradora minima eh: " + custo);

    }

   
}
