/**
 * 
 */
package presentation.dto.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author romain
 *
 */
public class FriendXml {
  
  private Integer id;

  /**
   * @return the id
   */
  @XmlAttribute
  public final Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public final void setId(final Integer id) {
    this.id = id;
  }
  

}
