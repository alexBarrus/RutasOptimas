package BackEnd;

/** Error de validación o de una operación no permitida sobre el grafo. */
public class GrafoException extends RuntimeException {

    public GrafoException(String mensaje) {
        super(mensaje);
    }
}
