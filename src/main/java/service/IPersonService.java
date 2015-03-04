/**
 * 
 */
package service;

import java.util.List;

import presentation.dto.PersonDto;

/**
 * @author Romain
 *
 */
public interface IPersonService {

  void createPerson(final PersonDto person);

  int deletePerson(final int id);

  int deleteAllPerson();

  void updatePerson(final PersonDto person);

  PersonDto findPerson(final int id);

  List<PersonDto> findAllPerson();

}
