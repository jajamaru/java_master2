/**
 * 
 */
package presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.dto.PersonDto;
import service.IPersonService;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class PersonDeleteController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(method = RequestMethod.DELETE)
  public String deleteListPerson(final Model model) {
    service.deleteAllPerson();
    return "redirect:/persons";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      service.deletePerson(id);
      return "redirect:/persons";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends", method = RequestMethod.DELETE)
  public String deleteFriends(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      _deleteFriends(dto);
      return "redirect:/persons";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends/{idFriend}", method = RequestMethod.DELETE)
  public String deleteSingleFriend(final Model model, @PathVariable
  final Integer id, @PathVariable
  final Integer idFriend) {
    try {
      final PersonDto dto = service.findPerson(id);
      _deleteSingleFriend(dto, idFriend);
      return "redirect:/persons";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  private void _deleteFriends(final PersonDto dto) {
    dto.setFriends(new ArrayList<PersonDto>());
    service.updatePerson(dto);
  }

  private void _deleteSingleFriend(final PersonDto dto, final Integer id) throws NoResultException {
    final List<PersonDto> friends = dto.getFriends();
    final PersonDto f = service.findPerson(id);
    if (friends.contains(f)) {
      friends.remove(f);
    }
    service.updatePerson(dto);
  }

}
