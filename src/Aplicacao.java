import lib.Grafo;
import lib.Vertice;

public class Aplicacao {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>(false);


        grafo.adicionarAresta("Terminal do Ibes", "Terminal Vila Velha", 3.4);
        grafo.adicionarAresta("Terminal do Ibes", "Terminal Itaparica", 8.6);
        grafo.adicionarAresta("Terminal do Ibes", "Terminal Sao Torquato", 6.8);
        grafo.adicionarAresta("Terminal do Ibes", "Parque da Prainha", 4.2);

        grafo.adicionarAresta("Terminal Vila Velha", "Terminal Itaparica", 8.1);
        grafo.adicionarAresta("Terminal Vila Velha", "Terminal Sao Torquato", 9.4);
        grafo.adicionarAresta("Terminal Vila Velha", "Parque da Prainha", 2.1);

        grafo.adicionarAresta("Terminal Itaparica", "Terminal Sao Torquato", 10.4);
        grafo.adicionarAresta("Terminal Itaparica", "Parque da Prainha", 8.7);

        
        grafo.buscaEmLargura();
        System.out.println("\n");
        grafo.dijkstra(new Vertice<String>("Terminal Sao Torquato"), new Vertice<String>("Parque da Prainha"));
        System.out.println("\n");
        grafo.kruskal();


        
    }
}
