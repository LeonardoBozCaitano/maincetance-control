package br.com.maintenance.persistence.repositories.person;

import br.com.maintenance.persistence.config.RepositoryBaseImpl;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.QPersonEntity;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.Optional;

public class PersonRepositoryImpl extends RepositoryBaseImpl implements PersonRepositoryCustom {

    @Override
    public Optional<PersonEntity> getPersonByName(String name) {
        QPersonEntity qPersonEntity = QPersonEntity.personEntity;

        JPAQuery<PersonEntity> query = select(qPersonEntity).from(qPersonEntity);
        query.where(qPersonEntity.name.eq(name));

        return Optional.ofNullable(query.fetchFirst());
    }

    @Override
    public Optional<PersonEntity> getPersonByEmail(String email) {
        QPersonEntity qPersonEntity = QPersonEntity.personEntity;

        JPAQuery<PersonEntity> query = select(qPersonEntity).from(qPersonEntity);
        query.where(qPersonEntity.email.eq(email));

        return Optional.ofNullable(query.fetchFirst());
    }

    @Override
    public Optional<PersonEntity> getPersonByPhone(String phone) {
        QPersonEntity qPersonEntity = QPersonEntity.personEntity;

        JPAQuery<PersonEntity> query = select(qPersonEntity).from(qPersonEntity);
        query.where(qPersonEntity.phone.eq(phone));

        return Optional.ofNullable(query.fetchFirst());
    }
}
