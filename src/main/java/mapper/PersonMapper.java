/**
 * 
 */
package mapper;

import presentation.dto.PersonDto;
import presentation.model.PersonForm;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
public class PersonMapper {

  /**
   * Convert a PersonDto object to PersonDo object
   * @param dto to convert
   * @return PersonDo object
   */
  public static PersonDo convertDtoToDo(final PersonDto dto) {
    final PersonDo person = new PersonDo();
    person.setId(dto.getId());
    person.setName(dto.getName());
    person.setBirthday(dto.getBirthday());
    return person;
  }

  /**
   * Convert a PersonForm object to PersonDto object
   * @param form to convert
   * @return PersonDto object
   */
  public static PersonDto convertFormToDto(final PersonForm form) {
    final PersonDto dto = new PersonDto();
    dto.setId(form.getId());
    dto.setName(form.getName());
    dto.setBirthday(form.getBirthday());
    return dto;
  }

}
