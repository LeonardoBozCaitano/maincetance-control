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

    /**
     * Client's person Id.
     */
    @NotEmpty
    @NotNull
    private String clientId;

    /**
     * Product Id
     */
    @NotEmpty
    @NotNull
    private String productId;

    /**
     * Employee Id.
     */
    @NotEmpty
    @NotNull
    private String employeeId;

    /**
     * Product's problem description
     */
    @NotEmpty
    @NotNull
    private String description;
}
