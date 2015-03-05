/**
 * 
 */
package presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import presentation.dto.PersonDto;
import presentation.dto.json.PersonJson;
import presentation.dto.xml.PersonListXml;
import presentation.dto.xml.PersonXml;
import service.IPersonService;

/**
 * @author romain
 *
 */
@Controller
@RequestMapping("/persons")
public class ManyPersonsController {

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
  public String getListPersonWithReturnTypeJson() throws JsonGenerationException,
      JsonMappingException, IOException {
    final List<? extends PersonDto> list = service.findAllPerson();
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(_getPersonInJson(list));
  }

  @ResponseBody
  @RequestMapping(value = "/xml", method = RequestMethod.GET)
  public PersonListXml getListPersonWithReturnTypeXml() {
    final List<? extends PersonDto> list = service.findAllPerson();
    return _getPersonInXml(list);
  }

  private PersonJson[] _getPersonInJson(final List<? extends PersonDto> list) {
    final List<PersonJson> tab = new ArrayList<PersonJson>();
    for (PersonDto person : list) {
      final PersonJson json = new PersonJson();
      json.setId(person.getId());
      json.setName(person.getName());
      json.setBirthday(person.getBirthday());
      tab.add(json);
    }
    final PersonJson[] tabDto = new PersonJson[tab.size()];
    tab.toArray(tabDto);
    return tabDto;
  }

  private PersonListXml _getPersonInXml(final List<? extends PersonDto> list) {
    final PersonListXml persons = new PersonListXml();
    final List<PersonXml> pList = new ArrayList<PersonXml>();
    for (PersonDto person : list) {
      final PersonXml xml = new PersonXml();
      xml.setId(person.getId());
      xml.setName(person.getName());
      xml.setBirthday(person.getBirthday());
      pList.add(xml);
    }
    persons.setPerson(pList);
    return persons;
  }

}
