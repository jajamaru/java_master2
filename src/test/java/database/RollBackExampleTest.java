/**
 * 
 */
package database;

import javax.persistence.PersistenceException;

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
import exception.PersonNotFoundException;

/**
 * @author romain
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configTest/applicationContext-test.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RollBackExampleTest {

  @Autowired
  @Qualifier("personService")
  private IPersonService service;

  @Test(expected = PersistenceException.class)
  public void persistPersonWhoIsPersisted() {
    final PersonDto p1 = service.findPerson(1);
    service.createPerson(p1);
  }

  @Test(expected = PersonNotFoundException.class)
  public void deletePersonWhoIsDeleted() {
    service.deletePerson(1);
    service.deletePerson(1);
  }

  @Test(expected = PersonNotFoundException.class)
  public void findPersonWhoIsNotInDatabase() {
    service.findPerson(Integer.MAX_VALUE);
  }

}
