/**
 * 
 */
package service.impl;

import java.util.List;

import mapper.PersonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import presentation.dto.PersonDto;
import service.IPersonService;
import dao.IPersonDao;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
@Service("personService")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonService implements IPersonService {

  @Autowired
  @Qualifier("personDao")
  private IPersonDao dao;

  /* (non-Javadoc)
   * @see service.IPersonService#createPerson(entity.PersonDo)
   */
  @Override
  public void createPerson(final PersonDto dto) {
    final PersonDo personDo = PersonMapper.convert(dto);
    dao.create(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#deletePerson(int)
   */
  @Override
  public int deletePerson(final int id) {
    return dao.delete(id);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#updatePerson(entity.PersonDo)
   */
  @Override
  public void updatePerson(final PersonDto dto) {
    final PersonDo personDo = PersonMapper.convert(dto);
    dao.update(personDo);
  }

  /* (non-Javadoc)
   * @see service.IPersonService#findPerson(int)
   */
  @Override
  public PersonDo findPerson(final int id) {
    return dao.find(id);

  }

  @Override
  public int deleteAllPerson() {
    return dao.deleteAll();
  }

  @Override
  public List<? extends PersonDo> findAllPerson() {
    return dao.findAll();
  }

}
