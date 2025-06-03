package org.projectTutore.refencementImmobilier.client_Immobilier;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.projectTutore.refencementImmobilier.client.ClientEntity;
import org.projectTutore.refencementImmobilier.immobilier.ImmobilierEntity;

import java.time.LocalDate;

@Data
@Schema(description = "Données représentant un client")
public class ClientImmobilierDto {

    @Schema(description = " * Recuperer le client(ou utilisateur) concerne", example = "Brice")
    @NotNull
    private ClientEntity client;


    @Schema(description = " * Recuoerer l'immobilier concerne", example = " Villa Corneille")
    @NotNull
    private ImmobilierEntity immobilier;


    @Schema(description = "Recuperer les avis de client sur  un immobilier precis", example = "Cette Villa est tes jolie et propre (client Brice et promotteur Corneille")
    private String avis;


    @Schema(description = " * Ici recuperer la date  directement lors de l'operation", example = "Now()<--sql par exemple pour recuperer la date encours ")
    @NotNull
    private LocalDate date;


    @Schema(description = " * Ici champ boolean pour checked si l'utilisateur est devenu client(a loue l'immobilier))", example = "Y <--yes ou N <-- NO")
    private Boolean aLouer ;

}
