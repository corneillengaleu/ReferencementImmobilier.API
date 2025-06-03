package org.projectTutore.refencementImmobilier.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(name = "client")
@Getter
@Setter
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_Id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    @Column(unique = true)
    private String tel;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String ville;
}

