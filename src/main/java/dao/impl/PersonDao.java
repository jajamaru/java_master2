/**
 * 
 */
package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
@Transactional(propagation = Propagation.REQUIRED)
public class PersonDao extends IPersonDao {

  @PersistenceUnit(unitName = "pu")
  private EntityManagerFactory entityManagerFactory;

  /* (non-Javadoc)
   * @see dao.IDao#find(int)
   */
  @Override
  public PersonDo find(final int id) {
    final TypedQuery<PersonDo> query = entityManagerFactory.createEntityManager().createNamedQuery(
        "PersonDo.find", PersonDo.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  /* (non-Javadoc)
   * @see dao.IDao#findAll()
   */
  @Override
  public List<? extends PersonDo> findAll() {
    final TypedQuery<PersonDo> query = entityManagerFactory.createEntityManager().createNamedQuery(
        "PersonDo.findAll", PersonDo.class);
    return query.getResultList();
  }

  /* (non-Javadoc)
   * @see dao.IDao#update(java.lang.Object)
   */
  @Override
  public void update(final PersonDo obj) {
    final EntityManager em = entityManagerFactory.createEntityManager();
    em.merge(obj);
  }

  /* (non-Javadoc)
   * @see dao.IDao#delete(int)
   */
  @Override
  public int delete(final int id) {
    final EntityManager em = entityManagerFactory.createEntityManager();

    //    em.getTransaction().begin();

    final Query query = em.createNamedQuery("PersonDo.delete");
    query.setParameter("id", id);
    final int nb = query.executeUpdate();

    //    em.getTransaction().commit();
    return nb;
  }

  /* (non-Javadoc)
   * @see dao.IDao#deleteAll()
   */
  @Override
  public int deleteAll() {
    final EntityManager em = entityManagerFactory.createEntityManager();
    final Query query = em.createNamedQuery("PersonDo.deleteAll");
    //    em.getTransaction().begin();
    final int nb = query.executeUpdate();
    //    em.getTransaction().commit();
    return nb;
  }

  /* (non-Javadoc)
   * @see dao.IDao#create(java.lang.Object)
   */
  @Override
  public void create(final PersonDo obj) {
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    //    entityManager.getTransaction().begin();
    entityManager.persist(obj);
    //    entityManager.getTransaction().commit();
  }

}
