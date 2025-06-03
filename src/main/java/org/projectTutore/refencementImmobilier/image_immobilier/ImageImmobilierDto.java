package org.projectTutore.refencementImmobilier.image_immobilier;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.projectTutore.refencementImmobilier.immobilier.ImmobilierEntity;

@Data
@Schema(description = "Données représentant les images d'un immobilier")
public class ImageImmobilierDto {

    @NotNull
    @Schema(description = "optionel, si on veut donner la surperficie sur une image", example = "Chambre boss 4m * 5m")
    private double superficie;


    @Schema(description = "Image des salle de l'immobilier, tous les images necessaire", example = "photo")
    String  photo;

    @NotNull
    @Schema(description = "Indique l'immobilier concerne", example = " immobilier_Id=1000000")
    private ImmobilierEntity Immobilier;

}
