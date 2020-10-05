package br.com.maintenance.services;

import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.handlers.mappers.PersonMapper;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.repositories.person.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {
        PersonEntity personEntity = new PersonEntity("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");

        List<PersonEntity> responseList = Collections.singletonList(personEntity);
        Mockito.when(personRepository.findAll()).thenReturn(responseList);

        List<PersonEntity> response = personService.getAll();

        assertEquals(1L, response.size());
        assertEquals(personEntity, response.get(0));
    }

    @Test
    public void savePerson_sucess() {
        final String name = "João da Silva";
        final String adress = "Rua 1, Blumenau";
        final String phone = "9999-1111";
        final String email = "joaodasilva@gmail.com";
        SavePersonInput input = new SavePersonInput(name, adress, phone, email);
        PersonEntity personEntity = new PersonEntity(name, adress, phone, email);

        Mockito.when(personRepository.getPersonByName(input.getName())).thenReturn(Optional.empty());
        Mockito.when(personRepository.getPersonByEmail(input.getEmail())).thenReturn(Optional.empty());
        Mockito.when(personRepository.getPersonByPhone(input.getPhone())).thenReturn(Optional.empty());

        Mockito.when(personMapper.toEntity(input)).thenReturn(personEntity);
        Mockito.when(personRepository.save(personEntity)).thenReturn(personEntity);

        PersonEntity output = personService.save(input);

        assertEquals(name, output.getName());
        assertEquals(adress, output.getAdress());
        assertEquals(email, output.getEmail());
        assertEquals(phone, output.getPhone());
    }

    @Test
    public void savePerson_withDuplicatedName() {
        final String name = "João da Silva";
        final String adress = "Rua 1, Blumenau";
        final String phone = "9999-1111";
        final String email = "joaodasilva@gmail.com";
        SavePersonInput input = new SavePersonInput(name, adress, phone, email);
        PersonEntity personEntity = new PersonEntity(name, adress, phone, email);

        Mockito.when(personRepository.getPersonByName(input.getName())).thenReturn(Optional.of(personEntity));

        try {
            PersonEntity output = personService.save(input);
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Person's name already in use");
        }
    }

    @Test
    public void savePerson_withDuplicatedEmail() {
        final String name = "João da Silva";
        final String adress = "Rua 1, Blumenau";
        final String phone = "9999-1111";
        final String email = "joaodasilva@gmail.com";
        SavePersonInput input = new SavePersonInput(name, adress, phone, email);
        PersonEntity personEntity = new PersonEntity(name, adress, phone, email);

        Mockito.when(personRepository.getPersonByName(input.getName())).thenReturn(Optional.empty());
        Mockito.when(personRepository.getPersonByEmail(input.getEmail())).thenReturn(Optional.of(personEntity));

        try {
            PersonEntity output = personService.save(input);
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Person's email already in use");
        }
    }

    @Test
    public void savePerson_withDuplicatedPhone() {
        final String name = "João da Silva";
        final String adress = "Rua 1, Blumenau";
        final String phone = "9999-1111";
        final String email = "joaodasilva@gmail.com";
        SavePersonInput input = new SavePersonInput(name, adress, phone, email);
        PersonEntity personEntity = new PersonEntity(name, adress, phone, email);

        Mockito.when(personRepository.getPersonByName(input.getName())).thenReturn(Optional.empty());
        Mockito.when(personRepository.getPersonByEmail(input.getEmail())).thenReturn(Optional.empty());
        Mockito.when(personRepository.getPersonByPhone(input.getPhone())).thenReturn(Optional.of(personEntity));

        try {
            PersonEntity output = personService.save(input);
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Person's phone already in use");
        }
    }

    @Test
    public void getOneOrFail_success() {
        UUID id = UUID.randomUUID();
        PersonEntity personEntity = new PersonEntity();
        Mockito.when(personRepository.findById(id.toString())).thenReturn(Optional.of(personEntity));

        PersonEntity response = personService.getOneOrFail(id.toString());

        assertEquals(personEntity, response);
    }

    @Test
    public void getOneOrFail_fail() {
        UUID id = UUID.randomUUID();
        Mockito.when(personRepository.findById(id.toString())).thenReturn(Optional.empty());

        try {
            PersonEntity response = personService.getOneOrFail(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Person not found");
        }
    }
}