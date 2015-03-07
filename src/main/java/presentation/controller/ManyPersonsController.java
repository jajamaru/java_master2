/**
 * 
 */
package presentation.controller;

import java.io.IOException;
import java.util.List;

import mapper.PersonJsonMapper;
import mapper.PersonXmlMapper;

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
    return PersonJsonMapper.getPersonInJson(list);
  }

  private PersonListXml _getPersonInXml(final List<? extends PersonDto> list) {
    return PersonXmlMapper.getPersonInXml(list);
  }

}
