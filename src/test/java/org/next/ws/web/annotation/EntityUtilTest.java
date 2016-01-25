package org.next.ws.web.annotation;

import com.mysema.query.jpa.impl.JPAQuery;
import org.junit.Test;
import org.next.ws.util.QueryingUtil;
import org.next.ws.web.card.QCardEntity;

import java.util.HashMap;
import java.util.Map;

public class EntityUtilTest {
    @Test
    public void querying() throws NoSuchFieldException, IllegalAccessException {
        JPAQuery query = new JPAQuery();
        Map<String, Object> params = new HashMap<>();
        params.put("attack", 1);
        params.put("attack<", 1);
        params.put("attack<=", 1);
        params.put("name%", "그림");
        System.out.println(QueryingUtil.mapQuery(query, QCardEntity.cardEntity, params));
    }
}