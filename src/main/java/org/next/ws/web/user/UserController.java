package org.next.ws.web.user;

import org.next.ws.jeo.JeoController;
import org.next.ws.jeo.JeoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@JeoController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserMap userMap;

    @JeoEvent(value = "fb.user", eventDest = "user.update")
    public UserDto registerFbUser(User user, String id, String name, String img) {
        return new UserDto(userMap.update(user, id, name, img));
    }

}
