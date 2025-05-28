package org.projectTutore.refencementImmobilier.immobilier;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ImmobilierDto {
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
    private Long categorieImmobilierId;
    private Long bailleurId;
}
