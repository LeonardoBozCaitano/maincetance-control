package br.com.maintenance.services;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.handlers.mappers.PersonMapper;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.repositories.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    /**
     * Return every saved person.
     *
     * @return product list
     */
    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    /**
     * Save a person with the validations below.
     *  - Already have a person with this name
     *  - Already have a person with this number
     *  - Already have a person with this email
     *
     * @param input Person atributes object
     * @return Saved person.
     */
    public PersonEntity validateAndSave(SavePersonInput input) {
        if (personRepository.getPersonByName(input.getName()).isPresent()) throw Exceptions.personNameAlreadyInUse();
        if (personRepository.getPersonByEmail(input.getEmail()).isPresent()) throw Exceptions.personEmailAlreadyInUse();
        if (personRepository.getPersonByPhone(input.getPhone()).isPresent()) throw Exceptions.personPhoneAlreadyInUse();

        return personRepository.save(personMapper.toEntity(input));
    }

    /**
     * Return the person by id, and throws exception if doesn't find.
     *
     * @return Person Entity
     */
    public PersonEntity getOneOrFail(String id) {
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw Exceptions.personNotFound();
        }
    }
}
