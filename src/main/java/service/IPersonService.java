/**
 * 
 */
package service;

import java.util.List;

import presentation.dto.PersonDto;
import entity.PersonDo;
import exception.EntityNotFoundException;

/**
 * @author Romain
 *
 */
public interface IPersonService {

  void createPerson(final PersonDto person);

  int deletePerson(final int id) throws EntityNotFoundException;

  int deleteAllPerson();

  void updatePerson(final PersonDto person);

  PersonDo findPerson(final int id) throws EntityNotFoundException;

  List<? extends PersonDo> findAllPerson();

  List<? extends PersonDo> findFriends(final Integer id) throws EntityNotFoundException;

}
