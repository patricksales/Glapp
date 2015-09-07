package br.com.glapp.Controle.JPA.Exception;

/**
 *
 * @author Usuário
 */
public class DAOException extends Exception {

    private long errCode = 0;
    private String metodo;

    public long getErrCode() {
        return errCode;
    }

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, long errorCode) {
        super(message);
        this.errCode = errorCode;
    }

    public DAOException(String message, Throwable cause, long errorCode) {
        super(message, cause);
        this.errCode = errorCode;
    }

    public DAOException(String metodo, String message, Throwable cause) {
        super(message, cause);
        this.metodo = metodo;
    }

    public DAOException(String metodo, String message) {
        super(message);
        this.metodo = metodo;
    }

    public DAOException(String metodo, Throwable cause, String vazia) {
        super(cause);
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

}
