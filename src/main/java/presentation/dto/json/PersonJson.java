/**
 * 
 */
package presentation.dto.json;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author romain
 *
 */
public class PersonJson {

  private Integer id;
  private String  name;
  private String  sexe;
  private Date    birthday;

  /**
   * @return the sexe
   */
  public String getSexe() {
    return sexe;
  }

  /**
   * @param sexe the sexe to set
   */
  public void setSexe(final String sexe) {
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
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

}
