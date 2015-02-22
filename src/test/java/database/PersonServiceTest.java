/**
 * 
 */
package database;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import service.IPersonService;
import entity.PersonDo;

/**
 * @author romain
 *
 */
public class PersonServiceTest {

  @Autowired
  @Qualifier("personService")
  private static IPersonService service;

  @Test
  public void findAll() {
    Assert.assertEquals(service.findAllPerson().size(), 3);
  }

  @Test
  public void find() {
    Assert.assertNotNull(service.findPerson(1));
  }

  @Test
  public void create() {
    final PersonDo person = new PersonDo();
    person.setName("truc");
    person.setBirthday(Date.valueOf("2003-01-01"));
    service.createPerson(person);
  }

  @Test
  public void update() {
    final String name = "abcd";
    PersonDo person = service.findPerson(1);

    person.setName(name);
    service.updatePerson(person);

    person = service.findPerson(1);
    Assert.assertEquals(name, person.getName());
  }

}
