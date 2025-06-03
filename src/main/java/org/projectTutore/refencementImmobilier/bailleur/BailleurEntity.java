package org.projectTutore.refencementImmobilier.bailleur;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bailleur")
@Getter
@Setter
public class BailleurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bailleur_Id;

    @NotNull
    private String nom;
    @NotNull
    private String prenom;

    @Column(unique = true)
    @NotNull
    private String tel;

    @Column(unique = true)
    private String email;


    @NotNull
    @Column(unique = true)
    private String cni;


    @NotNull
    @Lob
    private byte[] photo;

    @NotNull
    private String residence;
}
