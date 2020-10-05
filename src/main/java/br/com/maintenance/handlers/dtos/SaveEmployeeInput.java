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

    @NotNull
    @NotEmpty
    private String personId;

}
