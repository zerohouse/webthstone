package org.next.ws.util;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import org.next.ws.core.StaticValues;

import java.util.Map;

public class QueryingUtil {

    public static final String LIKE = "%";
    public static final String BIGGER = ">";
    public static final String BIGGER_EQUAL = ">=";
    public static final String SMALLER = "<";
    public static final String SMALLER_EQUAL = "<=";
    public static final String PAGE = "page";

    public static JPAQuery mapQuery(JPAQuery query, EntityPathBase pathBase, Map<String, Object> params) {
        query = query.from(pathBase).limit(StaticValues.DEFAULT_PAGE_SIZE);
        if (params == null)
            return query;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                if (entry.getValue() == null)
                    continue;
                if (entry.getKey().equals(PAGE)) {
                    query.offset(Long.parseLong(entry.getValue().toString()) * StaticValues.DEFAULT_PAGE_SIZE);
                    continue;
                }
                Class<?> valClazz = entry.getValue().getClass();
                query = getProcessEntry(query, pathBase, entry, valClazz);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return query;
    }

    private static JPAQuery getProcessEntry(JPAQuery query, EntityPathBase pathBase, Map.Entry<String, Object> entry, Class<?> valClazz) throws IllegalAccessException, NoSuchFieldException {
        if (valClazz.equals(String.class))
            query = stringPath(query, pathBase, entry);
        if (valClazz.equals(Integer.class) || valClazz.equals(Long.class) || valClazz.equals(Float.class))
            query = numberPath(query, pathBase, entry);
        return query;
    }

    private static JPAQuery numberPath(JPAQuery query, EntityPathBase pathBase, Map.Entry<String, Object> entry) throws IllegalAccessException, NoSuchFieldException {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (key.endsWith(BIGGER)) {
            NumberPath path = (NumberPath) getPathBase(pathBase, key, BIGGER);
            query = query.where(path.gt((Number) value));
            return query;
        }
        if (key.endsWith(BIGGER_EQUAL)) {
            NumberPath path = (NumberPath) getPathBase(pathBase, key, BIGGER_EQUAL);
            query = query.where(path.goe((Number) value));
            return query;
        }
        if (key.endsWith(SMALLER)) {
            NumberPath path = (NumberPath) getPathBase(pathBase, key, SMALLER);
            query = query.where(path.lt((Number) value));
            return query;
        }
        if (key.endsWith(SMALLER_EQUAL)) {
            NumberPath path = (NumberPath) getPathBase(pathBase, key, SMALLER_EQUAL);
            query = query.where(path.loe((Number) value));
            return query;
        }
        NumberPath path = (NumberPath) pathBase.getClass().getDeclaredField(key).get(pathBase);
        query = query.where(path.eq(value));
        return query;
    }

    private static JPAQuery stringPath(JPAQuery query, EntityPathBase pathBase, Map.Entry<String, Object> entry) throws IllegalAccessException, NoSuchFieldException {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (key.contains(LIKE)) {
            StringPath path = (StringPath) getPathBase(pathBase, key, LIKE);
            query = query.where(path.like(LIKE + value + LIKE));
            return query;
        }
        StringPath path = (StringPath) pathBase.getClass().getDeclaredField(key).get(pathBase);
        query = query.where(path.eq((String) value));
        return query;
    }

    private static Object getPathBase(EntityPathBase pathBase, String key, String signal) throws IllegalAccessException, NoSuchFieldException {
        return pathBase.getClass().getDeclaredField(key.replace(signal, "")).get(pathBase);
    }


}
