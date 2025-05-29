package org.projectTutore.refencementImmobilier.categorie_Immobilier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorie_immobilier")
@Getter
@Setter
public class CategorieImmobilierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorieImmobilierId;

    @NotNull
    private String nom;

    @NotNull
    private String standing;

    @NotNull
    private String description;
}
