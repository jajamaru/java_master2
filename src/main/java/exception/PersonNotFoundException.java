/**
 * 
 */
package exception;

/**
 * @author romain
 *
 */
public class PersonNotFoundException extends RuntimeException {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6964871564931467107L;

  public PersonNotFoundException(final String message) {
    super(message);
  }

}
