package Pruebas;

import BackEnd.AlgoritmoCamino;
import BackEnd.BellmanFord;
import BackEnd.Dijkstra;
import BackEnd.Grafo;
import BackEnd.GrafoException;
import BackEnd.ResultadoRuta;
import BackEnd.Vertice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Pruebas del módulo de optimización.
 * En NetBeans: clic derecho sobre este archivo > Run File.
 */
public class PruebasRutas {

    private static int aprobadas;
    private static int fallidas;

    public static void main(String[] args) {
        Grafo red = crearRed();

        seccion("1. Vecinos directos");
        ResultadoRuta vecinos = new Dijkstra(l -> { }).calcular(red, "A", "C");
        comprobar("A -> C cuesta 1", vecinos.costo() == 1 && vecinos.ruta().equals(List.of("A", "C")));

        seccion("2. Nodos distantes con intermedios");
        for (AlgoritmoCamino algoritmo : List.of(new Dijkstra(l -> { }), new BellmanFord(l -> { }))) {
            ResultadoRuta r = algoritmo.calcular(red, "A", "E");
            comprobar(r.resumen(), r.costo() == 11 && r.ruta().equals(List.of("A", "C", "B", "D", "E")));
        }

        seccion("3. La traza muestra aceptación y descarte");
        List<String> traza = new ArrayList<>();
        ResultadoRuta conTraza = new Dijkstra(traza::add).calcular(red, "A", "E");
        String texto = String.join("\n", traza);
        System.out.println(texto);
        comprobar("Incluye inicialización con infinito", texto.contains("[INICIALIZACIÓN]") && texto.contains("∞"));
        comprobar("Incluye nodos extraídos", texto.contains("[PASO 1] Extrae A"));
        comprobar("Acepta C -> B por mejorar 4", texto.contains("[ACEPTADA] 3 < 4"));
        comprobar("Descarta B -> C por costar más", texto.contains("[DESCARTADA] 12 >= 1"));
        comprobar("Incluye el resultado final", texto.contains("[RESULTADO FINAL] Ruta más corta: A -> C -> B -> D -> E"));
        comprobar("Hay aceptaciones y descartes contados", conTraza.aceptaciones() > 0 && conTraza.descartes() > 0);

        seccion("4. Nodos no conectados");
        for (AlgoritmoCamino algoritmo : List.of(new Dijkstra(l -> { }), new BellmanFord(l -> { }))) {
            List<String> lineas = new ArrayList<>();
            AlgoritmoCamino conLineas = algoritmo instanceof Dijkstra ? new Dijkstra(lineas::add) : new BellmanFord(lineas::add);
            ResultadoRuta r = conLineas.calcular(red, "A", "F");
            comprobar(r.resumen(), r.estado() == ResultadoRuta.Estado.SIN_CAMINO
                    && String.join("\n", lineas).contains("inaccesible"));
        }

        seccion("5. Ambos algoritmos llegan al mismo costo");
        ResultadoRuta d = new Dijkstra(l -> { }).calcular(red, "A", "E");
        ResultadoRuta b = new BellmanFord(l -> { }).calcular(red, "A", "E");
        System.out.println(ResultadoRuta.comparar(d, b));
        comprobar("Mismo costo en la red de ejemplo", d.costo() == b.costo());
        comprobar("La comparativa lo concluye", ResultadoRuta.comparar(d, b).contains("mismo costo óptimo"));
        comprobar("Mismo costo en 300 redes aleatorias", redesAleatoriasCoinciden(300));

        seccion("6. Validaciones");
        rechaza("Origen inexistente", () -> new Dijkstra(l -> { }).calcular(red, "Z", "A"));
        rechaza("Destino inexistente", () -> new BellmanFord(l -> { }).calcular(red, "A", "Z"));
        rechaza("Grafo vacío", () -> new Dijkstra(l -> { }).calcular(new Grafo(true), "A", "B"));
        ResultadoRuta mismo = new Dijkstra(l -> { }).calcular(red, "B", "B");
        comprobar("Origen igual a destino cuesta 0", mismo.costo() == 0 && mismo.ruta().equals(List.of("B")));

        Grafo negativo = new Grafo(true);
        agregarNodos(negativo, "X", "Y", "Z");
        negativo.agregarArista("X", "Y", 4);
        negativo.agregarArista("X", "Z", 5);
        negativo.agregarArista("Z", "Y", -3);
        comprobar("Dijkstra rechaza pesos negativos",
                new Dijkstra(l -> { }).calcular(negativo, "X", "Y").estado() == ResultadoRuta.Estado.PESOS_NEGATIVOS);
        ResultadoRuta conNegativos = new BellmanFord(l -> { }).calcular(negativo, "X", "Y");
        comprobar("Bellman-Ford resuelve el peso negativo con costo 2",
                conNegativos.costo() == 2 && conNegativos.ruta().equals(List.of("X", "Z", "Y")));

        Grafo ciclo = new Grafo(true);
        agregarNodos(ciclo, "P", "Q", "R");
        ciclo.agregarArista("P", "Q", 1);
        ciclo.agregarArista("Q", "R", 1);
        ciclo.agregarArista("R", "Q", -3);
        comprobar("Bellman-Ford detecta el ciclo negativo",
                new BellmanFord(l -> { }).calcular(ciclo, "P", "R").estado() == ResultadoRuta.Estado.CICLO_NEGATIVO);

        seccion("7. Consultas continuas sobre la misma red");
        AlgoritmoCamino reutilizado = new Dijkstra(l -> { });
        ResultadoRuta primera = reutilizado.calcular(red, "A", "E");
        reutilizado.calcular(red, "C", "D");
        ResultadoRuta tercera = reutilizado.calcular(red, "A", "E");
        comprobar("Repetir la consulta da lo mismo", primera.equals(tercera));

        seccion("8. Grafo no dirigido");
        Grafo noDirigido = new Grafo(false);
        agregarNodos(noDirigido, "A", "B", "C");
        noDirigido.agregarArista("A", "B", 2);
        noDirigido.agregarArista("B", "C", 3);
        comprobar("Recorre las rutas en sentido inverso",
                new Dijkstra(l -> { }).calcular(noDirigido, "C", "A").costo() == 5
                && new BellmanFord(l -> { }).calcular(noDirigido, "C", "A").costo() == 5);

        System.out.println("\nResultado: " + aprobadas + " aprobadas, " + fallidas + " fallidas.");
        if (fallidas > 0) {
            System.exit(1);
        }
    }

    private static Grafo crearRed() {
        Grafo red = new Grafo(true);
        agregarNodos(red, "A", "B", "C", "D", "E", "F");
        red.agregarArista("A", "B", 4);
        red.agregarArista("A", "C", 1);
        red.agregarArista("C", "B", 2);
        red.agregarArista("B", "C", 9);
        red.agregarArista("B", "D", 5);
        red.agregarArista("C", "D", 8);
        red.agregarArista("D", "E", 3);
        return red;
    }

    private static boolean redesAleatoriasCoinciden(int cantidad) {
        Random azar = new Random(2026);
        for (int n = 0; n < cantidad; n++) {
            Grafo g = new Grafo(azar.nextBoolean());
            int nodos = 2 + azar.nextInt(12);
            for (int i = 0; i < nodos; i++) {
                g.agregarVertice(ubicacion("N" + i));
            }
            for (int i = 0; i < nodos * 3; i++) {
                try {
                    g.agregarArista("N" + azar.nextInt(nodos), "N" + azar.nextInt(nodos), 1 + azar.nextInt(50));
                } catch (GrafoException repetida) {
                    // Las rutas repetidas o de un nodo a sí mismo simplemente no se agregan.
                }
            }
            ResultadoRuta d = new Dijkstra(l -> { }).calcular(g, "N0", "N" + (nodos - 1));
            ResultadoRuta b = new BellmanFord(l -> { }).calcular(g, "N0", "N" + (nodos - 1));
            if (d.estado() != b.estado() || (d.encontrada() && Math.abs(d.costo() - b.costo()) > 1e-9)) {
                return false;
            }
        }
        return true;
    }

    private static void agregarNodos(Grafo g, String... ids) {
        for (String id : ids) {
            g.agregarVertice(ubicacion(id));
        }
    }

    private static Vertice ubicacion(String id) {
        return new Vertice(id, "Ubicación " + id, "Terminal", "Zona Centro", 100);
    }

    private static void seccion(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
    }

    private static void comprobar(String nombre, boolean condicion) {
        if (condicion) {
            aprobadas++;
        } else {
            fallidas++;
        }
        System.out.println((condicion ? "[PASA] " : "[FALLA] ") + nombre);
    }

    private static void rechaza(String nombre, Runnable operacion) {
        try {
            operacion.run();
            comprobar(nombre + " (no se rechazó)", false);
        } catch (GrafoException ex) {
            comprobar(nombre + " -> " + ex.getMessage(), true);
        }
    }
}
