/**
 * 
 */
package presentation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import mapper.PersonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.model.PersonForm;
import service.IPersonService;

/**
 * @author Romain
 *
 */
@Controller
@RequestMapping("personTest")
public class PersonTestController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(method = RequestMethod.GET)
  public String formPerson(final ModelMap model) throws ParseException {
    final PersonForm person = new PersonForm();
    final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    person.setName("grgr");
    person.setBirthday(format.parse("01/01/2015"));
    model.addAttribute("person", person);
    model.addAttribute("result", "Aucun changement");
    return "personTest";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String putPerson(@Valid
  @ModelAttribute("person")
  final PersonForm form, final BindingResult result, final ModelMap model) {
    if (result.hasErrors()) {
      model.addAttribute("result", "Une erreur est survenue dans l'ajout de la personne");
    } else {
      persistPerson(form);
      model.addAttribute("result", "Personne ajoutée avec success");
    }
    return "personTest";
  }

  private void persistPerson(final PersonForm form) {
    service.createPerson(PersonMapper.convertPersonFormToDto(form));
  }

  //  @InitBinder
  //  public void validator(final WebDataBinder binder) {
  //    final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
  //    binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
  //  }

}
