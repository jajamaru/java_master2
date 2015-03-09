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
  
  private PersonXmlMapper() {
    //empty constructor
  }

  public static PersonListXml getPersonInXml(final List<? extends PersonDto> list) {
    final PersonListXml persons = new PersonListXml();
    final List<PersonXml> pList = new ArrayList<PersonXml>();
    for (PersonDto person : list) {
      final PersonXml xml = createPersonXml(person);
      pList.add(xml);
    }
    persons.setPerson(pList);
    return persons;
  }

  private static PersonXml createPersonXml(final PersonDto dto) {
    final PersonXml xml = new PersonXml();
    xml.setId(dto.getId());
    xml.setName(dto.getName());
    xml.setBirthday(dto.getBirthday());
    xml.setSexe(dto.getSexe());
    xml.setFriends(createFriendListXml(dto));
    return xml;
  }

  private static FriendListXml createFriendListXml(final PersonDto dto) {
    final FriendListXml friendList = new FriendListXml();
    friendList.setFriends(getListFriendXml(dto.getFriends()));
    friendList.getFriends().addAll(getListFriendXml(dto.getFriendsWith()));
    return friendList;
  }

  private static List<FriendXml> getListFriendXml(final List<PersonDto> list) {
    final List<FriendXml> friendListXml = new ArrayList<FriendXml>();
    for (final PersonDto itDto : list) {
      final FriendXml friendXml = new FriendXml();
      friendXml.setId(itDto.getId());
      friendListXml.add(friendXml);
    }
    return friendListXml;
  }

}
