package org.next.ws.web.deck;

import org.next.ws.web.card.CardEntity;
import org.next.ws.web.repository.CardEntityRepository;
import org.next.ws.web.repository.DeckEntityRepository;
import org.next.ws.web.repository.DeckHasCardRepository;
import org.next.ws.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deck")
public class DeckController {


    @Autowired
    DeckEntityRepository deckEntityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardEntityRepository cardEntityRepository;

    @Autowired
    DeckHasCardRepository deckHasCardRepository;

    @RequestMapping(method = RequestMethod.GET)
    public DeckEntityDto getDeck(Long deckId) {
        return new DeckEntityDto(deckEntityRepository.findOne(deckId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public DeckEntityDto makeDeck(String fbId) {
        DeckEntity deckEntity = new DeckEntity();
        deckEntity.setUser(userRepository.findByFbId(fbId));
        deckEntityRepository.save(deckEntity);
        return new DeckEntityDto(deckEntity);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Long putCardToDeck(Long deckId, Long cardId) {
        DeckEntity deck = deckEntityRepository.findOne(deckId);
        CardEntity cardEntity = cardEntityRepository.findOne(cardId);
        DeckHasCard deckHasCard = new DeckHasCard();
        deckHasCard.setCard(cardEntity);
        deckHasCard.setDeck(deck);
        deckHasCardRepository.save(deckHasCard);
        return deckHasCard.getId();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Long putCardToDeck(Long deckHasCardId) {
        DeckHasCard deckHasCard = deckHasCardRepository.findOne(deckHasCardId);
        deckHasCardRepository.delete(deckHasCard);
        return deckHasCard.getId();
    }

    @RequestMapping(value = "/name", method = RequestMethod.PUT)
    public DeckEntityDto getDeck(Long deckId, String name) {
        DeckEntity deckEntity = deckEntityRepository.findOne(deckId);
        deckEntity.setName(name);
        return new DeckEntityDto(deckEntity);
    }


}
