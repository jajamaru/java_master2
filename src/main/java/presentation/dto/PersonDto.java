/**
 * 
 */
package presentation.dto;

import java.util.Date;
import java.util.List;

/**
 * @author romain
 *
 */
public class PersonDto {

  private Integer         id;
  private String          name;
  private Date            birthday;
  private String          sexe;
  private List<PersonDto> friends;
  private List<PersonDto> persistFriends;
  private List<PersonDto> persistWithFriends;

  /**
   * @return the persistfriends
   */
  public List<PersonDto> getPersistfriends() {
    return persistFriends;
  }

  /**
   * @param persistfriends the persistfriends to set
   */
  public void setPersistFriends(final List<PersonDto> persistFriends) {
    this.persistFriends = persistFriends;
  }

  /**
   * @return the persistWithfriends
   */
  public List<PersonDto> getPersistWithfriends() {
    return persistWithFriends;
  }

  /**
   * @param persistWithfriends the persistWithfriends to set
   */
  public void setPersistWithFriends(final List<PersonDto> persistWithFriends) {
    this.persistWithFriends = persistWithFriends;
  }

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

}
