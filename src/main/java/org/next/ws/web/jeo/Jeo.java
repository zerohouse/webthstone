package org.next.ws.web.jeo;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.next.ws.util.Util;

import java.util.Map;

/**
 * Json Event Object
 * <p>
 * 제이슨으로 이벤트 전달 및 실행.
 */
@Getter
@Setter
@NoArgsConstructor
public class Jeo {
    public static final String MESSAGE = "message";
    private String type;
    private Map<String, Object> params;
    private Object result;

    public Jeo(String type, Object result) {
        this.type = type;
        this.result = result;
    }

    public static void event(SockJSSocket sockJSSocket, String event, Object result) {
        Jeo jeo = new Jeo(event, result);
        try {
            sockJSSocket.write(Buffer.buffer(Util.OBJECT_MAPPER.writeValueAsString(jeo)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
