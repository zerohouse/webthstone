package org.next.ws.web.card;

import com.mysema.query.types.Path;
import org.junit.Test;
import org.next.ws.entity.card.QCardEntity;

public class CardEntityTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        QCardEntity qCardEntity = QCardEntity.cardEntity;
        Path path = (Path) qCardEntity.getClass().getDeclaredField("attack").get(qCardEntity);
        System.out.println(path);

    }

    @Test
    public void tests() throws NoSuchFieldException, IllegalAccessException {
        String a = "caaa";
        System.out.println(a.substring(1));
    }

}