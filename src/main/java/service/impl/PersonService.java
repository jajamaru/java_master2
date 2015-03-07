/**
 * 
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;

import mapper.PersonMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import presentation.dto.PersonDto;
import service.IPersonService;
import dao.IPersonDao;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
@Service("personService")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonService implements IPersonService {

  private static Logger log = Logger.getLogger(PersonService.class);

  @Autowired
  @Qualifier("personDao")
  private IPersonDao    dao;

  /* (non-Javadoc)
   * @see service.IPersonService#createPerson(entity.PersonDo)
   */
  @Override
  public void createPerson(final PersonDto dto) {
    log.debug("createPerson - Creating person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    dao.create(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deletePerson(int)
   */
  @Override
  public int deletePerson(final int id) {
    log.debug("deletePerson - deleting person with id " + id);
    return dao.delete(id);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#updatePerson(entity.PersonDo)
   */
  @Override
  public void updatePerson(final PersonDto dto) {
    log.debug("updatePerson - Updating person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    dao.update(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findPerson(int)
   */
  @Override
  public PersonDto findPerson(final int id) {
    log.debug("findPerson - Retrieving person with id " + id);
    return PersonMapper.convertDotoDto(dao.find(id));

  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllPerson()
   */
  @Override
  public int deleteAllPerson() {
    log.debug("deleteAllPerson - Deleting all person");
    return dao.deleteAll();
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findAllPerson()
   */
  @Override
  public List<PersonDto> findAllPerson() {
    log.debug("findAllPerson - Finding all person");
    return PersonMapper.convertDotoDto(dao.findAll());
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllFriends(presentation.dto.PersonDto)
   */
  @Override
  public int deleteAllFriends(final PersonDto person) {
    final int nbFriendsDeleted = person.getFriends().size();
    log.debug("deleteAllFriends - deleting all friend to a " + person + ". " + nbFriendsDeleted
        + " deleted");
    person.setFriends(new ArrayList<PersonDto>());
    updatePerson(person);
    return nbFriendsDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteSingleFriend(presentation.dto.PersonDto, presentation.dto.PersonDto)
   */
  @Override
  public boolean deleteSingleFriend(final PersonDto person, final PersonDto deleted) {
    if (person.getFriends().contains(deleted)) {
      if (person.getFriends().remove(deleted)) {
        log.debug("UPDATE - " + person.toString());
        updatePerson(person);
      }
    }
    if (deleted.getFriends().contains(person)) {
      if (deleted.getFriends().remove(person)) {
        log.debug("UPDATE - " + deleted.toString());
        updatePerson(deleted);
      }
    }
    return true;
  }

}
