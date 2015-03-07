/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.List;

import presentation.dto.PersonDto;
import presentation.dto.json.PersonJson;

/**
 * @author romain
 *
 */
public class PersonJsonMapper {

  public static PersonJson[] getPersonInJson(final List<? extends PersonDto> list) {
    final List<PersonJson> tab = new ArrayList<PersonJson>();
    for (PersonDto person : list) {
      final PersonJson json = new PersonJson();
      json.setId(person.getId());
      json.setName(person.getName());
      json.setBirthday(person.getBirthday());
      json.setSexe(person.getSexe());
      tab.add(json);
    }
    final PersonJson[] tabDto = new PersonJson[tab.size()];
    tab.toArray(tabDto);
    return tabDto;
  }

}
