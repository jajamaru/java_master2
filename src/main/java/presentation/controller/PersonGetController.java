/**
 * 
 */
package presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import presentation.dto.PersonDto;
import presentation.dto.xml.PersonListXml;
import presentation.dto.xml.PersonXml;
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

  @ResponseBody
  @RequestMapping(value = "/json", method = RequestMethod.GET)
  public PersonDto[] getListPersonWithReturnTypeJson() {
    final List<? extends PersonDo> list = service.findAllPerson();
    return getPersonInJson(list);
  }

  @ResponseBody
  @RequestMapping(value = "/xml", method = RequestMethod.GET)
  public PersonListXml getListPersonWithReturnTypeXml() {
    final List<? extends PersonDo> list = service.findAllPerson();
    return getPersonInXml(list);
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

  private PersonDto[] getPersonInJson(final List<? extends PersonDo> list) {
    final List<PersonDto> tab = new ArrayList<PersonDto>();
    for (PersonDo person : list) {
      final PersonDto dto = new PersonDto();
      dto.setId(person.getId());
      dto.setName(person.getName());
      dto.setBirthday(person.getBirthday());
      tab.add(dto);
    }
    final PersonDto[] tabDto = new PersonDto[tab.size()];
    tab.toArray(tabDto);
    return tabDto;
  }

  private PersonListXml getPersonInXml(final List<? extends PersonDo> list) {
    final PersonListXml persons = new PersonListXml();
    final List<PersonXml> pList = new ArrayList<PersonXml>();
    for (PersonDo person : list) {
      final PersonXml xml = new PersonXml();
      xml.setId(person.getId());
      xml.setName(person.getName());
      xml.setBirthday(person.getBirthday());
      pList.add(xml);
    }
    persons.setPerson(pList);
    return persons;
  }

  private boolean isValidResult(final String result) {
    return isOkResult(result) || isNokResult(result);
  }

  private boolean isOkResult(final String result) {
    return "ok".equalsIgnoreCase(result);
  }

  private boolean isNokResult(final String result) {
    return "nok".equalsIgnoreCase(result);
  }

  //  private boolean isValidReturnType(final String type) {
  //    return isJsonReturnType(type) || isXmlReturnType(type);
  //  }
  //
  //  private boolean isJsonReturnType(final String type) {
  //    return "json".equalsIgnoreCase(type);
  //  }
  //
  //  private boolean isXmlReturnType(final String type) {
  //    return "xml".equalsIgnoreCase(type);
  //  }

}
