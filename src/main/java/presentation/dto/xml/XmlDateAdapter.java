/**
 * 
 */
package presentation.dto.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author romain
 *
 */
public class XmlDateAdapter extends XmlAdapter<String, Date> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  @Override
  public String marshal(final Date date) throws Exception {
    return dateFormat.format(date);
  }

  @Override
  public Date unmarshal(final String date) throws Exception {
    return dateFormat.parse(date);
  }

}
