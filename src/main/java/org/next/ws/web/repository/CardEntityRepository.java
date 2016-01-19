package org.next.ws.web.repository;

import org.next.ws.web.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardEntityRepository extends JpaRepository<CardEntity, Long> {
}
