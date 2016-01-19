package org.next.ws.web.user;

import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.next.ws.core.card.exception.CardUnUsableException;
import org.next.ws.core.game.player.Player;
import org.next.ws.jeo.resolver.JeoParameterResolver;
import org.next.ws.jeo.resolver.ParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Parameter;
import java.util.Map;

@JeoParameterResolver
public class PlayerResolver implements ParameterResolver {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isAcceptable(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) {
        return parameter.getType().equals(Player.class);
    }

    @Override
    public Object resolve(Parameter parameter, Map<String, Object> params, SockJSSocket sockJSSocket, String paramName) throws CardUnUsableException {
        User user = userRepository.getUser(sockJSSocket);
        if (user.getPlayer() == null || user.getPlayer().getCamp() == null) {
            user.sendMessage("게임에 참여하지 않았습니다.");
            throw new CardUnUsableException("게임에 참여하지 않았습니다.");
        }
        if (!user.getPlayer().getCamp().isTurn()) {
            user.sendMessage("상대방의 턴입니다.");
            throw new CardUnUsableException("상대방의 턴입니다.");
        }
        return user.getPlayer();
    }

}
