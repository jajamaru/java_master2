/**
 * 
 */
package presentation.dto.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * @author romain
 *
 */
public class JsonDateAdapter extends JsonSerializer<Date> {

  @Override
  public void serialize(final Date date, final JsonGenerator gen, final SerializerProvider ser)
      throws IOException, JsonProcessingException {
    final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    final String formattedDate = formatter.format(date);

    gen.writeString(formattedDate);
  }

}
