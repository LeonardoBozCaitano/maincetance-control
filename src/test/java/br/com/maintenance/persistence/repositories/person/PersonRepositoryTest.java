package br.com.maintenance.persistence.repositories.person;

import br.com.maintenance.App;
import br.com.maintenance.persistence.entities.PersonEntity;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void clean() {
        personRepository.deleteAll();
    }

    @Test
    public void getDuplicatedName() {
        PersonEntity personEntity;
        personEntity = new PersonEntity("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");
        personRepository.save(personEntity);

        personEntity = new PersonEntity("Roberto da silva", "Rua 2, Blumenau", "8888-1111", "roberto123@gmail.com");
        personRepository.save(personEntity);

        Optional<PersonEntity> response = personRepository.getPersonByName(personEntity.getName());

        assertTrue(response.isPresent());
        assertEquals(personEntity.getName(), response.get().getName());
        assertEquals(personEntity.getAdress(), response.get().getAdress());
        assertEquals(personEntity.getEmail(), response.get().getEmail());
        assertEquals(personEntity.getPhone(), response.get().getPhone());
    }

    @Test
    public void getDuplicatedEmail() {
        PersonEntity personEntity;
        personEntity = new PersonEntity("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");
        personRepository.save(personEntity);

        personEntity = new PersonEntity("Roberto da silva", "Rua 2, Blumenau", "8888-1111", "roberto123@gmail.com");
        personRepository.save(personEntity);

        Optional<PersonEntity> response = personRepository.getPersonByEmail(personEntity.getEmail());

        assertTrue(response.isPresent());
        assertEquals(personEntity.getName(), response.get().getName());
        assertEquals(personEntity.getAdress(), response.get().getAdress());
        assertEquals(personEntity.getEmail(), response.get().getEmail());
        assertEquals(personEntity.getPhone(), response.get().getPhone());
    }

    @Test
    public void getDuplicatedPhone() {
        PersonEntity personEntity;
        personEntity = new PersonEntity("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");
        personRepository.save(personEntity);

        personEntity = new PersonEntity("Roberto da silva", "Rua 2, Blumenau", "8888-1111", "roberto123@gmail.com");
        personRepository.save(personEntity);

        Optional<PersonEntity> response = personRepository.getPersonByPhone(personEntity.getPhone());

        assertTrue(response.isPresent());
        assertEquals(personEntity.getName(), response.get().getName());
        assertEquals(personEntity.getAdress(), response.get().getAdress());
        assertEquals(personEntity.getEmail(), response.get().getEmail());
        assertEquals(personEntity.getPhone(), response.get().getPhone());
    }
}