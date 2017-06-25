package business.dao;

/**
 *
 * @author Kalicki
 */
public class DAOException extends Exception {

  public DAOException() {}

  public DAOException(String msg) {
    super(msg);
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }
}
