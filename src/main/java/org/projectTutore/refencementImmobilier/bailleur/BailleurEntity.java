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
    private Long bailleurId;

    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String tel;
    private String email;
    @NotNull
    private String cni;
    @NotNull
    private String photo;
    @NotNull
    private String residence;
}
