/**
 * 
 */
package database;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import presentation.dto.PersonDto;
import service.IPersonService;
import entity.PersonDo;

/**
 * @author romain
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configTest/PersonDaoTest-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class PersonServiceTest {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @Test
  public void autowiredIsWorked() {
    Assert.assertNotNull(service);
  }

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
    final PersonDto dto = new PersonDto();
    dto.setName("truc");
    dto.setBirthday(Date.valueOf("2003-01-01"));
    service.createPerson(dto);
    Assert.assertEquals(service.findAllPerson().size(), 4);
  }

  @Test
  public void update() {
    final String name = "abcd";
    PersonDo person = service.findPerson(1);

    // On récupère le do popur le convertir en dto
    final PersonDto dto = new PersonDto();
    dto.setId(person.getId());
    dto.setName(person.getName());
    dto.setBirthday(person.getBirthday());

    // On change le champs name du dto
    dto.setName(name);
    service.updatePerson(dto);

    person = service.findPerson(1);
    Assert.assertEquals(name, person.getName());
  }

  @Test
  public void delete() {
    Assert.assertNotNull(service.deletePerson(1));
  }

  @Test
  public void deleteAll() {
    Assert.assertEquals(3, service.deleteAllPerson());
  }

}
