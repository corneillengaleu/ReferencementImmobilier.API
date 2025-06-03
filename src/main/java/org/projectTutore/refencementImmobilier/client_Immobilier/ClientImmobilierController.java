package org.projectTutore.refencementImmobilier.client_Immobilier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.projectTutore.refencementImmobilier.Configuration.CustomApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ci")
@Tag(name = "Client_immobilier", description = "Gestion de la table de relation client_immobilier")
public class ClientImmobilierController {

    @Autowired
    private ClientImmobilierService clientImmobilierService;

    @Operation(summary = "Créer une relation client_immoblier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Relation Client_immobilier créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomApiResponse<ClientImmobilierDto>> createClient(@RequestBody ClientImmobilierDto clientImmobilierDto) {
        ClientImmobilierDto createdClient = clientImmobilierService.createClient(clientImmobilierDto);
        return new ResponseEntity<>(
                new CustomApiResponse<>(true, "Client créé avec succès", createdClient, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Lister tous les Relation Client_immobilier ")
    @ApiResponse(responseCode = "200", description = "Liste des Relation Client_immobilier  récupérée avec succès")
    @GetMapping("/list/all")
    public ResponseEntity<CustomApiResponse<List<ClientImmobilierDto>>> getAllClients() {
        List<ClientImmobilierDto> clients = clientImmobilierService.getAllClientsImmobilier();
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Liste des Relation Client_immobilier  récupérée avec succès", clients, HttpStatus.OK)
        );
    }

    @Operation(summary = "Obtenir une Relation Client_immobilier  par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relation Client_immobilier  trouvé"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<CustomApiResponse<ClientImmobilierDto>> getClientById(@PathVariable Long id) {
        return clientImmobilierService.getClientImmobilierById(id)
                .map(dto -> ResponseEntity.ok(
                        new CustomApiResponse<>(true, "Relation Client_immobilier  trouvé", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "Relation Client_immobilier  non trouvé", null, HttpStatus.NOT_FOUND)));
    }

    @Operation(summary = "Supprimer une Relation Client_immobilier ")
    @ApiResponse(responseCode = "200", description = "Relation Client_immobilier  supprimé avec succès")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteClient(@PathVariable Long id) {
        clientImmobilierService.deleteClientImmobilier(id);
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Relation Client_immobilier  supprimé avec succès", null, HttpStatus.OK)
        );
    }

    @Operation(summary = "Rechercher des Relation Client_immobilier  par mot-clé")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public ResponseEntity<CustomApiResponse<List<ClientImmobilierDto>>> searchClients(@RequestParam String keyword) {
        List<ClientImmobilierDto> results = clientImmobilierService.searchClientsImmobilier(keyword);
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Résultats de la recherche", results, HttpStatus.OK)
        );
    }
}
