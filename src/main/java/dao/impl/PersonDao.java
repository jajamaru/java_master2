/**
 * 
 */
package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.IPersonDao;
import entity.PersonDo;

/**
 * @author romain
 *
 */
@Repository("personDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PersonDao extends IPersonDao {

  private static final Logger LOGGER = Logger.getLogger(PersonDao.class);

  @PersistenceContext(unitName = "pu")
  private EntityManager       entityManager;

  /* (non-Javadoc)
   * @see dao.IDao#find(int)
   */
  @Override
  public PersonDo find(final int id) {
    LOGGER.debug("Retrieving a person with id " + id);
    final TypedQuery<? extends PersonDo> query = entityManager.createNamedQuery("PersonDo.find",
        PersonDo.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  /* (non-Javadoc)
   * @see dao.IDao#findAll()
   */
  @Override
  public List<? extends PersonDo> findAll() {
    LOGGER.debug("Retrieving all persons");
    final TypedQuery<PersonDo> query = entityManager.createNamedQuery("PersonDo.findAll",
        PersonDo.class);
    return query.getResultList();
  }

  /* (non-Javadoc)
   * @see dao.IDao#update(java.lang.Object)
   */
  @Override
  public PersonDo update(final PersonDo obj) {
    LOGGER.debug("Updating a person " + obj);
    return entityManager.merge(obj);
  }

  /* (non-Javadoc)
   * @see dao.IDao#delete(int)
   */
  @Override
  public int delete(final int id) {
    LOGGER.debug("Deleting a person with id " + id);
    final Query query = entityManager.createNamedQuery("PersonDo.delete");
    query.setParameter("id", id);
    return query.executeUpdate();
  }

  /* (non-Javadoc)
   * @see dao.IDao#deleteAll()
   */
  @Override
  public int deleteAll() {
    LOGGER.debug("Deleting all persons");
    final Query query = entityManager.createNamedQuery("PersonDo.deleteAll");
    return query.executeUpdate();
  }

  /* (non-Javadoc)
   * @see dao.IDao#create(java.lang.Object)
   */
  @Override
  public <U extends PersonDo> void create(final U obj) {
    LOGGER.debug("Creating a person " + obj);
    entityManager.persist(obj);
  }

}
