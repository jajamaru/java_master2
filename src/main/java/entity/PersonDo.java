/**
 * 
 */
package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "PersonDo.find", query = "Select p from PersonDo p where p.id = :id"),
    @NamedQuery(name = "PersonDo.findAll", query = "SELECT p FROM PersonDo p"),
    @NamedQuery(name = "PersonDo.delete", query = "DELETE FROM PersonDo WHERE id = :id"),
    @NamedQuery(name = "PersonDo.deleteAll", query = "DELETE FROM PersonDo")
 })
public class PersonDo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int    id;

  @Column(name = "name")
  private String name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "birthday")
  private Date   birthday;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final int id) {
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