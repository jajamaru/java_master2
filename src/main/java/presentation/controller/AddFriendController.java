/**
 * 
 */
package presentation.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import mapper.PersonMapper;

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
import presentation.model.Friend;
import presentation.model.FriendForm;
import presentation.model.PersonForm;
import service.IPersonService;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class AddFriendController {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
  public String getFriendPerson(final Model model, @PathVariable
  final Integer id) {
    try {
      final PersonDto dto = service.findPerson(id);
      final List<PersonDto> allPersons = service.findAllPerson();
      model.addAttribute("friendList", _createFriendForm(dto, allPersons));
      return "personFriends";
    } catch (final NoResultException e) {
      return "404";
    }
  }

  @RequestMapping(value = "/{id}/friends", method = RequestMethod.PUT)
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
    } catch (final NoResultException e) {
      return "404";
    }
  }

  private void _updateFriends(final FriendForm form) {
    service.updatePerson(PersonMapper.convertFriendFormToDto(form));
  }

  private PersonForm _createPersonForm(final PersonDto dto) {
    final PersonForm form = new PersonForm();
    form.setId(dto.getId());
    form.setName(dto.getName());
    form.setBirthday(dto.getBirthday());
    return form;
  }

  private FriendForm _createFriendForm(final PersonDto refDto, final List<PersonDto> list) {
    final FriendForm form = new FriendForm();
    form.setPerson(_createPersonForm(refDto));
    form.setFriends(_createListFriendForm(refDto, list));
    return form;
  }

  private List<Friend> _createListFriendForm(final PersonDto refDto, final List<PersonDto> list) {
    final List<Friend> form = new ArrayList<Friend>();
    final List<PersonDto> friends = refDto.getFriends();
    for (final Iterator<PersonDto> it = list.iterator(); it.hasNext();) {
      final PersonDto person = it.next();
      if (!person.equals(refDto)) {
        final Friend newFriend = new Friend();
        newFriend.setPerson(_createPersonForm(person));
        newFriend.setFriendShip(friends.contains(person));
        form.add(newFriend);
      }
    }
    return form;
  }

}
