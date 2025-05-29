package org.projectTutore.refencementImmobilier.categorie_Immobilier;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Données représentant une catégorie d'immobilier")
public class CategorieImmobilierDto {

    @Schema(description = "Nom de la catégorie", example = "Appartement")
    private String nom;

    @Schema(description = "Niveau de standing de la catégorie", example = "Luxe")
    private String standing;

    @Schema(description = "Description détaillée de la catégorie", example = "Appartement situé en centre-ville, proche des commodités")
    private String description;
}
