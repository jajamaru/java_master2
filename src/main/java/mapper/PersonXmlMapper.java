/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.List;

import presentation.dto.PersonDto;
import presentation.dto.xml.FriendListXml;
import presentation.dto.xml.FriendXml;
import presentation.dto.xml.PersonListXml;
import presentation.dto.xml.PersonXml;

/**
 * @author romain
 *
 */
public class PersonXmlMapper {

  public static PersonListXml getPersonInXml(final List<? extends PersonDto> list) {
    final PersonListXml persons = new PersonListXml();
    final List<PersonXml> pList = new ArrayList<PersonXml>();
    for (PersonDto person : list) {
      final PersonXml xml = _createPersonXml(person);
      pList.add(xml);
    }
    persons.setPerson(pList);
    return persons;
  }

  private static PersonXml _createPersonXml(final PersonDto dto) {
    final PersonXml xml = new PersonXml();
    xml.setId(dto.getId());
    xml.setName(dto.getName());
    xml.setBirthday(dto.getBirthday());
    xml.setSexe(dto.getSexe());
    xml.setFriends(_createFriendListXml(dto));
    return xml;
  }

  private static FriendListXml _createFriendListXml(final PersonDto dto) {
    final FriendListXml friendList = new FriendListXml();
    friendList.setFriends(_getListFriendXml(dto.getFriends()));
    friendList.getFriends().addAll(_getListFriendXml(dto.getFriendsWith()));
    return friendList;
  }

  private static List<FriendXml> _getListFriendXml(final List<PersonDto> list) {
    final List<FriendXml> friendListXml = new ArrayList<FriendXml>();
    for (final PersonDto itDto : list) {
      final FriendXml friendXml = new FriendXml();
      friendXml.setId(itDto.getId());
      friendListXml.add(friendXml);
    }
    return friendListXml;
  }

}
