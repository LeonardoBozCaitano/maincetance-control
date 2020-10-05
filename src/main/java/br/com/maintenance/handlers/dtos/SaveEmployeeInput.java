package br.com.maintenance.handlers.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeInput {

    /**
     * Person Id.
     */
    @NotNull
    @NotEmpty
    private String personId;

}
