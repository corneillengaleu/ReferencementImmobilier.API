package org.projectTutore.refencementImmobilier.immobilier;

import jakarta.persistence.*;
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
    private Long immobilierId;

    private String titre;
    private String description;
    private double superficie;
    private int nbreChambre;
    private String adresse;
    private double pris;
    private String avis;
    private LocalDate date;
    private int nombre;
    private String photoAccueil;

    @ManyToOne
    @JoinColumn(name = "categorieImmobilierId", nullable = false)
    private CategorieImmobilierEntity categorieImmobilier;

    @ManyToOne
    @JoinColumn(name = "bailleurId", nullable = false)
    private BailleurEntity bailleur;
}
