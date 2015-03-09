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

  private static final Logger LOGGER = Logger.getLogger(PersonService.class);

  @Autowired
  @Qualifier("personDao")
  private IPersonDao          dao;

  /* (non-Javadoc)
   * @see service.IPersonService#createPerson(entity.PersonDo)
   */
  @Override
  public void createPerson(final PersonDto dto) {
    LOGGER.debug("Creating a person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    dao.create(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deletePerson(int)
   */
  @Override
  public int deletePerson(final int id) {
    LOGGER.debug("Deleting a person with id " + id);
    final int nbDeleted = dao.delete(id);
    if (nbDeleted > 0) {
      LOGGER.debug("Person deleted [ nb row deleted " + nbDeleted + " ]");
    } else {
      LOGGER.debug("No person deleted with id " + id);
      throw new PersonNotFoundException("PersonDo object with id " + id
          + " does not exists or he's aldready deleted !");
    }
    return nbDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#updatePerson(entity.PersonDo)
   */
  @Override
  public PersonDto updatePerson(final PersonDto dto) {
    LOGGER.debug("Updating a person " + dto);
    final PersonDo personDo = PersonMapper.convertDtoToDo(dto);
    try {
      final PersonDto dtoMerged = PersonMapper.convertDotoDto(dao.update(personDo));
      LOGGER.debug("Person updated " + dtoMerged);
      return dtoMerged;
    } catch (final IllegalArgumentException e) {
      LOGGER.error("Empty entity or entity was already deleted before the merging", e);
      throw new PersonNotFoundException(
          "Empty entity or entity was already deleted before the merging");
    }
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findPerson(int)
   */
  @Override
  public PersonDto findPerson(final int id) {
    LOGGER.debug("Retrieving a person with id " + id);
    try {
      final PersonDo personDo = dao.find(id);
      LOGGER.debug("Person retrieved " + personDo);
      return PersonMapper.convertDotoDto(personDo);
    } catch (final NoResultException e) {
      LOGGER.error("No person found with id " + id, e);
      throw new PersonNotFoundException("PersonDo object does not found with id " + id);
    }

  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllPerson()
   */
  @Override
  public int deleteAllPerson() {
    LOGGER.debug("Deleting all person");
    final int nbDeleted = dao.deleteAll();
    LOGGER.debug(nbDeleted + " persons deleted");
    return nbDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findAllPerson()
   */
  @Override
  public List<PersonDto> findAllPerson() {
    LOGGER.debug("Finding all person");
    final List<? extends PersonDo> list = dao.findAll();
    LOGGER.debug(list.size() + " persons found");
    return PersonMapper.convertDotoDto(list);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteAllFriends(presentation.dto.PersonDto)
   */
  @Override
  public int deleteAllFriends(final PersonDto person) {
    final int nbFriendsDeleted = person.getFriends().size();
    LOGGER.debug("Deleting all friends to a " + person);
    person.setFriends(new ArrayList<PersonDto>());
    LOGGER.debug(nbFriendsDeleted + " friends deleted");
    updatePerson(person);
    return nbFriendsDeleted;
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deleteSingleFriend(presentation.dto.PersonDto, presentation.dto.PersonDto)
   */
  @Override
  public boolean deleteSingleFriend(final PersonDto person, final PersonDto deleted) {
    if (person.getFriends().remove(deleted) || person.getFriendsWith().remove(deleted)) {
      LOGGER.debug(deleted.getId() + " is deleted from person " + person.getId() + " list");
      updatePerson(person);
    }
    if (deleted.getFriends().remove(person) || deleted.getFriendsWith().remove(person)) {
      LOGGER.debug(person.getId() + " is deleted from person " + deleted.getId() + " list");
      updatePerson(deleted);
    }
    return true;
  }

}
