/**
 * 
 */
package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author romain
 *
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(name = "PersonDo.find", query = "SELECT p from PersonDo p where p.id = :id"),
    @NamedQuery(name = "PersonDo.findAll", query = "SELECT p FROM PersonDo p"),
    @NamedQuery(name = "PersonDo.delete", query = "DELETE FROM PersonDo WHERE id = :id"),
    @NamedQuery(name = "PersonDo.deleteAll", query = "DELETE FROM PersonDo") })
public class PersonDo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer        id;

  @Column(name = "name", nullable = false)
  private String         name;

  @Temporal(TemporalType.DATE)
  @Column(name = "birthday")
  private Date           birthday;

  @JoinTable(name = "friend", joinColumns = { @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "idFriend", referencedColumnName = "id", nullable = false) })
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
  private List<PersonDo> friends;

  @ManyToMany(mappedBy = "friends", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
      CascadeType.REFRESH })
  private List<PersonDo> friendsWith;

  /**
   * @return the friendsWith
   */
  public List<PersonDo> getFriendsWith() {
    return friendsWith;
  }

  /**
   * @param friendsWith the friendsWith to set
   */
  public void setFriendsWith(final List<PersonDo> friendsWith) {
    this.friendsWith = friendsWith;
  }

  /**
   * @return the friends
   */
  public List<PersonDo> getFriends() {
    return friends;
  }

  /**
   * @param friends the friends to set
   */
  public void setFriends(final List<PersonDo> friends) {
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
    final PersonDo other = (PersonDo) obj;
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
