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
    Assert.assertEquals(service.findAllPerson().size(), 5);
  }

  @Test
  public void find() {
    Assert.assertNotNull(service.findPerson(1));
    Assert.assertNotNull(service.findPerson(1).getFriends());
    Assert.assertEquals(service.findPerson(1).getFriends().size(), 2);

    Assert.assertNotNull(service.findPerson(2));
    Assert.assertNotNull(service.findPerson(2).getFriends());
    Assert.assertEquals(service.findPerson(2).getFriends().size(), 2);

    Assert.assertNotNull(service.findPerson(3));
    Assert.assertNotNull(service.findPerson(3).getFriends());
    Assert.assertEquals(service.findPerson(3).getFriends().size(), 1);

    Assert.assertNotNull(service.findPerson(5));
    Assert.assertEquals(service.findPerson(5).getFriends().size(), 0);
  }

  @Test
  public void create() {
    final PersonDto dto = new PersonDto();
    dto.setName("truc");
    dto.setBirthday(Date.valueOf("2003-01-01"));
    service.createPerson(dto);
    Assert.assertEquals(service.findAllPerson().size(), 6);
  }

  @Test
  public void updateSinglePerson() {
    final String name = "abcd";
    PersonDto dto = service.findPerson(1);
    dto.setName(name);

    service.updatePerson(dto);

    dto = service.findPerson(1);
    Assert.assertEquals(name, dto.getName());
  }

  @Test
  public void delete() {
    Assert.assertNotNull(service.deletePerson(1));
  }

  @Test
  public void deleteAll() {
    Assert.assertEquals(5, service.deleteAllPerson());
  }

}
