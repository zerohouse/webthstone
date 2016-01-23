package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.next.ws.core.game.Game;
import org.next.ws.jeo.Jeo;
import org.next.ws.web.deck.DeckEntity;
import org.next.ws.web.deck.DeckEntityDto;
import org.next.ws.web.matching.SocketUserPlayer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "fbId"))
public class User {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<DeckEntity> deckList;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String img;

    @NotNull
    @Column
    private String fbId;

    @Transient
    private SockJSSocket sockJSSocket;

    @Transient
    private SocketUserPlayer player;

    @Transient
    private Game game;

    public User(SockJSSocket sockJSSocket) {
        this.sockJSSocket = sockJSSocket;
    }

    public void setPlayer(SocketUserPlayer player) {
        this.player = player;
    }

    public void sendMessage(String message) {
        Jeo.event(sockJSSocket, Jeo.MESSAGE, message);
    }

    public List<DeckEntityDto> getDeckEntityDtoList() {
        if (deckList == null)
            return new ArrayList<>();
        return deckList.stream().map(DeckEntityDto::new).collect(Collectors.toList());
    }

}
