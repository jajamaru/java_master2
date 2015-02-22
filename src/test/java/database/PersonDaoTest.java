/**
 * 
 */
package database;

import java.sql.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
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
  @Transactional
  @Rollback(true)
  public void update() {
    final String name = "abcd";
    PersonDo person = dao.find(1);

    person.setName(name);
    dao.update(person);

    person = dao.find(1);
    Assert.assertEquals(name, person.getName());
  }

  @Test
  @Transactional
  @Rollback(true)
  public void delete() {
    Assert.assertNotNull(dao.delete(1));
  }

  @Test
  @Transactional
  @Rollback(true)
  public void create() {
    final PersonDo person = new PersonDo();
    person.setName("New");
    person.setBirthday(Date.valueOf("2014-01-01"));
    dao.create(person);
    Assert.assertEquals(dao.findAll().size(), 4);
  }

}
