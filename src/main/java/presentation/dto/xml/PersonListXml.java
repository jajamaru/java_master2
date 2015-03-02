/**
 * 
 */
package presentation.dto.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author romain
 *
 */
@XmlRootElement(name = "persons")
public class PersonListXml {

  private List<PersonXml> person;

  /**
   * @return the persons
   */
  @XmlElement(name = "person")
  public List<PersonXml> getPerson() {
    return person;
  }

  /**
   * @param persons the persons to set
   */
  public void setPerson(final List<PersonXml> person) {
    this.person = person;
  }

}
