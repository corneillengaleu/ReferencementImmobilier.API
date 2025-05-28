package org.projectTutore.refencementImmobilier.client;

import org.projectTutore.refencementImmobilier.Configuration.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // ✅ Créer un client
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ClientDto>> createClient(@RequestBody ClientDto clientDto) {
        ClientDto createdClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Client créé avec succès", createdClient, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    // ✅ Lister tous les clients
    @GetMapping("/list/all")
    public ResponseEntity<ApiResponse<List<ClientDto>>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Liste des clients récupérée avec succès", clients, HttpStatus.OK)
        );
    }

    // ✅ Obtenir un client par ID
    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(dto -> ResponseEntity.ok(
                        new ApiResponse<>(true, "Client trouvé", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Client non trouvé", null, HttpStatus.NOT_FOUND)));
    }

    // ✅ Supprimer un client
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client supprimé avec succès", null, HttpStatus.OK)
        );
    }

    // ✅ Recherche de clients
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ClientDto>>> searchClients(@RequestParam String keyword) {
        List<ClientDto> results = clientService.searchClients(keyword);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Résultats de la recherche", results, HttpStatus.OK)
        );
    }
}
