package org.next.ws.web.card;

import org.next.ws.web.repository.CardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardEntityRepository cardEntityRepository;

    public Long save(CardParameterDto card) {
        CardEntity cardEntity = new CardEntity(card);
        cardEntityRepository.save(cardEntity);
        return cardEntity.getId();
    }
}
