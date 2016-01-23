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
    public String gamePlay(User user, String hero, Integer deckId) {
        if (user.getFbId() == null)
            return "로그인이 필요한 서비스입니다.";
        if(deckId == null)
            return "덱을 선택해주세요.";
        if(hero == null)
            return "영웅 능력을 선택해 주세요.";
        DeckEntity deckEntity = deckEntityRepository.findOne(new Long(deckId)); //[TODO 파라미터 타입]
        List<Card> cardList = deckEntity.getCardList();
        SocketUserPlayer player = new SocketUserPlayer(user, hero, new Deck(cardList));
        return matchingService.enqueue(player);
    }
}
