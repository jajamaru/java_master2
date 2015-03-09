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
      final PersonJson json = _createPersonJson(person);
      tab.add(json);
    }
    final PersonJson[] tabDto = new PersonJson[tab.size()];
    tab.toArray(tabDto);
    return tabDto;
  }

  private static PersonJson _createPersonJson(final PersonDto dto) {
    final PersonJson json = new PersonJson();
    json.setId(dto.getId());
    json.setName(dto.getName());
    json.setBirthday(dto.getBirthday());
    json.setSexe(dto.getSexe());
    json.setFriends(_getIdFriends(dto.getFriends()));
    json.getFriends().addAll(_getIdFriends(dto.getFriendsWith()));
    return json;
  }

  private static List<Integer> _getIdFriends(final List<PersonDto> list) {
    final List<Integer> ids = new ArrayList<Integer>();
    for (final PersonDto itDto : list) {
      ids.add(itDto.getId());
    }
    return ids;
  }

}
