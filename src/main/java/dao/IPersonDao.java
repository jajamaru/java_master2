/**
 * 
 */
package dao;

import java.util.List;

import entity.PersonDo;

/**
 * @author romain
 *
 */
public abstract class IPersonDao extends IDao<PersonDo> {

  public abstract List<? extends PersonDo> findFriends(final Integer id);

}
