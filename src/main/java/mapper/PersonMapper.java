/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import presentation.dto.PersonDto;
import entity.FemmeDo;
import entity.HommeDo;
import entity.PersonDo;
import entity.Sexe;

/**
 * @author Romain
 *
 */
public class PersonMapper {

  private static Logger log = Logger.getLogger(PersonMapper.class);

  /**
   * Convert a PersonDto object to PersonDo object
   * @param dto to convert
   * @return PersonDo object
   */
  public static PersonDo convertDtoToDo(final PersonDto dto) {
    log.debug("Begin of conversion of " + dto);
    final PersonDo person = _createDo(dto);
    if (dto.getFriends() != null && !dto.getFriends().isEmpty()) {
      log.debug("Friends conversion" + dto.getFriends().size());
      final MemoryPerson<PersonDo, PersonDto> mem = new MemoryPerson<PersonDo, PersonDto>();
      person.setFriends(_convertDtotoDo(dto.getFriends(), mem));
    }
    log.debug("End of converion " + person);
    return person;
  }

  /**
   * Convert a list of PersonDto object to a list of PersonDo object
   * @param listPersonDto to convert
   * @return List of PersonDo
   */
  private static List<PersonDo> _convertDtotoDo(final List<? extends PersonDto> listPersonDto,
      final MemoryPerson<PersonDo, PersonDto> mem) {
    final List<PersonDo> list = new ArrayList<PersonDo>();
    for (final PersonDto itDto : listPersonDto) {
      log.debug("Friend conversion " + itDto);
      final PersonDo tmpDo = _createDo(itDto);

      if (!mem.isInstanciated(tmpDo)) {
        mem.addInstanciated(tmpDo);
      }

      final PersonDo instance = mem.getInstanceOf(tmpDo);
      list.add(instance);

      if (!mem.isExplored(itDto)) {
        mem.addExplored(itDto);
        if (itDto.getFriends() != null && !itDto.getFriends().isEmpty()) {
          tmpDo.setFriends(_convertDtotoDo(itDto.getFriends(), mem));
        }
      }
    }
    return list;
  }

  /**
   * @param dto
   * @return
   */
  private static PersonDo _createDo(final PersonDto dto) {
    if (Sexe.HOMME.equals(dto.getSexe())) {
      return _createHommeDo(dto);
    } else if (Sexe.FEMME.equals(dto.getSexe())) {
      return _createFemmeDo(dto);
    }
    return null;
  }

  private static HommeDo _createHommeDo(final PersonDto dto) {
    log.debug("Creating a HommeDo object with " + dto);
    final HommeDo homme = new HommeDo();
    homme.setBirthday(dto.getBirthday());
    homme.setName(dto.getName());
    homme.setId(dto.getId());
    log.debug("HommeDo created " + homme);
    return homme;
  }

  private static FemmeDo _createFemmeDo(final PersonDto dto) {
    log.debug("Creating a FemmeDo object with " + dto);
    final FemmeDo femme = new FemmeDo();
    femme.setBirthday(dto.getBirthday());
    femme.setName(dto.getName());
    femme.setId(dto.getId());
    log.debug("FemmeDo created " + femme);
    return femme;
  }

  /**
   * @param person
   * @return
   */
  private static PersonDto _createDto(final PersonDo person) {
    log.debug("Creating a PersonDto object with " + person);
    final PersonDto dto = new PersonDto();
    dto.setBirthday(person.getBirthday());
    dto.setName(person.getName());
    dto.setId(person.getId());
    dto.setSexe(person.getSexe());
    log.debug("PersonDto created " + dto);
    return dto;
  }

  /**
   * Convert a PesonDo object to PersonDto object
   * @param person do to convert
   * @return PersonDto object
   */
  public static PersonDto convertDotoDto(final PersonDo person) {
    log.debug("Begin of conversion of " + person);
    final PersonDto dto = _createDto(person);

    final List<PersonDto> result = new ArrayList<PersonDto>();

    final MemoryPerson<PersonDto, PersonDo> mem = new MemoryPerson<PersonDto, PersonDo>();
    if (person.getFriends() != null && !person.getFriends().isEmpty()) {
      log.debug("Friend conversion [ getFriends() " + person.getFriends().size() + " ]");
      result.addAll(_convertDoToDto(person.getFriends(), mem));
    }
    if (person.getFriendsWith() != null && !person.getFriendsWith().isEmpty()) {
      log.debug("Friend conversion [ getFriendsWith() " + person.getFriendsWith().size() + " ]");
      result.addAll(_convertDoToDto(person.getFriendsWith(), mem));
    }

    dto.setFriends(result);
    log.debug("End of converion " + dto);
    return dto;
  }

  /**
   * @param friends
   * @return
   */
  private static List<PersonDto> _convertDoToDto(final List<PersonDo> friends,
      final MemoryPerson<PersonDto, PersonDo> mem) {
    final List<PersonDto> result = new ArrayList<PersonDto>();
    for (final PersonDo itDo : friends) {
      log.debug("Friend conversion " + itDo);
      final PersonDto tmpDto = _createDto(itDo);

      if (!mem.isInstanciated(tmpDto)) {
        mem.addInstanciated(tmpDto);
      }

      final PersonDto instance = mem.getInstanceOf(tmpDto);
      result.add(instance);

      final List<PersonDto> f = new ArrayList<PersonDto>();
      if (!mem.isExplored(itDo)) {
        mem.addExplored(itDo);
        if (itDo.getFriends() != null && !itDo.getFriends().isEmpty()) {
          f.addAll(_convertDoToDto(itDo.getFriends(), mem));
        }
        if (itDo.getFriendsWith() != null && !itDo.getFriendsWith().isEmpty()) {
          f.addAll(_convertDoToDto(itDo.getFriendsWith(), mem));
        }
        tmpDto.setFriends(f);
      }

    }
    return result;
  }

  /**
   * Convert a list of PersonDo object to a list of PersonDto object
   * @param listPersonDo to convert
   * @return List of PersonDto
   */
  public static List<PersonDto> convertDotoDto(final List<? extends PersonDo> listPersonDo) {
    log.debug("Begin of list conversion " + listPersonDo.size());
    final List<PersonDto> list = new ArrayList<PersonDto>();
    for (final PersonDo itDo : listPersonDo) {
      list.add(convertDotoDto(itDo));
    }
    log.debug("End of list conversion " + list);
    return list;
  }

}

/**
 * @author romain
 * This class use to break infinite recursion.
 * @param <T> Instance of object to stock
 * @param <U> Object already explored
 */
final class MemoryPerson<T, U> {

  private Set<T> instance;
  private Set<U> explored;

  public MemoryPerson() {
    this.instance = new HashSet<T>();
    this.explored = new HashSet<U>();
  }

  public boolean isExplored(final U isExplored) {
    return explored.contains(isExplored);
  }

  public boolean isInstanciated(final T isInstanciated) {
    return instance.contains(isInstanciated);
  }

  public boolean addExplored(final U addExplored) {
    return explored.add(addExplored);
  }

  public boolean addInstanciated(final T addInstanciated) {
    return instance.add(addInstanciated);
  }

  public T getInstanceOf(final T instanciated) {
    if (isInstanciated(instanciated)) {
      for (final Iterator<T> it = instance.iterator(); it.hasNext();) {
        final T elt = it.next();
        if (elt.equals(instanciated)) {
          return elt;
        }
      }
    }
    return null;
  }
}