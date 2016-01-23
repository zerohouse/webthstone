package org.next.ws.web.repository;

import org.next.ws.web.deck.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckEntityRepository extends JpaRepository<DeckEntity, Long> {
}
