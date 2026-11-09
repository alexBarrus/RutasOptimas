package BackEnd;

/** Ubicación (nodo) del grafo. */
public class Vertice {

    private final String id;
    private final String nombre;
    private final String tipo;
    private final String estado;
    private final double capacidad;

    public Vertice(String id, String nombre, String tipo, String estado, double capacidad) {
        this.id = validarId(id);
        this.nombre = validarTexto(nombre, "nombre");
        this.tipo = validarTexto(tipo, "tipo");
        this.estado = validarTexto(estado, "estado");
        if (!Double.isFinite(capacidad) || capacidad < 0) {
            throw new GrafoException("La capacidad debe ser un número finito mayor o igual a 0.");
        }
        this.capacidad = capacidad;
    }

    /** Un identificador válido es un código de letras, dígitos, guion o guion bajo. */
    public static String validarId(String id) {
        if (id == null || id.isBlank()) {
            throw new GrafoException("El identificador es obligatorio.");
        }
        String limpio = id.trim();
        if (!limpio.matches("[A-Za-z0-9_-]{1,20}")) {
            throw new GrafoException("El identificador '" + limpio
                    + "' no es válido: use hasta 20 letras, dígitos, '-' o '_' (sin espacios ni acentos).");
        }
        return limpio;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new GrafoException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public double getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Tipo: " + tipo
                + " | Estado: " + estado
                + " | Capacidad: " + Grafo.formatear(capacidad);
    }
}
