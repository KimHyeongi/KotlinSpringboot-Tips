package com.tistory.eclipse4j.domain.persist.card.repository

import com.tistory.eclipse4j.domain.persist.card.entity.CardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<CardEntity, Long>
