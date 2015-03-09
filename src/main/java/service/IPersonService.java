/**
 * 
 */
package service;

import java.util.List;

import presentation.dto.PersonDto;
import exception.PersonNotFoundException;

/**
 * @author Romain
 * This interface use to communicate with PersonDo object.
 *
 */
public interface IPersonService {

  /**
   * @param person
   */
  void createPerson(final PersonDto person);

  /**
   * Delete a person
   * @param id of delete person
   * @return Number of deletion
   */
  int deletePerson(final int id);

  /**
   * Delete all person
   * @return Number of deletion
   */
  int deleteAllPerson();

  /**
   * Delete all friends of a person
   * @param person to delete friends
   * @return Number of deletion
   */
  int deleteAllFriends(final PersonDto person);

  /**
   * Delete a on friend to person
   * @param person Person to delete friend
   * @param deleted Person to remove from friend list
   * @return If deletion is well done
   */
  boolean deleteSingleFriend(final PersonDto person, final PersonDto deleted);

  /**
   * Update a person
   * @param person Person to update
   * @return the personDto merged
   */
  PersonDto updatePerson(final PersonDto person);

  /**
   * Retrieve a person by id
   * @param id Person's id
   * @return The person retrieved
   */
  PersonDto findPerson(final int id);

  /**
   * Retrieve all person
   * @return A list of person
   */
  List<PersonDto> findAllPerson();

}
