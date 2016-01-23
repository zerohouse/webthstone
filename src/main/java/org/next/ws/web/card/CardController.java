package org.next.ws.web.card;

import org.next.ws.core.action.serialize.WsActionProperties;
import org.next.ws.core.scanner.ComponentScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    CardService cardService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CardEntityDto> getCardList(Map<String, Object> params) {
        return cardDao.getList(params);
    }

    @RequestMapping(value = "/effect", method = RequestMethod.GET)
    public Map<String, WsActionProperties> getEffectList() {
        return ComponentScanner.getActionInfo();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long saveCard(@RequestBody CardParameterDto cardParameterDto) {
        return cardService.save(cardParameterDto);
    }

}
