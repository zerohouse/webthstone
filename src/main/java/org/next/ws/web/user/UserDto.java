package org.next.ws.web.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.web.deck.DeckEntityDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private List<DeckEntityDto> deckList;
    private Long id;
    private String name;
    private String img;
    private String fbId;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.img = user.getImg();
        this.fbId = user.getFbId();
        this.deckList = user.getDeckEntityDtoList();
    }
}
