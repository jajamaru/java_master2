/**
 * 
 */
package presentation.dto;

import java.util.Date;
import java.util.List;

import annotation.Pojo;
import entity.Sexe;

/**
 * @author romain
 *
 */
@Pojo
public class PersonDto {

  private Integer         id;
  private String          name;
  private Date            birthday;
  private Sexe            sexe;
  private List<PersonDto> friends;

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
   * @return the friends
   */
  public List<PersonDto> getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public void setFriends(final List<PersonDto> friends) {
    this.friends = friends;
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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final PersonDto other = (PersonDto) obj;
    if (birthday == null) {
      if (other.birthday != null) {
        return false;
      }
    } else if (!birthday.equals(other.birthday)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PersonDto [id=" + id + ", name=" + name + ", birthday=" + birthday + ", sexe=" + sexe
        + ", friends=" + ((friends != null) ? (friends.size()) : 0) + "]";
  }

}
