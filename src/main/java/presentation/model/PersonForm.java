/**
 * 
 */
package presentation.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Romain
 *
 */
public class PersonForm {

  private Integer id;

  @NotNull
  @NotEmpty
  @Size(min = 2)
  private String  name;

  @NotNull
  private String  sexe;

  @NotNull
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Past
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
