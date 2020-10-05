package br.com.maintenance.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity implements Serializable {

    public ProductEntity(String name, String type, String brand){
        this.setName(name);
        this.setType(type);
        this.setBrand(brand);
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column
    private String id;

    @NotEmpty
    @NotNull
    private String type;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String brand;
}
