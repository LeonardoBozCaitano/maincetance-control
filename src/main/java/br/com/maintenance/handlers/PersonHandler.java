package br.com.maintenance.handlers;

import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonHandler {

    private PersonService personService;

    PersonHandler(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public PersonEntity savePerson(@RequestBody SavePersonInput input) {
        try {
            return personService.save(input);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonEntity> getAll() {
        try {
            return personService.getAll();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
