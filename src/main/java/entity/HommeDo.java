/**
 * 
 */
package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Romain
 *
 */
@Entity
@DiscriminatorValue(Sexe.Values.HOMME)
public class HommeDo extends PersonDo {

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "HommeDo [ " + super.toString() + " ]";
  }

}
