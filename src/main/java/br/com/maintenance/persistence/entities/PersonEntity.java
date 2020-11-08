package br.com.maintenance.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "person")
@NoArgsConstructor
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column
    private String id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String adress;

    @NotEmpty
    @NotNull
    private String phone;

    @Email
    @NotEmpty
    @NotNull
    private String email;

    public PersonEntity(String name, String adress, String phone, String email) {
        this.setName(name);
        this.setAdress(adress);
        this.setPhone(phone);
        this.setName(name);
        this.setEmail(email);
    }
}
