/**
 * 
 */
package presentation.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.IPersonService;
import entity.PersonDo;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class PersonGetController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(method = RequestMethod.GET)
  public String getListPerson(final Model model) {
    model.addAttribute("personList", service.findAllPerson());
    return "personList";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDo person = service.findPerson(id);
      model.addAttribute("person", person);
      return "person";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/persons", method = RequestMethod.GET)
  public String getFriends(final Model model, @PathVariable
  final Integer id) {
    final List<? extends PersonDo> list = service.findFriends(id);
    model.addAttribute("friendList", list);
    return (list != null) ? "personFriends" : "404";
  }

}
