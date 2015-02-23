/**
 * 
 */
package service;

import java.util.List;

import entity.PersonDo;

/**
 * @author Romain
 *
 */
public interface IPersonService {
  
  void createPerson(final PersonDo person);
  
  int deletePerson(final int id);
  
  int deleteAllPerson();
  
  void updatePerson(final PersonDo person);
  
  PersonDo findPerson(final int id);
  
  List<? extends PersonDo> findAllPerson();

}
