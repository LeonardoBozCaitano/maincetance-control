package br.com.maintenance.persistence.repositories.person;

import br.com.maintenance.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PersonRepositoryCustom {

    /**
     * Get a person by the name
     *
     * @param name to search
     * @return an optional with the person entity or empty.
     */
    Optional<PersonEntity> getPersonByName(String name);

    /**
     * Get a person by the email
     *
     * @param email to search
     * @return an optional with the person entity or empty.
     */
    Optional<PersonEntity> getPersonByEmail(String email);

    /**
     * Get a person by the phone
     *
     * @param phone to search
     * @return an optional with the person entity or empty.
     */
    Optional<PersonEntity> getPersonByPhone(String phone);
}
