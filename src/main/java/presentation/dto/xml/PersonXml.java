/**
 * 
 */
package presentation.dto.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entity.Sexe;

/**
 * @author romain
 *
 */
public class PersonXml {

  private Integer       id;

  private String        name;

  private Sexe          sexe;

  private Date          birthday;

  private FriendListXml friends;

  /**
   * @return the friends
   */
  @XmlElement(name = "friends")
  public final FriendListXml getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public final void setFriends(final FriendListXml friends) {
    this.friends = friends;
  }

  /**
   * @return the sexe
   */
  @XmlAttribute
  public Sexe getSexe() {
    return sexe;
  }

  /**
   * @param sexe the sexe to set
   */
  public void setSexe(final Sexe sexe) {
    this.sexe = sexe;
  }

  /**
   * @return the id
   */
  @XmlAttribute
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  @XmlElement
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * @return the birthday
   */
  @XmlElement
  @XmlJavaTypeAdapter(XmlDateAdapter.class)
  public Date getBirthday() {
    return birthday;
  }

  /**
   * @param birthday the birthday to set
   */
  public void setBirthday(final Date birthday) {
    this.birthday = birthday;
  }

}
