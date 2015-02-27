/**
 * 
 */
package service;

import java.util.List;

import presentation.dto.PersonDto;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
public interface IPersonService {
  
  void createPerson(final PersonDto person);
  
  int deletePerson(final int id);
  
  int deleteAllPerson();
  
  void updatePerson(final PersonDto person);
  
  PersonDo findPerson(final int id);
  
  List<? extends PersonDo> findAllPerson();

}
