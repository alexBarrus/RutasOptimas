package BackEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Parte común de los algoritmos de camino más corto: validación, inicialización,
 * relajación de aristas con su traza y armado del resultado.
 */
public abstract class AlgoritmoCamino {

    private final Consumer<String> traza;
    protected Grafo grafo;
    protected Map<String, Double> distancia;
    private Map<String, String> previo;
    private int evaluaciones;
    private int aceptaciones;
    private int descartes;

    protected AlgoritmoCamino(Consumer<String> traza) {
        this.traza = traza;
    }

    public abstract String getNombre();

    /** Ejecuta la búsqueda; devuelve false si el resultado queda indefinido por un ciclo negativo. */
    protected abstract boolean buscar(String origen, String destino);

    /** Permite a cada algoritmo rechazar un grafo que no puede procesar. */
    protected boolean aceptaGrafo() {
        return true;
    }

    public final ResultadoRuta calcular(Grafo grafo, String origen, String destino) {
        this.grafo = grafo;
        String idOrigen = grafo.buscarVertice(origen).getId();
        String idDestino = grafo.buscarVertice(destino).getId();
        distancia = new LinkedHashMap<>();
        previo = new HashMap<>();
        evaluaciones = 0;
        aceptaciones = 0;
        descartes = 0;

        escribir("=== " + getNombre() + ": " + idOrigen + " -> " + idDestino + " ===");
        if (!aceptaGrafo()) {
            return resultado(idOrigen, idDestino, ResultadoRuta.Estado.PESOS_NEGATIVOS, List.of(), Double.NaN);
        }
        inicializar(idOrigen);
        if (!buscar(idOrigen, idDestino)) {
            return resultado(idOrigen, idDestino, ResultadoRuta.Estado.CICLO_NEGATIVO, List.of(), Double.NaN);
        }
        return concluir(idOrigen, idDestino);
    }

    private void inicializar(String origen) {
        for (Vertice vertice : grafo.getVertices()) {
            distancia.put(vertice.getId(), Double.POSITIVE_INFINITY);
        }
        distancia.put(origen, 0.0);
        escribir("[INICIALIZACIÓN] Origen en 0 y el resto en infinito:");
        escribirDistancias();
    }

    /** Aplica la condición dist[u] + peso < dist[v]; devuelve true si se aceptó el camino. */
    protected boolean relajar(Arista arista) {
        String u = arista.getOrigen();
        String v = arista.getDestino();
        double nuevo = distancia.get(u) + arista.getPeso();
        double actual = distancia.get(v);
        evaluaciones++;
        escribir("  Evalúa " + u + " -> " + v + " (peso " + formatear(arista.getPeso()) + "): dist[" + u + "] + peso = "
                + formatear(nuevo) + ", dist[" + v + "] = " + formatear(actual));
        if (nuevo < actual) {
            distancia.put(v, nuevo);
            previo.put(v, u);
            aceptaciones++;
            escribir("    [ACEPTADA] " + formatear(nuevo) + " < " + formatear(actual)
                    + ": dist[" + v + "] = " + formatear(nuevo) + ", previo[" + v + "] = " + u);
            return true;
        }
        descartes++;
        escribir("    [DESCARTADA] " + formatear(nuevo) + " >= " + formatear(actual) + ": se conserva dist[" + v + "]");
        return false;
    }

    private ResultadoRuta concluir(String origen, String destino) {
        double costo = distancia.get(destino);
        if (Double.isInfinite(costo)) {
            escribir("[RESULTADO FINAL] No existe ningún camino de " + origen + " a " + destino
                    + ": el destino es inaccesible.");
            return resultado(origen, destino, ResultadoRuta.Estado.SIN_CAMINO, List.of(), Double.POSITIVE_INFINITY);
        }
        List<String> ruta = new ArrayList<>();
        for (String actual = destino; actual != null; actual = previo.get(actual)) {
            ruta.add(actual);
        }
        Collections.reverse(ruta);
        escribir("[RESULTADO FINAL] Ruta más corta: " + String.join(" -> ", ruta));
        for (String nodo : ruta) {
            escribir("  " + nodo + " (costo acumulado " + formatear(distancia.get(nodo)) + ")");
        }
        escribir("  Costo total: " + formatear(costo));
        return resultado(origen, destino, ResultadoRuta.Estado.ENCONTRADA, List.copyOf(ruta), costo);
    }

    private ResultadoRuta resultado(String origen, String destino, ResultadoRuta.Estado estado,
            List<String> ruta, double costo) {
        ResultadoRuta r = new ResultadoRuta(getNombre(), origen, destino, estado, ruta, costo,
                evaluaciones, aceptaciones, descartes);
        escribir("Contadores: " + evaluaciones + " evaluaciones, " + aceptaciones + " aceptaciones, "
                + descartes + " descartes.\n");
        return r;
    }

    protected void escribir(String linea) {
        traza.accept(linea);
    }

    protected void escribirDistancias() {
        StringBuilder texto = new StringBuilder("  Distancias:");
        distancia.forEach((id, valor) -> texto.append(' ').append(id).append('=').append(formatear(valor)));
        escribir(texto.toString());
    }

    /** Muestra el infinito con su símbolo y elimina el ruido de redondeo de los decimales. */
    public static String formatear(double valor) {
        if (Double.isInfinite(valor)) {
            return "∞";
        }
        return Grafo.formatear(Math.abs(valor) < 1e12 ? Math.round(valor * 1e6) / 1e6 : valor);
    }
}
