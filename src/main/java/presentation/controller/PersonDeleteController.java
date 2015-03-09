/**
 * 
 */
package presentation.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.dto.PersonDto;
import service.IPersonService;
import exception.PersonNotFoundException;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class PersonDeleteController {

  private static final Logger LOGGER               = Logger.getLogger(PersonDeleteController.class);
  private static final String REDIRECT             = "redirect:/persons";
  private static final String LOG_PERSON_NOT_FOUND = "Person not found";

  @Autowired
  @Qualifier("personService")
  private IPersonService      service;

  @RequestMapping(method = RequestMethod.DELETE)
  public String deleteListPerson() {
    service.deleteAllPerson();
    return REDIRECT;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteSinglePerson(@PathVariable
  final Integer id) {
    try {
      service.deletePerson(id);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      LOGGER.error(LOG_PERSON_NOT_FOUND, e);
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends", method = RequestMethod.DELETE)
  public String deleteFriends(@PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      service.deleteAllFriends(dto);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      LOGGER.error(LOG_PERSON_NOT_FOUND, e);
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends/{idFriend}", method = RequestMethod.DELETE)
  public String deleteSingleFriend(@PathVariable
  final Integer id, @PathVariable
  final Integer idFriend) {
    try {
      final PersonDto dto = service.findPerson(id);
      final PersonDto deleted = service.findPerson(idFriend);
      service.deleteSingleFriend(dto, deleted);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      LOGGER.error(LOG_PERSON_NOT_FOUND, e);
      return "404";
    }
  }

}
