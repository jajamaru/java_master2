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
@DiscriminatorValue("H")
public class HommeDo extends PersonDo {

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "HommeDo [getSexe()=" + getSexe() + ", getFriendsWith()=" + getFriendsWith()
        + ", getFriends()=" + getFriends() + ", getId()=" + getId() + ", getName()=" + getName()
        + ", getBirthday()=" + getBirthday() + ", hashCode()=" + hashCode() + ", getClass()="
        + getClass() + ", toString()=" + super.toString() + "]";
  }

}
