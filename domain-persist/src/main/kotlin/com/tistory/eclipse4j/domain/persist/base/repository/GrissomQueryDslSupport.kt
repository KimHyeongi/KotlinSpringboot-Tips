package com.tistory.eclipse4j.domain.persist.base.repository

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

abstract class GrissomQueryDslSupport(domainClass: Class<*>) :
    QuerydslRepositorySupport(domainClass) {

    @PersistenceContext(unitName = "default")
    override fun setEntityManager(entityManager: EntityManager) = super.setEntityManager(entityManager)
}
