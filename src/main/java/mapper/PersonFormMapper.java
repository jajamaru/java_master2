/**
 * 
 */
package mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import presentation.dto.PersonDto;
import presentation.model.Friend;
import presentation.model.FriendForm;
import presentation.model.PersonForm;

/**
 * @author romain
 *
 */
public class PersonFormMapper {

  /**
   * Create a FriendForm object
   * @param refDto
   * @param list
   * @return
   */
  public static FriendForm createFriendForm(final PersonDto refDto, final List<PersonDto> list) {
    final FriendForm form = new FriendForm();
    form.setPerson(createPersonForm(refDto));
    form.setFriends(_createListFriendForm(refDto, list));
    return form;
  }

  /**
   * Create a PersonForm object
   * @param dto
   * @return
   */
  public static PersonForm createPersonForm(final PersonDto dto) {
    final PersonForm form = new PersonForm();
    form.setId(dto.getId());
    form.setName(dto.getName());
    form.setBirthday(dto.getBirthday());
    form.setSexe(dto.getSexe());
    return form;
  }

  /**
   * Convert a PersonForm object to a PersonDto object
   * @param form to convert
   * @return PersonDto object
   */
  public static PersonDto convertPersonFormToDto(final PersonForm form) {
    final PersonDto dto = new PersonDto();
    dto.setId(form.getId());
    dto.setName(form.getName());
    dto.setBirthday(form.getBirthday());
    dto.setSexe(form.getSexe());
    return dto;
  }

  /**
   * Convert a FriendForm object to a PersonDto object
   * @param form
   * @return
   */
  public static PersonDto convertFriendFormToDto(final FriendForm form) {
    final PersonDto dto = new PersonDto();
    dto.setBirthday(form.getPerson().getBirthday());
    dto.setId(form.getPerson().getId());
    dto.setName(form.getPerson().getName());
    dto.setSexe(form.getPerson().getSexe());

    final List<PersonDto> newFriends = new ArrayList<PersonDto>();
    final List<Friend> friends = form.getFriends();
    for (final Iterator<Friend> it = friends.iterator(); it.hasNext();) {
      final Friend f = it.next();
      if (f.isFriendShip()) {
        newFriends.add(_convertFriendToDto(f));
      }
    }

    dto.setFriends(newFriends);
    return dto;
  }

  private static PersonDto _convertFriendToDto(final Friend form) {
    final PersonDto dto = new PersonDto();
    dto.setName(form.getPerson().getName());
    dto.setBirthday(form.getPerson().getBirthday());
    dto.setId(form.getPerson().getId());
    dto.setSexe(form.getPerson().getSexe());
    return dto;
  }

  private static List<Friend> _createListFriendForm(final PersonDto refDto,
      final List<PersonDto> list) {
    final List<Friend> form = new ArrayList<Friend>();
    final List<PersonDto> friends = refDto.getFriends();
    for (final Iterator<PersonDto> it = list.iterator(); it.hasNext();) {
      final PersonDto person = it.next();
      if (!person.equals(refDto)) {
        final Friend newFriend = new Friend();
        newFriend.setPerson(createPersonForm(person));
        newFriend.setFriendShip(friends.contains(person));
        form.add(newFriend);
      }
    }
    return form;
  }

}
