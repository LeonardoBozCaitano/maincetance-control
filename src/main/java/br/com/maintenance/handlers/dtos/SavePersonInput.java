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

    /**
     * Person's name
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Person's address
     */
    @NotNull
    @NotEmpty
    private String adress;

    /**
     * Person's phone
     */
    @NotNull
    @NotEmpty
    private String phone;

    /**
     * Person's email
     */
    @NotNull
    @NotEmpty
    @Email
    private String email;
}
