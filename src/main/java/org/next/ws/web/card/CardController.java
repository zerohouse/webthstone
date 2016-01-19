package org.next.ws.web.card;

import org.next.ws.web.jeo.JeoController;
import org.next.ws.web.jeo.JeoEvent;
import org.next.ws.web.jeo.resolver.JsonParse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@JeoController
public class CardController {

    @Autowired
    CardDao cardDao;

    @JeoEvent("card.list")
    public List<CardEntity> getCardList(Map<String, Object> params) {
        return cardDao.getList(params);
    }

    @JeoEvent("card.save")
    public void saveCard(@JsonParse CardEntity card) {
        System.out.println(card);
    }


}
