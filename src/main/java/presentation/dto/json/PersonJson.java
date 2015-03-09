/**
 * 
 */
package presentation.dto.json;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import entity.Sexe;

/**
 * @author romain
 *
 */
public class PersonJson {

  private Integer id;
  private String  name;
  private Sexe    sexe;
  private Date    birthday;
  private List<Integer> friends;

  /**
   * @return the friends
   */
  public final List<Integer> getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public final void setFriends(final List<Integer> friends) {
    this.friends = friends;
  }

  /**
   * @return the sexe
   */
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
  @JsonSerialize(using = JsonDateAdapter.class)
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
