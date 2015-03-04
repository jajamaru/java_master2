/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import presentation.dto.PersonDto;
import presentation.model.PersonForm;
import entity.PersonDo;

/**
 * @author Romain
 *
 */
public class PersonMapper {

  /**
   * Convert a PersonDto object to PersonDo object
   * @param dto to convert
   * @return PersonDo object
   */
  public static PersonDo convertDtoToDo(final PersonDto dto) {
    final PersonDo person = _createDo(dto);
    if (dto.getFriends() != null) {
      final MemoryPerson<PersonDo, PersonDto> mem = new MemoryPerson<PersonDo, PersonDto>();
      person.setFriends(_convertDtotoDo(dto.getFriends(), mem));
    }
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
    for (final Iterator<? extends PersonDto> it = listPersonDto.iterator(); it.hasNext();) {
      final PersonDto tmpDto = it.next();

      final PersonDo tmpDo = _createDo(tmpDto);

      if (!mem.isInstanciated(tmpDo)) {
        mem.addInstanciated(tmpDo);
      }

      final PersonDo instance = mem.getInstanceOf(tmpDo);
      list.add(instance);

      if (!mem.isExplored(tmpDto)) {
        mem.addExplored(tmpDto);
        if (tmpDto.getFriends() != null) {
          tmpDo.setFriends(_convertDtotoDo(tmpDto.getFriends(), mem));
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
    final PersonDo personDo = new PersonDo();
    personDo.setBirthday(dto.getBirthday());
    personDo.setName(dto.getName());
    personDo.setId(dto.getId());
    return personDo;
  }

  /**
   * @param person
   * @return
   */
  private static PersonDto _createDto(final PersonDo person) {
    final PersonDto dto = new PersonDto();
    dto.setBirthday(person.getBirthday());
    dto.setName(person.getName());
    dto.setId(person.getId());
    return dto;
  }

  /**
   * Convert a PesonDo object to PersonDto object
   * @param person do to convert
   * @return PersonDto object
   */
  public static PersonDto convertDotoDto(final PersonDo person) {
    final PersonDto dto = _createDto(person);

    final List<PersonDto> result = new ArrayList<PersonDto>();

    final MemoryPerson<PersonDto, PersonDo> mem = new MemoryPerson<PersonDto, PersonDo>();
    if (person.getFriends() != null) {
      result.addAll(_convertDoToDto(person.getFriends(), mem));
    }
    if (person.getFriendsWith() != null) {
      result.addAll(_convertDoToDto(person.getFriendsWith(), mem));
    }

    dto.setFriends(result);
    return dto;
  }

  /**
   * @param friends
   * @return
   */
  private static List<PersonDto> _convertDoToDto(final List<PersonDo> friends,
      final MemoryPerson<PersonDto, PersonDo> mem) {
    final List<PersonDto> result = new ArrayList<PersonDto>();
    for (final Iterator<PersonDo> it = friends.iterator(); it.hasNext();) {
      final PersonDo tmpDo = it.next();
      final PersonDto tmpDto = _createDto(tmpDo);

      if (!mem.isInstanciated(tmpDto)) {
        mem.addInstanciated(tmpDto);
      }

      final PersonDto instance = mem.getInstanceOf(tmpDto);
      result.add(instance);

      if (!mem.isExplored(tmpDo)) {
        mem.addExplored(tmpDo);
        if (tmpDo.getFriends() != null) {
          tmpDto.setFriends(_convertDoToDto(tmpDo.getFriends(), mem));
        }
        if (tmpDo.getFriendsWith() != null) {
          tmpDto.setFriends(_convertDoToDto(tmpDo.getFriendsWith(), mem));
        }
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
    final List<PersonDto> list = new ArrayList<PersonDto>();
    for (final Iterator<? extends PersonDo> it = listPersonDo.iterator(); it.hasNext();) {
      list.add(convertDotoDto(it.next()));
    }
    return list;
  }

  /**
   * Convert a PersonForm object to PersonDto object
   * @param form to convert
   * @return PersonDto object
   */
  public static PersonDto convertFormToDto(final PersonForm form) {
    final PersonDto dto = new PersonDto();
    dto.setId(form.getId());
    dto.setName(form.getName());
    dto.setBirthday(form.getBirthday());
    return dto;
  }

}

class MemoryPerson<T, U> {

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