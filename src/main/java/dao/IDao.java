/**
 * 
 */
package dao;

import java.util.List;

/**
 * @author romain
 *
 * @param <T> The object's type
 */
public abstract class IDao<T> {

  /**
   * 
   */
  public IDao() {
    //empty method
  }

  /**
   * Find a <U> object by an id
   * <U> must extend <T>
   * @param id Id of object
   * @return <U> object
   */
  public abstract T find(final int id);

  /**
   * Find All <T> objects
   * @return List of <T> objects
   */
  public abstract List<? extends T> findAll();

  /**
   * Update a <T> object
   * @param obj Updating object
   * @return Number of updated object in database
   */
  public abstract <U extends T> void update(final U obj);

  /**
   * Delete a <T> object
   * @param id Id of object
   * @return Number of deleted object in database
   */
  public abstract int delete(final int id);

  /**
   * Delete all <T> objects
   * @return Number of deleted object in database
   */
  public abstract int deleteAll();

  /**
   * Create a <T> object in database
   * @param obj Creating object
   */
  public abstract <U extends T> void create(final U obj);

}
