/**
 * 
 */
package presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  
  private static final String REDIRECT = "redirect:/persons";

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(method = RequestMethod.DELETE)
  public String deleteListPerson(final Model model) {
    service.deleteAllPerson();
    return REDIRECT;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      service.deletePerson(id);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends", method = RequestMethod.DELETE)
  public String deleteFriends(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      service.deleteAllFriends(dto);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends/{idFriend}", method = RequestMethod.DELETE)
  public String deleteSingleFriend(final Model model, @PathVariable
  final Integer id, @PathVariable
  final Integer idFriend) {
    try {
      final PersonDto dto = service.findPerson(id);
      final PersonDto deleted = service.findPerson(idFriend);
      service.deleteSingleFriend(dto, deleted);
      return REDIRECT;
    } catch (final PersonNotFoundException e) {
      return "404";
    }
  }

}
