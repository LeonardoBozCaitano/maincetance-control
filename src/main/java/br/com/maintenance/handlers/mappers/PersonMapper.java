package br.com.maintenance.handlers.mappers;

import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.persistence.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonEntity toEntity(SavePersonInput dto) {
        return new PersonEntity(dto.getName(), dto.getAdress(), dto.getPhone(), dto.getEmail());
    }
}
