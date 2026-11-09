package Pruebas;

import BackEnd.Grafo;
import BackEnd.GrafoException;
import BackEnd.Vertice;
import java.util.List;

/**
 * Pruebas de funcionamiento de la actividad.
 * En NetBeans: clic derecho sobre este archivo > Run File.
 */
public class PruebasGrafo {

    private static int aprobadas;
    private static int fallidas;

    public static void main(String[] args) {
        Grafo g = new Grafo(true);

        seccion("1. Crear un grafo vacío");
        comprobar("El grafo nuevo está vacío", g.estaVacio() && g.numeroAristas() == 0);

        seccion("Operaciones sobre grafo vacío");
        rechaza("Buscar en grafo vacío", () -> g.buscarVertice("A"));
        rechaza("Eliminar en grafo vacío", () -> g.eliminarVertice("A"));
        rechaza("BFS en grafo vacío", () -> g.recorridoBFS("A"));
        rechaza("DFS en grafo vacío", () -> g.recorridoDFS("A"));
        rechaza("Matriz de grafo vacío", g::obtenerMatrizAdyacencia);
        rechaza("Conectividad de grafo vacío", g::evaluarConectividad);

        seccion("2. Insertar vértices con identificadores diferentes");
        for (String id : List.of("A", "B", "C", "D", "E")) {
            g.agregarVertice(ubicacion(id));
        }
        comprobar("Hay 5 ubicaciones", g.numeroVertices() == 5);

        seccion("3. Identificador duplicado");
        rechaza("Rechaza el id A repetido", () -> g.agregarVertice(ubicacion("A")));
        comprobar("El total sigue en 5", g.numeroVertices() == 5);

        seccion("4. Conectar con distintos pesos");
        g.agregarArista("A", "B", 5);
        g.agregarArista("A", "C", 2.5);
        g.agregarArista("B", "D", 7);
        g.agregarArista("C", "D", 1.25);
        g.agregarArista("D", "A", 3);
        comprobar("Hay 5 rutas", g.numeroAristas() == 5);

        seccion("5. Buscar vértices");
        comprobar("Encuentra B", "B".equals(g.buscarVertice("B").getId()));
        rechaza("No encuentra Z", () -> g.buscarVertice("Z"));

        seccion("7. Recorridos desde diferentes nodos");
        igual("BFS desde A", List.of("A", "B", "C", "D"), g.recorridoBFS("A"));
        igual("DFS desde A", List.of("A", "B", "D", "C"), g.recorridoDFS("A"));
        igual("BFS desde D", List.of("D", "A", "B", "C"), g.recorridoBFS("D"));
        igual("DFS desde C", List.of("C", "D", "A", "B"), g.recorridoDFS("C"));
        igual("BFS desde E aislado", List.of("E"), g.recorridoBFS("E"));

        seccion("8. Matriz y conectividad");
        double[][] m = g.obtenerMatrizAdyacencia();
        comprobar("Matriz[A][C] = 2.5", m[0][2] == 2.5);
        comprobar("Matriz[C][A] = 0 por ser dirigido", m[2][0] == 0);
        System.out.println(g.matrizComoTexto());
        comprobar("Con E aislado está desconectado",
                g.evaluarConectividad() == Grafo.Conectividad.DESCONECTADO);
        g.agregarArista("D", "E", 4);
        comprobar("E solo recibe rutas: débilmente conectado",
                g.evaluarConectividad() == Grafo.Conectividad.DEBILMENTE_CONECTADO);
        g.agregarArista("E", "A", 6);
        comprobar("Con E -> A: fuertemente conectado",
                g.evaluarConectividad() == Grafo.Conectividad.FUERTEMENTE_CONECTADO);

        seccion("6. Eliminar vértices y aristas");
        g.agregarVertice(ubicacion("F"));
        comprobar("F aislado elimina 0 rutas", g.eliminarVertice("F") == 0);
        comprobar("D elimina en cascada sus 4 rutas", g.eliminarVertice("D") == 4);
        comprobar("Quedan 3 rutas", g.numeroAristas() == 3);
        comprobar("Ninguna ruta menciona D", g.getAristas().stream()
                .noneMatch(a -> a.getOrigen().equals("D") || a.getDestino().equals("D")));
        g.eliminarArista("A", "B");
        comprobar("Eliminar arista A-B deja 2 rutas", g.numeroAristas() == 2);
        rechaza("Eliminar otra vez la arista A-B", () -> g.eliminarArista("A", "B"));
        rechaza("Eliminar un vértice inexistente", () -> g.eliminarVertice("D"));

        seccion("10. Entradas inválidas y campos vacíos");
        rechaza("Id vacío", () -> new Vertice(" ", "N", "T", "E", 1));
        rechaza("Id con espacios", () -> new Vertice("A 1", "N", "T", "E", 1));
        rechaza("Nombre vacío", () -> new Vertice("X", "", "T", "E", 1));
        rechaza("Tipo vacío", () -> new Vertice("X", "N", " ", "E", 1));
        rechaza("Estado vacío", () -> new Vertice("X", "N", "T", null, 1));
        rechaza("Capacidad negativa", () -> new Vertice("X", "N", "T", "E", -1));
        rechaza("Peso cero", () -> g.agregarArista("A", "B", 0));
        rechaza("Peso NaN", () -> g.agregarArista("A", "B", Double.NaN));
        rechaza("Peso infinito", () -> g.agregarArista("A", "B", Double.POSITIVE_INFINITY));
        rechaza("Arista con origen inexistente", () -> g.agregarArista("Z", "A", 1));
        rechaza("Arista con destino inexistente", () -> g.agregarArista("A", "Z", 1));
        rechaza("Arista de un nodo a sí mismo", () -> g.agregarArista("A", "A", 1));
        rechaza("Arista repetida", () -> g.agregarArista("A", "C", 9));

        seccion("9. Destruir el grafo");
        g.destruir();
        comprobar("Quedó vacío", g.estaVacio() && g.numeroVertices() == 0 && g.numeroAristas() == 0);

        seccion("Grafo no dirigido");
        Grafo n = new Grafo(false);
        for (String id : List.of("A", "B", "C")) {
            n.agregarVertice(ubicacion(id));
        }
        n.agregarArista("A", "B", 4);
        comprobar("La ruta cuenta una sola vez", n.numeroAristas() == 1);
        double[][] simetrica = n.obtenerMatrizAdyacencia();
        comprobar("La matriz es simétrica", simetrica[0][1] == 4 && simetrica[1][0] == 4);
        rechaza("B-A es la misma ruta que A-B", () -> n.agregarArista("B", "A", 8));
        comprobar("C aislado: desconectado", n.evaluarConectividad() == Grafo.Conectividad.DESCONECTADO);
        n.agregarArista("B", "C", 2);
        comprobar("Conexo equivale a fuerte",
                n.evaluarConectividad() == Grafo.Conectividad.FUERTEMENTE_CONECTADO);
        comprobar("Cascada cuenta 2 rutas no dirigidas", n.eliminarVertice("B") == 2);
        comprobar("No quedan rutas", n.numeroAristas() == 0);

        System.out.println("\nResultado: " + aprobadas + " aprobadas, " + fallidas + " fallidas.");
        if (fallidas > 0) {
            System.exit(1);
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

    private static void igual(String nombre, List<String> esperado, List<String> obtenido) {
        comprobar(nombre + ": " + obtenido, esperado.equals(obtenido));
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
