/**
 * 
 */
package presentation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.model.PersonForm;

/**
 * @author Romain
 *
 */
@Controller
@RequestMapping("personTest")
public class PersonTestController {

  @RequestMapping(method = RequestMethod.GET)
  public String formPerson(final ModelMap model) {
    final PersonForm person = new PersonForm();
    model.addAttribute("person", person);
    model.addAttribute("result", "Aucun changement");
    return "personTest";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String putPerson(@Validated
  final PersonForm person, final BindingResult result, final ModelMap model) {
    if (result.hasErrors()) {
      model.addAttribute("result", "Une erreur est survenue dans l'ajout de la personne");
    } else {
      model.addAttribute("result", "Personne ajoutée avec success");
      model.addAttribute("person", person);
    }
    return "personTest";
  }

  @InitBinder
  public void validator(final WebDataBinder binder) {
    final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
  }

}
