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

  private PersonMapper() {
    //empty constructor
  }

  private static final Logger LOGGER = Logger.getLogger(PersonMapper.class);

  /**
   * Convert a PersonDto object to PersonDo object
   * @param dto to convert
   * @return PersonDo object
   */
  public static PersonDo convertDtoToDo(final PersonDto dto) {
    LOGGER.debug("Begin of conversion of " + dto);
    final PersonDo person = createDo(dto);
    final MemoryPerson<PersonDo, PersonDto> mem = new MemoryPerson<PersonDo, PersonDto>();
    if (dto.getFriends() != null && !dto.getFriends().isEmpty()) {
      LOGGER.debug("Friends conversion" + dto.getFriends().size());
      person.setFriends(convertDtoListtoDoList(dto.getFriends(), mem));
    }
    if (dto.getFriendsWith() != null && !dto.getFriendsWith().isEmpty()) {
      LOGGER.debug("FriendsWith conversion" + dto.getFriendsWith().size());
      person.setFriendsWith(convertDtoListtoDoList(dto.getFriendsWith(), mem));
    }
    LOGGER.debug("End of converion " + person);
    return person;
  }

  /**
   * Convert a list of PersonDto object to a list of PersonDo object
   * @param listPersonDto to convert
   * @return List of PersonDo
   */
  private static List<PersonDo> convertDtoListtoDoList(
      final List<? extends PersonDto> listPersonDto, final MemoryPerson<PersonDo, PersonDto> mem) {
    final List<PersonDo> list = new ArrayList<PersonDo>();
    for (final PersonDto itDto : listPersonDto) {
      LOGGER.debug("Friend conversion " + itDto);
      final PersonDo tmpDo = createDo(itDto);

      if (!mem.isInstanciated(tmpDo)) {
        mem.addInstanciated(tmpDo);
      }

      final PersonDo instance = mem.getInstanceOf(tmpDo);
      list.add(instance);

      if (!mem.isExplored(itDto)) {
        mem.addExplored(itDto);
        if (itDto.getFriends() != null && !itDto.getFriends().isEmpty()) {
          tmpDo.setFriends(convertDtoListtoDoList(itDto.getFriends(), mem));
        }
        if (itDto.getFriendsWith() != null && !itDto.getFriendsWith().isEmpty()) {
          tmpDo.setFriendsWith(convertDtoListtoDoList(itDto.getFriendsWith(), mem));
        }
      }
    }
    return list;
  }

  /**
   * @param dto
   * @return
   */
  private static PersonDo createDo(final PersonDto dto) {
    if (Sexe.HOMME.equals(dto.getSexe())) {
      return createHommeDo(dto);
    } else if (Sexe.FEMME.equals(dto.getSexe())) {
      return createFemmeDo(dto);
    }
    return null;
  }

  private static HommeDo createHommeDo(final PersonDto dto) {
    LOGGER.debug("Creating a HommeDo object with " + dto);
    final HommeDo homme = new HommeDo();
    homme.setBirthday(dto.getBirthday());
    homme.setName(dto.getName());
    homme.setId(dto.getId());
    LOGGER.debug("HommeDo created " + homme);
    return homme;
  }

  private static FemmeDo createFemmeDo(final PersonDto dto) {
    LOGGER.debug("Creating a FemmeDo object with " + dto);
    final FemmeDo femme = new FemmeDo();
    femme.setBirthday(dto.getBirthday());
    femme.setName(dto.getName());
    femme.setId(dto.getId());
    LOGGER.debug("FemmeDo created " + femme);
    return femme;
  }

  /**
   * @param person
   * @return
   */
  private static PersonDto createDto(final PersonDo person) {
    LOGGER.debug("Creating a PersonDto object with " + person);
    final PersonDto dto = new PersonDto();
    dto.setBirthday(person.getBirthday());
    dto.setName(person.getName());
    dto.setId(person.getId());
    dto.setSexe(person.getSexe());
    LOGGER.debug("PersonDto created " + dto);
    return dto;
  }

  /**
   * Convert a PesonDo object to PersonDto object
   * @param person do to convert
   * @return PersonDto object
   */
  public static PersonDto convertDotoDto(final PersonDo person) {
    LOGGER.debug("Begin of conversion of " + person);
    final PersonDto dto = createDto(person);

    final List<PersonDto> friends = new ArrayList<PersonDto>();
    final List<PersonDto> friendsWith = new ArrayList<PersonDto>();

    final MemoryPerson<PersonDto, PersonDo> mem = new MemoryPerson<PersonDto, PersonDo>();
    if (person.getFriends() != null && !person.getFriends().isEmpty()) {
      LOGGER.debug("Friend conversion [ getFriends() " + person.getFriends().size() + " ]");
      friends.addAll(convertDoListToDtoList(person.getFriends(), mem));
    }
    if (person.getFriendsWith() != null && !person.getFriendsWith().isEmpty()) {
      LOGGER.debug("Friend conversion [ getFriendsWith() " + person.getFriendsWith().size() + " ]");
      friendsWith.addAll(convertDoListToDtoList(person.getFriendsWith(), mem));
    }

    dto.setFriends(friends);
    dto.setFriendsWith(friendsWith);
    LOGGER.debug("End of converion " + dto);
    return dto;
  }

  /**
   * @param friends
   * @return
   */
  private static List<PersonDto> convertDoListToDtoList(final List<PersonDo> friends,
      final MemoryPerson<PersonDto, PersonDo> mem) {
    final List<PersonDto> result = new ArrayList<PersonDto>();
    for (final PersonDo itDo : friends) {
      LOGGER.debug("Friend conversion " + itDo);
      final PersonDto tmpDto = createDto(itDo);

      if (!mem.isInstanciated(tmpDto)) {
        mem.addInstanciated(tmpDto);
      }

      final PersonDto instance = mem.getInstanceOf(tmpDto);
      result.add(instance);

      final List<PersonDto> f = new ArrayList<PersonDto>();
      final List<PersonDto> fw = new ArrayList<PersonDto>();
      if (!mem.isExplored(itDo)) {
        mem.addExplored(itDo);
        if (itDo.getFriends() != null && !itDo.getFriends().isEmpty()) {
          f.addAll(convertDoListToDtoList(itDo.getFriends(), mem));
        }
        if (itDo.getFriendsWith() != null && !itDo.getFriendsWith().isEmpty()) {
          fw.addAll(convertDoListToDtoList(itDo.getFriendsWith(), mem));
        }
        tmpDto.setFriends(f);
        tmpDto.setFriendsWith(fw);
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
    LOGGER.debug("Begin of list conversion " + listPersonDo.size());
    final List<PersonDto> list = new ArrayList<PersonDto>();
    for (final PersonDo itDo : listPersonDo) {
      list.add(convertDotoDto(itDo));
    }
    LOGGER.debug("End of list conversion " + list);
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