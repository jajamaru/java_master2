/**
 * 
 */
package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

  @PersistenceContext(unitName = "pu")
  private EntityManager entityManager;

  /* (non-Javadoc)
   * @see dao.IDao#find(int)
   */
  @Override
  public PersonDo find(final int id) {
    final TypedQuery<PersonDo> query = entityManager.createNamedQuery("PersonDo.find",
        PersonDo.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  /* (non-Javadoc)
   * @see dao.IDao#findAll()
   */
  @Override
  public List<? extends PersonDo> findAll() {
    final TypedQuery<PersonDo> query = entityManager.createNamedQuery("PersonDo.findAll",
        PersonDo.class);
    return query.getResultList();
  }

  /* (non-Javadoc)
   * @see dao.IDao#update(java.lang.Object)
   */
  @Override
  public void update(final PersonDo obj) {
    entityManager.merge(obj);
  }

  /* (non-Javadoc)
   * @see dao.IDao#delete(int)
   */
  @Override
  public int delete(final int id) {
    final Query query = entityManager.createNamedQuery("PersonDo.delete");
    query.setParameter("id", id);
    return query.executeUpdate();
  }

  /* (non-Javadoc)
   * @see dao.IDao#deleteAll()
   */
  @Override
  public int deleteAll() {
    final Query query = entityManager.createNamedQuery("PersonDo.deleteAll");
    return query.executeUpdate();
  }

  /* (non-Javadoc)
   * @see dao.IDao#create(java.lang.Object)
   */
  @Override
  public void create(final PersonDo obj) {
    entityManager.persist(obj);
  }

  @Override
  public List<? extends PersonDo> findFriends(final Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

}
