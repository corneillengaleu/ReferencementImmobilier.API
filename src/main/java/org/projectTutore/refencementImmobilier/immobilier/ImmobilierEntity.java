package org.projectTutore.refencementImmobilier.immobilier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.projectTutore.refencementImmobilier.bailleur.BailleurEntity;
import org.projectTutore.refencementImmobilier.categorie_Immobilier.CategorieImmobilierEntity;

import java.time.LocalDate;

@Entity
@Table(name = "immobilier")
@Getter
@Setter
public class ImmobilierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long immobilier_Id;

    @NotNull
    private String titre;

    @NotNull
    private String description;

    @NotNull
    private double superficie;

    @NotNull
    private int nbreChambre;

    @NotNull
    private String adresse;

    @NotNull
    private double prix;

    private String avis;

    @NotNull
    private LocalDate date;

    @NotNull
    private int nombre;

    @NotNull
    @Lob
    @Column(name = "photo_accueil")
    private byte[]  photo;

    @ManyToOne
    @JoinColumn(name = "categorieImmobilier_Id", nullable = false)
    @NotNull
    private CategorieImmobilierEntity categorieImmobilier;

    @ManyToOne
    @JoinColumn(name = "bailleur_Id", nullable = false)
    @NotNull
    private BailleurEntity bailleur;
}
