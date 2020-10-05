package br.com.maintenance.persistence.repositories.person;

import br.com.maintenance.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String>, PersonRepositoryCustom {

}
