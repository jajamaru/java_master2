/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.List;

import presentation.dto.PersonDto;
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
      final PersonXml xml = new PersonXml();
      xml.setId(person.getId());
      xml.setName(person.getName());
      xml.setBirthday(person.getBirthday());
      xml.setSexe(person.getSexe());
      pList.add(xml);
    }
    persons.setPerson(pList);
    return persons;
  }

}
