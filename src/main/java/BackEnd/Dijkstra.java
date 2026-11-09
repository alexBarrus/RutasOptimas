package BackEnd;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Consumer;

/** Dijkstra con cola de prioridad. Termina al extraer el destino. */
public class Dijkstra extends AlgoritmoCamino {

    public static final String NOMBRE = "Dijkstra";

    private record Entrada(String id, double distancia) {
    }

    public Dijkstra(Consumer<String> traza) {
        super(traza);
    }

    @Override
    public String getNombre() {
        return NOMBRE;
    }

    @Override
    protected boolean aceptaGrafo() {
        for (Arista arista : grafo.getAristas()) {
            if (arista.getPeso() < 0) {
                escribir("[ERROR] Dijkstra no admite pesos negativos: " + arista
                        + ". Use Bellman-Ford para este grafo.");
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean buscar(String origen, String destino) {
        PriorityQueue<Entrada> cola = new PriorityQueue<>(Comparator.comparingDouble(Entrada::distancia));
        Set<String> visitados = new HashSet<>();
        cola.add(new Entrada(origen, 0));
        int paso = 0;
        while (!cola.isEmpty()) {
            Entrada entrada = cola.remove();
            String u = entrada.id();
            if (!visitados.add(u)) {
                escribir("[COLA] Ignora entrada obsoleta de " + u + " con costo " + formatear(entrada.distancia()));
                continue;
            }
            paso++;
            escribir("[PASO " + paso + "] Extrae " + u + " con distancia " + formatear(entrada.distancia()));
            if (u.equals(destino)) {
                escribir("  Se extrajo el destino: su distancia ya es definitiva.");
                return true;
            }
            for (Arista arista : grafo.getRutasDesde(u)) {
                if (relajar(arista)) {
                    cola.add(new Entrada(arista.getDestino(), distancia.get(arista.getDestino())));
                }
            }
            escribirDistancias();
        }
        escribir("[COLA] Quedó vacía sin alcanzar el destino.");
        return true;
    }
}
