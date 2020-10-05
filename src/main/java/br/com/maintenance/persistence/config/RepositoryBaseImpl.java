package br.com.maintenance.persistence.config;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RepositoryBaseImpl {

    @PersistenceContext
    public EntityManager entityManager;

    public <U> JPAQuery<U> select(Expression<U> expression) {
        return new JPAQuery<>(entityManager).select(expression);
    }
}
