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

import presentation.model.PersonForm;
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

  @RequestMapping(value = "?result={result}", method = RequestMethod.GET)
  public String getListPersonWithRequestFeedback(final Model model, @PathVariable
  final String result) {
    model.addAttribute("personList", service.findAllPerson());
    if (isValidResult(result)) {
      if (isOkResult(result)) {
        model.addAttribute("result", "OK");
      } else {
        model.addAttribute("result", "NOK");
      }
    }
    return "personList";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDo personDo = service.findPerson(id);
      final PersonForm form = new PersonForm();
      form.setId(personDo.getId());
      form.setName(personDo.getName());
      form.setBirthday(personDo.getBirthday());
      model.addAttribute("person", form);
      return "person";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  //  @RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
  //  public String getFriends(final Model model, @PathVariable
  //  final Integer id) {
  //    final List<? extends PersonDo> list = service.findFriends(id);
  //    model.addAttribute("friendList", list);
  //    return (list != null) ? "personFriends" : "404";
  //  }

  private boolean isValidResult(final String result) {
    return isOkResult(result) || isNokResult(result);
  }

  private boolean isOkResult(final String result) {
    return result.equalsIgnoreCase("ok");
  }

  private boolean isNokResult(final String result) {
    return result.equalsIgnoreCase("nok");
  }

}
