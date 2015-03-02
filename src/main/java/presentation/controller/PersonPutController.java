/**
 * 
 */
package presentation.controller;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import mapper.PersonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.model.PersonForm;
import service.IPersonService;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class PersonPutController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public String updatePerson(final Model model, @PathVariable
  final Integer id, @Valid
  @ModelAttribute("person")
  final PersonForm form, final BindingResult result) {
    try {
      service.findPerson(id);
    } catch (final NoResultException e) {
      return "404";
    }

    if (result.hasErrors()) {
      model.addAttribute("result", "NOK");
    } else {
      updatePerson(form);
      model.addAttribute("result", "OK");
    }
    return "redirect:/persons";

  }

  private void updatePerson(final PersonForm form) {
    service.updatePerson(PersonMapper.convertFormToDto(form));
  }

  //  @RequestMapping(value = "/{id}/friends/{idFriend}", method = RequestMethod.PUT)
  //  public String updateFriend(final Model model, @PathVariable
  //  final Integer id, @PathVariable
  //  final Integer idFriend) {
  //
  //    return "redirect:persons";
  //  }

}
