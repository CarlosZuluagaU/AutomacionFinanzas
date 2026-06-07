package co.edu.udea.fabrica.finanzas.exceptions;

public class FinanzasException extends RuntimeException {

    public FinanzasException(String message) {
        super(message);
    }

    public FinanzasException(String message, Throwable cause) {
        super(message, cause);
    }
}
