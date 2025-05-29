package org.projectTutore.refencementImmobilier.immobilier;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Data
@Schema(description = "Données représentant un bien immobilier")
public class ImmobilierDto {

    @Schema(description = "Titre du bien immobilier", example = "Appartement moderne à Paris")
    private String titre;

    @Schema(description = "Description détaillée du bien immobilier", example = "Appartement lumineux avec balcon et vue sur la Tour Eiffel")
    private String description;

    @Schema(description = "Superficie en mètres carrés", example = "85.5")
    private double superficie;

    @Schema(description = "Nombre de chambres", example = "3")
    private int nbreChambre;

    @Schema(description = "Adresse complète du bien immobilier", example = "10 rue de la Paix, 75002 Paris")
    private String adresse;

    @Schema(description = "Prix du bien immobilier", example = "350000")
    private double pris;

    @Schema(description = "Avis ou commentaire sur le bien", example = "Très bon état, quartier calme")
    private String avis;

    @Schema(description = "Date de disponibilité ou de création", example = "2024-04-01")
    private LocalDate date;

    @Schema(description = "Nombre disponible (ex : nombre d’unités)", example = "1")
    private int nombre;

    @Schema(description = "URL ou chemin de la photo principale du bien", example = "https://exemple.com/photos/accueil.jpg")
    private String photoAccueil;

    @Schema(description = "Identifiant de la catégorie immobilière associée", example = "5")
    private Long categorieImmobilierId;

    @Schema(description = "Identifiant du bailleur associé", example = "10")
    private Long bailleurId;
}
