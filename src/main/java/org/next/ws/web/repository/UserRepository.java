package org.next.ws.web.repository;

import org.next.ws.web.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFbId(String fbId);
}
