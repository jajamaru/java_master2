/**
 * 
 */
package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    Assert.assertEquals(dao.findAll().size(), 4);
  }

  @Test
  public void find() {
    Assert.assertNotNull(dao.find(1));
    Assert.assertNotNull(dao.find(1).getFriends());
    Assert.assertNotNull(dao.find(1).getFriendsWith());
  }

  @Test
  public void findFriends() {
    Assert.assertEquals(dao.findFriends(1).size(), 2);

    final List<PersonDo> list = dao.findFriends(2);
    Assert.assertEquals(list.size(), 2);

    final List<Integer> array = new ArrayList<Integer>();
    for (final Iterator<PersonDo> it = list.iterator(); it.hasNext();) {
      array.add(it.next().getId());
    }
    Assert.assertTrue(array.contains(1));
    Assert.assertTrue(array.contains(4));

    Assert.assertEquals(dao.findFriends(4).size(), 1);
    Assert.assertEquals(dao.findFriends(3).size(), 1);
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
    Assert.assertEquals(dao.findFriends(2).size(), 1);
  }

  @Test
  public void deleteAll() {
    Assert.assertEquals(dao.deleteAll(), 4);
  }

  @Test
  public void create() throws ParseException {
    final PersonDo person = new PersonDo();
    person.setName("New");
    final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
    person.setBirthday(sdf.parse("01/01/2014"));
    System.out.println(person.getBirthday());
    dao.create(person);
    Assert.assertEquals(dao.findAll().size(), 5);
  }

}
