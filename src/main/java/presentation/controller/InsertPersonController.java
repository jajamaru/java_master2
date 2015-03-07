/**
 * 
 */
package presentation.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import mapper.PersonFormMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.model.PersonForm;
import service.IPersonService;
import entity.Sexe;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class InsertPersonController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String printFormInsertPerson(final Model model) {
    final PersonForm person = new PersonForm();
    model.addAttribute("person", person);
    return "formPerson";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String insertPerson(@Valid
  @ModelAttribute("person")
  final PersonForm form, final BindingResult result, final ModelMap model) {
    if (result.hasErrors()) {
      return "formPerson";
    } else {
      _persistPerson(form);
      return "redirect:/persons";
    }
  }

  @ModelAttribute("sexeChoice")
  public List<String> getSexe() {
    return Arrays.asList(Sexe.Values.HOMME, Sexe.Values.FEMME);
  }

  private void _persistPerson(final PersonForm form) {
    service.createPerson(PersonFormMapper.convertPersonFormToDto(form));
  }

}
