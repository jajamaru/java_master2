/**
 * 
 */
package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import dao.IPersonDao;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configTest/PersonDaoTest-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class PersonDaoTest {

  @Autowired
  @Qualifier("personDao")
  private IPersonDao dao;

  @Test
  public void autowiredIsWorked() {
    Assert.assertNotNull(dao);
  }

  @Test
  public void findAll() {
    Assert.assertEquals(dao.findAll().size(), 3);
  }

  @Test
  public void find() {
    Assert.assertNotNull(dao.find(1));
  }

  @Test
  public void update() {
    final String name = "abcd";
    PersonDo person = dao.find(1);

    person.setName(name);
    dao.update(person);

    person = dao.find(1);
    Assert.assertEquals(name, person.getName());
  }

  @Test
  public void delete() {
    Assert.assertNotNull(dao.delete(1));
  }

  @Test
  public void deleteAll() {
    Assert.assertEquals(3, dao.deleteAll());
  }

  @Test
  public void create() throws ParseException {
    final PersonDo person = new PersonDo();
    person.setName("New");
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
    person.setBirthday(sdf.parse("01/01/2014"));
    System.out.println(person.getBirthday());
    dao.create(person);
    Assert.assertEquals(dao.findAll().size(), 4);
  }

}
