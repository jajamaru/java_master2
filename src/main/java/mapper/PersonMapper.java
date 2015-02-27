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
  public static PersonDo convert(final PersonDto dto) {
    final PersonDo person = new PersonDo();
    person.setId(dto.getId());
    person.setName(dto.getName());
    person.setBirthday(dto.getBirthday());
    return person;
  }

}
