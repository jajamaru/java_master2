/**
 * 
 */
package presentation.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    return "redirect:persons";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      service.deletePerson(id);
      return "redirect:persons";
    } catch (final NoResultException e) {
      return "404";
    }
  }
  
//  @RequestMapping(value = "/{id}/persons", method = RequestMethod.DELETE)
//  public String deleteFriends(final Model model, @PathVariable
//  final Integer id) {
//    try {
//      service.deletePerson(id);
//      return "redirect:persons";
//    } catch (final NoResultException e) {
//      return "404";
//    }
//  }

}
