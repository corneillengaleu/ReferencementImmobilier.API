package org.projectTutore.refencementImmobilier.bailleur;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(description = "Données représentant un bailleur")
public class BailleurDto {

    @Schema(description = "Nom du bailleur", example = "Dupont")
    private String nom;

    @Schema(description = "Prénom du bailleur", example = "Jean")
    private String prenom;

    @Schema(description = "Numéro de téléphone du bailleur", example = "++237680641519")
    private String tel;

    @Schema(description = "Adresse email du bailleur", example = "cn@example.com")
    private String email;

    @Schema(description = "Numéro de la Carte Nationale d'Identité (CNI)", example = "123456789")
    private String cni;

    @Schema(description = "URL ou chemin vers la photo du bailleur", example = "http://example.com/photos/cn.jpg")
    @NotNull
    @Lob
    private String photo;

    @Schema(description = "Adresse de résidence du bailleur", example = "Dakar, Sénégal")
    private String residence;
}
