package org.projectTutore.refencementImmobilier.client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Données représentant un client")
public class ClientDto {

    @Schema(description = "Nom du client", example = "Dupont")
    private String nom;

    @Schema(description = "Prénom du client", example = "Jean")
    private String prenom;

    @Schema(description = "Numéro de téléphone du client", example = "+33712345678")
    private String tel;

    @Schema(description = "Adresse email du client", example = "jean.dupont@example.com")
    private String email;

    @Schema(description = "Ville de résidence du client", example = "Paris")
    private String ville;
}
