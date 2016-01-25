package org.next.ws.web.card;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.next.ws.ApplicationLauncher;
import org.next.ws.web.repository.CardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)
public class CardDaoTest {

    @Autowired
    CardDao dao;

    @Autowired
    CardEntityRepository cardEntityRepository;

    @Before
    public void setup() {
//        CardEntity cardEntity  = new CardEntity(4, 4, 5, "서리바람설인");
//        cardEntity.setFbId(1L);
//        CardEntity cardEntity1 = new CardEntity(3, 3, 4, "기계거미");
//        cardEntity1.setFbId(2L);
//        cardEntityRepository.save(cardEntity);
//        cardEntityRepository.save(cardEntity1);
    }


    @Test
    public void testGetList() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name%", "서리");
        System.out.println(dao.getList(params).get(0));
        assertEquals(1, dao.getList(params).size());
    }
    @Test
    public void testGetList2() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("attack", 4);
        System.out.println(dao.getList(params).get(0));
        assertEquals(1, dao.getList(params).size());
    }
    @Test
    public void testGetList3() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("attack<=", 4);
        System.out.println(dao.getList(params).get(0));
        assertEquals(2, dao.getList(params).size());
    }
}