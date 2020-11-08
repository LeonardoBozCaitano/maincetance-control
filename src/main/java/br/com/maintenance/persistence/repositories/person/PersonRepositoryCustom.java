package br.com.maintenance.persistence.repositories.person;

import br.com.maintenance.persistence.entities.PersonEntity;

import java.util.Optional;

public interface PersonRepositoryCustom {

    Optional<PersonEntity> getPersonByName(String name);

    Optional<PersonEntity> getPersonByEmail(String email);

    Optional<PersonEntity> getPersonByPhone(String phone);
}
