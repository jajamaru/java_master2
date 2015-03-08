/**
 * 
 */
package mapper;

import java.util.ArrayList;
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

    final List<Friend> listOfFriend = new ArrayList<Friend>();
    final List<Friend> listOfFriendWith = new ArrayList<Friend>();
    for (final PersonDto itDto : list) {
      if (!itDto.equals(refDto)) {
        if (refDto.getFriendsWith().contains(itDto)) {
          listOfFriendWith.add(_createFriend(itDto, true));
        } else if (refDto.getFriends().contains(itDto)) {
          listOfFriend.add(_createFriend(itDto, true));
        } else {
          listOfFriend.add(_createFriend(itDto, false));
        }
      }
    }

    form.setFriends(listOfFriend);
    form.setFriendsWith(listOfFriendWith);
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

  private static PersonDto _createDto(final FriendForm form) {
    final PersonDto dto = new PersonDto();
    dto.setBirthday(form.getPerson().getBirthday());
    dto.setId(form.getPerson().getId());
    dto.setName(form.getPerson().getName());
    dto.setSexe(form.getPerson().getSexe());
    return dto;
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
    final PersonDto dto = _createDto(form);

    final List<Friend> friends = form.getFriends();
    final List<Friend> friendsWith = form.getFriendsWith();

    if (friends != null) {
      dto.setFriends(_convertFriendListToDtoList(friends));
    }

    if (friendsWith != null) {
      dto.setFriendsWith(_convertFriendListToDtoList(friendsWith));
    }

    return dto;
  }

  private static List<PersonDto> _convertFriendListToDtoList(final List<Friend> list) {
    final List<PersonDto> friends = new ArrayList<PersonDto>();
    for (final Friend itFriend : list) {
      if (itFriend.isFriendShip()) {
        friends.add(_convertFriendToDto(itFriend));
      }
    }
    return friends;

  }

  private static PersonDto _convertFriendToDto(final Friend form) {
    final PersonDto dto = new PersonDto();
    dto.setName(form.getPerson().getName());
    dto.setBirthday(form.getPerson().getBirthday());
    dto.setId(form.getPerson().getId());
    dto.setSexe(form.getPerson().getSexe());
    return dto;
  }

  private static Friend _createFriend(final PersonDto dto, final boolean friendShip) {
    final Friend friend = new Friend();
    friend.setFriendShip(friendShip);
    friend.setPerson(createPersonForm(dto));
    return friend;
  }

}
