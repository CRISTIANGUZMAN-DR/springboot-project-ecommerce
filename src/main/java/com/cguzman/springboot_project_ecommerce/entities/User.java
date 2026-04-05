package com.cguzman.springboot_project_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "El name no puede estar nulo")
    @NotBlank(message = "El name no puede estar vacio")
    @Size(min = 3, message = "El nombre debe ser mayor a 3 letras")
    @Pattern(regexp = "^[a-zA-Z\s]+$", message = "El campo solo debe contener letras")
    private String name;

    @NotNull(message = "El email no puede estar nulo")
    @NotBlank(message = "El email no puede estar vacio")
    @Size(min = 5, message = "La longitud del email debe ser mayor a 5 caracteres")
    @Column(unique = true)
    private String email;

    @NotNull(message = "El password no puede estar nulo")
    @NotBlank(message = "El password no puede estar vacio")
    private String password;

    private Boolean active = true;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
