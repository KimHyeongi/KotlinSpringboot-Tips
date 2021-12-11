package com.tistory.eclipse4j.domain.persist.db.dic.repository

import com.tistory.eclipse4j.domain.persist.db.dic.entity.Dic
import org.springframework.data.jpa.repository.JpaRepository

interface DicRepository : JpaRepository<Dic, Long>
