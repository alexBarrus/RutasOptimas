package BackEnd;

import java.util.List;

/** Resultado y contadores de una búsqueda de camino más corto. */
public record ResultadoRuta(
        String algoritmo,
        String origen,
        String destino,
        Estado estado,
        List<String> ruta,
        double costo,
        int evaluaciones,
        int aceptaciones,
        int descartes) {

    public enum Estado {
        ENCONTRADA("Ruta encontrada"),
        SIN_CAMINO("Destino inaccesible"),
        PESOS_NEGATIVOS("No aplicable: hay pesos negativos"),
        CICLO_NEGATIVO("Ciclo negativo: costo indefinido");

        private final String texto;

        Estado(String texto) {
            this.texto = texto;
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    public boolean encontrada() {
        return estado == Estado.ENCONTRADA;
    }

    public String costoTexto() {
        return encontrada() ? AlgoritmoCamino.formatear(costo) : "-";
    }

    public String resumen() {
        if (encontrada()) {
            return algoritmo + ": " + String.join(" -> ", ruta) + " | Costo total: " + costoTexto();
        }
        return algoritmo + ": " + origen + " -> " + destino + " | " + estado;
    }

    /** Arma la tabla comparativa de dos ejecuciones sobre el mismo grafo. */
    public static String comparar(ResultadoRuta a, ResultadoRuta b) {
        String fila = "%-22s%-20s%-20s%n";
        StringBuilder texto = new StringBuilder("=== COMPARATIVA DE DESEMPEÑO ===\n");
        texto.append(String.format(fila, "", a.algoritmo(), b.algoritmo()));
        texto.append(String.format(fila, "Costo", a.costoTexto(), b.costoTexto()));
        texto.append(String.format(fila, "Evaluaciones", a.evaluaciones(), b.evaluaciones()));
        texto.append(String.format(fila, "Aceptaciones", a.aceptaciones(), b.aceptaciones()));
        texto.append(String.format(fila, "Descartes", a.descartes(), b.descartes()));
        texto.append("Conclusión: ");
        if (a.encontrada() && b.encontrada()) {
            texto.append(Math.abs(a.costo() - b.costo()) < 1e-9
                    ? "ambos algoritmos llegaron al mismo costo óptimo."
                    : "los costos difieren.");
        } else if (a.estado() == b.estado()) {
            texto.append(a.estado()).append(" en ambos algoritmos.");
        } else {
            texto.append("no comparable (").append(a.estado()).append(" / ").append(b.estado()).append(").");
        }
        return texto.toString();
    }
}
