/**
 * 
 */
package presentation.model;

import java.util.List;

import javax.validation.Valid;

/**
 * @author romain
 *
 */
public class FriendForm {

  @Valid
  private PersonForm   person;

  @Valid
  private List<Friend> friends;

  /**
   * @return the friends
   */
  public List<Friend> getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public void setFriends(final List<Friend> friends) {
    this.friends = friends;
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
