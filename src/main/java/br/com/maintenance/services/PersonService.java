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

    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    public PersonEntity validateAndSave(SavePersonInput input) {
        if (personRepository.getPersonByName(input.getName()).isPresent()) throw Exceptions.personNameAlreadyInUse();
        if (personRepository.getPersonByEmail(input.getEmail()).isPresent()) throw Exceptions.personEmailAlreadyInUse();
        if (personRepository.getPersonByPhone(input.getPhone()).isPresent()) throw Exceptions.personPhoneAlreadyInUse();

        return personRepository.save(personMapper.toEntity(input));
    }

    public PersonEntity getOneOrFail(String id) {
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw Exceptions.personNotFound();
        }
    }
}
