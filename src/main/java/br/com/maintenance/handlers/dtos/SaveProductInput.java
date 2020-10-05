package br.com.maintenance.handlers.dtos;

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
public class SaveProductInput {

    /**
     * Product's name
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Product's type
     */
    @NotEmpty
    @NotNull
    private String type;

    /**
     * Product's brand
     */
    @NotEmpty
    @NotNull
    private String brand;
}
