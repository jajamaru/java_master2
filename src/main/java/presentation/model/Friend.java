/**
 * 
 */
package presentation.model;

import javax.validation.Valid;

/**
 * @author romain
 *
 */
public class Friend {

  private boolean    friendShip;

  @Valid
  private PersonForm person;

  /**
   * @return the friendShip
   */
  public boolean isFriendShip() {
    return friendShip;
  }

  /**
   * @param friendShip the friendShip to set
   */
  public void setFriendShip(final boolean friendShip) {
    this.friendShip = friendShip;
  }

  /**
   * @return the person
   */
  public PersonForm getPerson() {
    return person;
  }

  /**
   * @param person the person to set
   */
  public void setPerson(final PersonForm person) {
    this.person = person;
  }

}
