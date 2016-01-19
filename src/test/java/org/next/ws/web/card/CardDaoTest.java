package org.next.ws.web.card;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.next.ws.core.card.Card;
import org.next.ws.web.ApplicationLauncher;
import org.next.ws.web.repository.CardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)
public class CardDaoTest {

    @Autowired
    CardDao dao;

    @Autowired
    CardEntityRepository cardEntityRepository;

    @Before
    public void setup() {
        CardEntity cardEntity  = new CardEntity(4, 4, 5, "서리바람설인");
        cardEntity.setId(1L);
        CardEntity cardEntity1 = new CardEntity(3, 3, 4, "기계거미");
        cardEntity1.setId(2L);
        cardEntityRepository.save(cardEntity);
        cardEntityRepository.save(cardEntity1);
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