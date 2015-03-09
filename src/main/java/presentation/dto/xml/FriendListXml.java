/**
 * 
 */
package presentation.dto.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author romain
 *
 */
public class FriendListXml {

  private List<FriendXml> friends;

  /**
   * @return the friends
   */
  @XmlElement(name = "friends")
  public final List<FriendXml> getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public final void setFriends(final List<FriendXml> friends) {
    this.friends = friends;
  }

}
