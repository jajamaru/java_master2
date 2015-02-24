/**
 * 
 */
package presentation.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Romain
 *
 */
public class PersonForm {

  @NotNull(message = "person.name.error.notnull")
  @Size(min = 2, message = "person.name.error.size")
  private String name;

  @NotNull(message = "person.date.error.notnull")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Past(message = "person.date.error.past")
  private Date   birthday;

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
