package br.com.maintenance.handlers.mappers;

import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class PersonMapperTest {

    @InjectMocks
    private PersonMapper personMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        SavePersonInput input = new SavePersonInput("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");

        PersonEntity entity = personMapper.toEntity(input);

        assertEquals(input.getAdress(), entity.getAdress());
        assertEquals(input.getEmail(), entity.getEmail());
        assertEquals(input.getName(), entity.getName());
        assertEquals(input.getPhone(), entity.getPhone());
    }

}