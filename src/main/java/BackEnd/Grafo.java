package BackEnd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Grafo dirigido o no dirigido implementado con listas de adyacencia.
 * En un grafo no dirigido cada ruta se guarda en ambos sentidos.
 * Los mapas conservan el orden de inserción, así los recorridos son predecibles.
 */
public class Grafo {

    public enum Conectividad {
        FUERTEMENTE_CONECTADO("Fuertemente conectado"),
        DEBILMENTE_CONECTADO("Débilmente conectado"),
        DESCONECTADO("Desconectado");

        private final String texto;

        Conectividad(String texto) {
            this.texto = texto;
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    private final boolean dirigido;
    private final Map<String, Vertice> vertices = new LinkedHashMap<>();
    private final Map<String, List<Arista>> adyacencia = new LinkedHashMap<>();

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public boolean esDirigido() {
        return dirigido;
    }

    public boolean estaVacio() {
        return vertices.isEmpty();
    }

    public int numeroVertices() {
        return vertices.size();
    }

    public int numeroAristas() {
        return getAristas().size();
    }

    public List<Vertice> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    /** Devuelve las rutas del grafo; en uno no dirigido, cada ruta aparece una sola vez. */
    public List<Arista> getAristas() {
        List<Arista> resultado = new ArrayList<>();
        Set<String> procesados = new HashSet<>();
        for (Map.Entry<String, List<Arista>> entrada : adyacencia.entrySet()) {
            for (Arista arista : entrada.getValue()) {
                if (dirigido || !procesados.contains(arista.getDestino())) {
                    resultado.add(arista);
                }
            }
            procesados.add(entrada.getKey());
        }
        return resultado;
    }

    /** Rutas que salen de una ubicación, en orden de inserción. */
    public List<Arista> getRutasDesde(String id) {
        return new ArrayList<>(adyacencia.get(exigirVertice(id)));
    }

    public void agregarVertice(Vertice vertice) {
        if (vertice == null) {
            throw new GrafoException("La ubicación no puede ser nula.");
        }
        if (vertices.containsKey(vertice.getId())) {
            throw new GrafoException("Ya existe una ubicación con el identificador '" + vertice.getId() + "'.");
        }
        vertices.put(vertice.getId(), vertice);
        adyacencia.put(vertice.getId(), new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, double peso) {
        String idOrigen = exigirVertice(origen);
        String idDestino = exigirVertice(destino);
        if (idOrigen.equals(idDestino)) {
            throw new GrafoException("Una ruta debe conectar dos ubicaciones distintas.");
        }
        if (buscarArista(idOrigen, idDestino) != null) {
            throw new GrafoException("Ya existe una ruta entre '" + idOrigen + "' y '" + idDestino + "'.");
        }
        adyacencia.get(idOrigen).add(new Arista(idOrigen, idDestino, peso));
        if (!dirigido) {
            adyacencia.get(idDestino).add(new Arista(idDestino, idOrigen, peso));
        }
    }

    /** Elimina la ubicación y sus rutas. Devuelve cuántas rutas se eliminaron en cascada. */
    public int eliminarVertice(String id) {
        String clave = exigirVertice(id);
        int eliminadas = adyacencia.remove(clave).size();
        vertices.remove(clave);
        for (List<Arista> rutas : adyacencia.values()) {
            int antes = rutas.size();
            rutas.removeIf(arista -> arista.getDestino().equals(clave));
            if (dirigido) {
                eliminadas += antes - rutas.size();
            }
        }
        return eliminadas;
    }

    public void eliminarArista(String origen, String destino) {
        String idOrigen = exigirVertice(origen);
        String idDestino = exigirVertice(destino);
        Arista arista = buscarArista(idOrigen, idDestino);
        if (arista == null) {
            throw new GrafoException("No existe una ruta de '" + idOrigen + "' a '" + idDestino + "'.");
        }
        adyacencia.get(idOrigen).remove(arista);
        if (!dirigido) {
            adyacencia.get(idDestino).remove(buscarArista(idDestino, idOrigen));
        }
    }

    public Vertice buscarVertice(String id) {
        return vertices.get(exigirVertice(id));
    }

    public List<String> recorridoBFS(String inicio) {
        String origen = exigirVertice(inicio);
        List<String> secuencia = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new ArrayDeque<>();
        visitados.add(origen);
        cola.add(origen);
        while (!cola.isEmpty()) {
            String actual = cola.remove();
            secuencia.add(actual);
            for (Arista arista : adyacencia.get(actual)) {
                if (visitados.add(arista.getDestino())) {
                    cola.add(arista.getDestino());
                }
            }
        }
        return secuencia;
    }

    public List<String> recorridoDFS(String inicio) {
        String origen = exigirVertice(inicio);
        List<String> secuencia = new ArrayList<>();
        visitarDFS(origen, new HashSet<>(), secuencia);
        return secuencia;
    }

    private void visitarDFS(String actual, Set<String> visitados, List<String> secuencia) {
        visitados.add(actual);
        secuencia.add(actual);
        for (Arista arista : adyacencia.get(actual)) {
            if (!visitados.contains(arista.getDestino())) {
                visitarDFS(arista.getDestino(), visitados, secuencia);
            }
        }
    }

    /** Matriz de pesos en el orden de getVertices(); 0 significa que no hay ruta. */
    public double[][] obtenerMatrizAdyacencia() {
        exigirNoVacio();
        List<String> ids = new ArrayList<>(vertices.keySet());
        double[][] matriz = new double[ids.size()][ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            for (Arista arista : adyacencia.get(ids.get(i))) {
                matriz[i][ids.indexOf(arista.getDestino())] = arista.getPeso();
            }
        }
        return matriz;
    }

    /** Presenta la matriz de adyacencia como una tabla de columnas alineadas. */
    public String matrizComoTexto() {
        double[][] matriz = obtenerMatrizAdyacencia();
        List<String> ids = new ArrayList<>(vertices.keySet());
        int ancho = 1;
        for (int i = 0; i < ids.size(); i++) {
            ancho = Math.max(ancho, ids.get(i).length());
            for (double peso : matriz[i]) {
                ancho = Math.max(ancho, formatear(peso).length());
            }
        }
        String celda = "%" + (ancho + 2) + "s";
        StringBuilder texto = new StringBuilder(String.format(celda, ""));
        for (String id : ids) {
            texto.append(String.format(celda, id));
        }
        for (int i = 0; i < ids.size(); i++) {
            texto.append('\n').append(String.format(celda, ids.get(i)));
            for (double peso : matriz[i]) {
                texto.append(String.format(celda, formatear(peso)));
            }
        }
        return texto.toString();
    }

    public Conectividad evaluarConectividad() {
        exigirNoVacio();
        String inicio = vertices.keySet().iterator().next();
        if (alcanzables(inicio, true, false).size() == vertices.size()
                && alcanzables(inicio, false, true).size() == vertices.size()) {
            return Conectividad.FUERTEMENTE_CONECTADO;
        }
        if (alcanzables(inicio, true, true).size() == vertices.size()) {
            return Conectividad.DEBILMENTE_CONECTADO;
        }
        return Conectividad.DESCONECTADO;
    }

    /** Ubicaciones alcanzables desde inicio siguiendo rutas directas, inversas o ambas. */
    private Set<String> alcanzables(String inicio, boolean directas, boolean inversas) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new ArrayDeque<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.remove();
            for (Map.Entry<String, List<Arista>> entrada : adyacencia.entrySet()) {
                for (Arista arista : entrada.getValue()) {
                    if (directas && arista.getOrigen().equals(actual) && visitados.add(arista.getDestino())) {
                        cola.add(arista.getDestino());
                    }
                    if (inversas && arista.getDestino().equals(actual) && visitados.add(arista.getOrigen())) {
                        cola.add(arista.getOrigen());
                    }
                }
            }
        }
        return visitados;
    }

    public void destruir() {
        vertices.clear();
        adyacencia.clear();
    }

    /** Lista de adyacencia en texto, para ver la estructura del grafo. */
    public String estructuraComoTexto() {
        exigirNoVacio();
        StringBuilder texto = new StringBuilder();
        for (Map.Entry<String, List<Arista>> entrada : adyacencia.entrySet()) {
            texto.append(entrada.getKey()).append(" ->");
            if (entrada.getValue().isEmpty()) {
                texto.append(" (sin rutas)");
            }
            for (Arista arista : entrada.getValue()) {
                texto.append(' ').append(arista.getDestino())
                        .append('(').append(formatear(arista.getPeso())).append(')');
            }
            texto.append('\n');
        }
        return texto.toString().stripTrailing();
    }

    /** Escribe un número sin el ".0" final cuando es entero. */
    public static String formatear(double valor) {
        String texto = String.valueOf(valor);
        return texto.endsWith(".0") ? texto.substring(0, texto.length() - 2) : texto;
    }

    private Arista buscarArista(String origen, String destino) {
        for (Arista arista : adyacencia.get(origen)) {
            if (arista.getDestino().equals(destino)) {
                return arista;
            }
        }
        return null;
    }

    private void exigirNoVacio() {
        if (vertices.isEmpty()) {
            throw new GrafoException("El grafo está vacío.");
        }
    }

    /** Valida que el grafo tenga datos y que la ubicación exista; devuelve su id normalizado. */
    private String exigirVertice(String id) {
        exigirNoVacio();
        String clave = Vertice.validarId(id);
        if (!vertices.containsKey(clave)) {
            throw new GrafoException("No existe la ubicación '" + clave + "'.");
        }
        return clave;
    }
}
