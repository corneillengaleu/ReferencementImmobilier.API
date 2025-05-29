package org.projectTutore.refencementImmobilier.client;

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
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Créer un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomApiResponse<ClientDto>> createClient(@RequestBody ClientDto clientDto) {
        ClientDto createdClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(
                new CustomApiResponse<>(true, "Client créé avec succès", createdClient, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Lister tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients récupérée avec succès")
    @GetMapping("/list/all")
    public ResponseEntity<CustomApiResponse<List<ClientDto>>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Liste des clients récupérée avec succès", clients, HttpStatus.OK)
        );
    }

    @Operation(summary = "Obtenir un client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<CustomApiResponse<ClientDto>> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(dto -> ResponseEntity.ok(
                        new CustomApiResponse<>(true, "Client trouvé", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "Client non trouvé", null, HttpStatus.NOT_FOUND)));
    }

    @Operation(summary = "Supprimer un client")
    @ApiResponse(responseCode = "200", description = "Client supprimé avec succès")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Client supprimé avec succès", null, HttpStatus.OK)
        );
    }

    @Operation(summary = "Rechercher des clients par mot-clé")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public ResponseEntity<CustomApiResponse<List<ClientDto>>> searchClients(@RequestParam String keyword) {
        List<ClientDto> results = clientService.searchClients(keyword);
        return ResponseEntity.ok(
                new CustomApiResponse<>(true, "Résultats de la recherche", results, HttpStatus.OK)
        );
    }
}
