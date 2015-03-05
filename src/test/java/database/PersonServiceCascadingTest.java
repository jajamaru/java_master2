/**
 * 
 */
package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
public class PersonServiceCascadingTest {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @Test
  public void autowiredIsWorked() {
    Assert.assertNotNull(service);
  }

  @Test
  public void retrieveCascading() {
    final PersonDto p1 = service.findPerson(1);
    final List<PersonDto> friends = p1.getFriends();

    Assert.assertEquals(friends.size(), 2);
    Assert.assertTrue(friends.get(0).equals(service.findPerson(2)));
    Assert.assertTrue(friends.get(1).equals(service.findPerson(3)));
  }

  @Test
  public void persistCascading() throws ParseException {
    final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

    final PersonDto p1 = new PersonDto();
    p1.setName("p1");
    p1.setBirthday(sdf.parse("01/01/2014"));

    final PersonDto p2 = new PersonDto();
    p2.setName("p2");
    p2.setBirthday(sdf.parse("02/02/2014"));

    final PersonDto p3 = new PersonDto();
    p3.setName("p3");
    p3.setBirthday(sdf.parse("03/03/2014"));

    final PersonDto p4 = new PersonDto();
    p4.setName("p4");
    p4.setBirthday(sdf.parse("04/04/2014"));

    p2.setFriends(Arrays.asList(p3, p4));
    p1.setFriends(Arrays.asList(p2));

    service.createPerson(p1);

    Assert.assertEquals(service.findAllPerson().size(), 9);
  }

  @Test
  public void deleteCascading() {
    service.deletePerson(1);
    Assert.assertEquals(service.findAllPerson().size(), 4);

    final PersonDto p2 = service.findPerson(2);
    final PersonDto p3 = service.findPerson(3);

    Assert.assertEquals(p2.getFriends().size(), 1);
    Assert.assertEquals(p3.getFriends().size(), 0);
  }

  @Test
  public void addFriend() {
    final PersonDto f1 = service.findPerson(5);

    final PersonDto dto = service.findPerson(1);
    dto.getFriends().add(f1);

    service.updatePerson(dto);

    Assert.assertEquals(service.findPerson(1).getFriends().size(), 3);
  }

  @Test
  public void deleteFriend() {
    final PersonDto dto = service.findPerson(1);
    dto.setFriends(null);

    service.updatePerson(dto);

    Assert.assertEquals(service.findPerson(1).getFriends().size(), 0);
  }

}
