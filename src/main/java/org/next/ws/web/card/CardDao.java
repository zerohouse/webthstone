package org.next.ws.web.card;

import com.mysema.query.jpa.impl.JPAQuery;
import org.next.ws.entity.card.QCardEntity;
import org.next.ws.util.QueryingUtil;
import org.next.ws.web.annotation.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Dao
public class CardDao {

    @PersistenceContext
    private EntityManager entityManager;

    private QCardEntity qCardEntity = QCardEntity.cardEntity;

    public List<CardEntityDto> getList(Map<String, Object> params) {
        JPAQuery query = new JPAQuery(entityManager);
        query = QueryingUtil.mapQuery(query, qCardEntity, params);
        return query.list(qCardEntity).stream().map(CardEntityDto::new).collect(Collectors.toList());
    }
}
