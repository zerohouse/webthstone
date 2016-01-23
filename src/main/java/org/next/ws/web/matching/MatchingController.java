package org.next.ws.web.matching;

import org.next.ws.core.card.Card;
import org.next.ws.core.game.player.deck.Deck;
import org.next.ws.jeo.JeoController;
import org.next.ws.jeo.JeoEvent;
import org.next.ws.web.deck.DeckEntity;
import org.next.ws.web.repository.DeckEntityRepository;
import org.next.ws.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@JeoController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @Autowired
    DeckEntityRepository deckEntityRepository;

    @JeoEvent(value = "game.play", eventDest = "message")
    public String gamePlay(User user, String heroType, Long deckId) {
        if (user.getFbId() == null)
            return "로그인이 필요한 서비스입니다.";
        DeckEntity deckEntity = deckEntityRepository.findOne(deckId);
        List<Card> cardList = deckEntity.getCardList();
        SocketUserPlayer player = new SocketUserPlayer(user, heroType, new Deck(cardList));
        return matchingService.enqueue(player);
    }
}
