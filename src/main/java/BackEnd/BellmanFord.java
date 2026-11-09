package BackEnd;

import java.util.function.Consumer;

/** Bellman-Ford: relaja todas las rutas hasta V-1 veces y detecta ciclos negativos. */
public class BellmanFord extends AlgoritmoCamino {

    public static final String NOMBRE = "Bellman-Ford";

    public BellmanFord(Consumer<String> traza) {
        super(traza);
    }

    @Override
    public String getNombre() {
        return NOMBRE;
    }

    @Override
    protected boolean buscar(String origen, String destino) {
        int iteraciones = grafo.numeroVertices() - 1;
        for (int i = 1; i <= iteraciones; i++) {
            escribir("[ITERACIÓN " + i + " de " + iteraciones + "]");
            boolean huboCambios = false;
            for (Vertice vertice : grafo.getVertices()) {
                String u = vertice.getId();
                if (Double.isInfinite(distancia.get(u))) {
                    escribir("  Omite las rutas de " + u + ": aún no tiene distancia.");
                    continue;
                }
                escribir("  Visita " + u);
                for (Arista arista : grafo.getRutasDesde(u)) {
                    huboCambios |= relajar(arista);
                }
            }
            escribirDistancias();
            if (!huboCambios) {
                escribir("  Sin cambios en esta iteración: las distancias ya son definitivas.");
                return true;
            }
        }
        return verificarCiclos();
    }

    private boolean verificarCiclos() {
        escribir("[VERIFICACIÓN] Busca ciclos negativos alcanzables desde el origen.");
        for (Vertice vertice : grafo.getVertices()) {
            double du = distancia.get(vertice.getId());
            if (Double.isInfinite(du)) {
                continue;
            }
            for (Arista arista : grafo.getRutasDesde(vertice.getId())) {
                if (du + arista.getPeso() < distancia.get(arista.getDestino())) {
                    escribir("[ERROR] Ciclo negativo detectado en " + arista + ": no existe un costo mínimo definido.");
                    return false;
                }
            }
        }
        escribir("  No hay ciclos negativos.");
        return true;
    }
}
