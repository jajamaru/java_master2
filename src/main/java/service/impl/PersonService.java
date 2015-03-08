/**
 * 
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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
import exception.PersonNotFoundException;

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
    log.debug("Creating a person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    dao.create(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deletePerson(int)
   */
  @Override
  public int deletePerson(final int id) throws PersonNotFoundException {
    log.debug("Deleting a person with id " + id);
    final int nbDeleted = dao.delete(id);
    if (nbDeleted > 0) {
      log.debug("Person deleted [ nb row deleted " + nbDeleted + " ]");
    } else {
      log.debug("No person deleted with id " + id);
      throw new PersonNotFoundException("PersonDo object with id " + id
          + " does not exists or he's aldready deleted !");
    }
    return nbDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#updatePerson(entity.PersonDo)
   */
  @Override
  public PersonDto updatePerson(final PersonDto dto) throws PersonNotFoundException {
    log.debug("Updating a person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    try {
      final PersonDto dtoMerged = PersonMapper.convertDotoDto(dao.update(personDo));
      log.debug("Person updated " + dtoMerged);
      return dtoMerged;
    } catch (final IllegalArgumentException e) {
      log.error("Empty entity or entity was already deleted before the merging", e);
      throw new PersonNotFoundException(
          "Empty entity or entity was already deleted before the merging");
    }
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findPerson(int)
   */
  @Override
  public PersonDto findPerson(final int id) throws PersonNotFoundException {
    log.debug("Retrieving a person with id " + id);
    try {
      final PersonDo personDo = dao.find(id);
      log.debug("Person retrieved " + personDo);
      return PersonMapper.convertDotoDto(personDo);
    } catch (final NoResultException e) {
      log.error("No person found with id " + id, e);
      throw new PersonNotFoundException("PersonDo object does not found with id " + id);
    }

  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllPerson()
   */
  @Override
  public int deleteAllPerson() {
    log.debug("Deleting all person");
    final int nbDeleted = dao.deleteAll();
    log.debug(nbDeleted + " persons deleted");
    return nbDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findAllPerson()
   */
  @Override
  public List<PersonDto> findAllPerson() {
    log.debug("Finding all person");
    final List<? extends PersonDo> list = dao.findAll();
    log.debug(list.size() + " persons found");
    return PersonMapper.convertDotoDto(list);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllFriends(presentation.dto.PersonDto)
   */
  @Override
  public int deleteAllFriends(final PersonDto person) {
    final int nbFriendsDeleted = person.getFriends().size();
    log.debug("Deleting all friends to a " + person);
    person.setFriends(new ArrayList<PersonDto>());
    log.debug(nbFriendsDeleted + " friends deleted");
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
