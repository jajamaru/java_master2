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

import presentation.dto.PersonDto;
import presentation.model.PersonForm;
import service.IPersonService;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class UpdatePersonController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getSinglePerson(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      model.addAttribute("person", _createPersonForm(dto));
      return "person";
    } catch (final NoResultException e) {
      return "404";
    }
  }
  
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
      return "redirect:/persons/" + id;
    } else {
      updatePerson(form);
      return "redirect:/persons";
    }
    

  }
  
  private PersonForm _createPersonForm(final PersonDto dto) {
    final PersonForm form = new PersonForm();
    form.setId(dto.getId());
    form.setName(dto.getName());
    form.setBirthday(dto.getBirthday());
    return form;
  }

  private void updatePerson(final PersonForm form) {
    service.updatePerson(PersonMapper.convertPersonFormToDto(form));
  }

}
