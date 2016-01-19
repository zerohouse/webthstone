package org.next.ws.web.card;

import org.next.ws.web.repository.CardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardDao cardDao;

    @Autowired
    CardEntityRepository cardEntityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<CardEntity> getCardList(Map<String, Object> params) {
        return cardDao.getList(params);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long saveCard(CardEntity card) {
        cardEntityRepository.save(card);
        return card.getId();
    }

}
