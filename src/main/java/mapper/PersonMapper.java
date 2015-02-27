/**
 * 
 */
package mapper;

import presentation.dto.PersonDto;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
public class PersonMapper {

  /**
   * @param dto
   * @return
   */
  public static PersonDo convert(PersonDto dto) {
    final PersonDo person = new PersonDo();
    person.setName(dto.getName());
    person.setBirthday(dto.getBirthday());
    return person;
  }

}
