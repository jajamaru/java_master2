/**
 * 
 */
package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
 * @author romain
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configTest/PersonDaoTest-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class PersonCascadingTest {

  @Autowired
  @Qualifier("personDao")
  private IPersonDao dao;

  @Test
  public void autowiredIsWorked() {
    Assert.assertNotNull(dao);
  }

  @Test
  public void persistCascading() throws ParseException {
    final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

    final PersonDo p1 = new PersonDo();
    p1.setName("p1");
    p1.setBirthday(sdf.parse("01/01/2014"));

    final PersonDo p2 = new PersonDo();
    p2.setName("p2");
    p2.setBirthday(sdf.parse("02/02/2014"));

    final PersonDo p3 = new PersonDo();
    p3.setName("p3");
    p3.setBirthday(sdf.parse("03/03/2014"));

    final PersonDo p4 = new PersonDo();
    p4.setName("p4");
    p4.setBirthday(sdf.parse("04/04/2014"));

    p2.setFriends(Arrays.asList(p3, p4));
    p1.setFriends(Arrays.asList(p2));
    p1.setFriendsWith(Arrays.asList(p4));

    dao.create(p1);

    Assert.assertEquals(dao.findAll().size(), 8);
  }
}
