/**
 * 
 */
package presentation.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import presentation.dto.PersonDto;
import presentation.model.FriendForm;
import service.IPersonService;
import exception.PersonNotFoundException;

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
  public String getFriendPerson(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      final List<PersonDto> allPersons = service.findAllPerson();
      model.addAttribute("friendList", _createFriendForm(dto, allPersons));
      return "personFriends";
    } catch (final PersonNotFoundException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public String addFriend(@Valid
  @ModelAttribute("friendList")
  final FriendForm form, final BindingResult result, final ModelMap model, @PathVariable
  final Integer id) {

    try {
      if (result.hasErrors()) {
        model.addAttribute("result", "NOK");
      } else {
        _updateFriends(form);
        model.addAttribute("result", "OK");
      }
      return "redirect:/persons";
    } catch (final PersonNotFoundException e) {
      return "404";
    }
  }

  private void _updateFriends(final FriendForm form) {
    service.updatePerson(PersonFormMapper.convertFriendFormToDto(form));
  }

  private FriendForm _createFriendForm(final PersonDto refDto, final List<PersonDto> list) {
    return PersonFormMapper.createFriendForm(refDto, list);
  }

}
