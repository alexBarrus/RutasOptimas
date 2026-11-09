package BackEnd;

/** Ruta entre dos ubicaciones, identificadas por su id. */
public class Arista {

    private final String origen;
    private final String destino;
    private final double peso;

    public Arista(String origen, String destino, double peso) {
        if (!Double.isFinite(peso) || peso == 0) {
            throw new GrafoException("El peso debe ser un número finito y distinto de 0.");
        }
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return origen + " -> " + destino + " (peso " + Grafo.formatear(peso) + ")";
    }
}
