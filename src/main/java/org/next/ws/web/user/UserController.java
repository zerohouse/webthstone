package org.next.ws.web.user;

import org.next.ws.jeo.JeoController;
import org.next.ws.jeo.JeoEvent;

@JeoController
public class UserController {

    @JeoEvent("fb.user")
    public void registerFbUser(User user, String id, String name, String img){
        if(id!=null)
            user.setId(id);
        if(name!=null)
            user.setName(name);
        if(img!=null)
            user.setImg(img);
        System.out.println(user);
    }

}
