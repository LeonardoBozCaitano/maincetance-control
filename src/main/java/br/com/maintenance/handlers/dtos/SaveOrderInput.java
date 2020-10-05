package br.com.maintenance.handlers.dtos;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SaveOrderInput {

    @NotEmpty
    @NotNull
    private String clientId;

    @NotEmpty
    @NotNull
    private String productId;

    @NotEmpty
    @NotNull
    private String employeeId;

    @NotEmpty
    @NotNull
    private String description;
}
