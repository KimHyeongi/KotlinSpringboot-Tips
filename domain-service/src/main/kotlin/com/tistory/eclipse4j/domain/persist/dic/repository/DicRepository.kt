package com.tistory.eclipse4j.domain.persist.dic.repository

import com.tistory.eclipse4j.domain.persist.dic.entity.DicEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DicRepository : JpaRepository<DicEntity, Long>
