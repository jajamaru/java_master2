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
@DiscriminatorValue(Sexe.Values.FEMME)
public class FemmeDo extends PersonDo {

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "FemmeDo [ " + super.toString() + " ]";
  }

}
