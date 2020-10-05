package br.com.maintenance.handlers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SavePersonInput {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String adress;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    @Email
    private String email;
}
