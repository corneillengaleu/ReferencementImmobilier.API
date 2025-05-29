package org.projectTutore.refencementImmobilier.immobilier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/immobiliers")
@Tag(name = "Immobiliers", description = "Opérations liées à la gestion des biens immobiliers")
public class ImmobilierController {

    @Autowired
    private ImmobilierService immobilierService;

    @Operation(summary = "Créer un bien immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/create")
    public ImmobilierDto createImmobilier(
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) {
        return immobilierService.createImmobilier(dto, bailleurId, categorieImmobilierId);
    }

    @Operation(summary = "Mettre à jour un bien immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PutMapping("/update/{id}")
    public ImmobilierDto updateImmobilier(
            @PathVariable Long id,
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) {
        return immobilierService.updateImmobilier(id, dto, bailleurId, categorieImmobilierId);
    }

    @Operation(summary = "Supprimer un bien immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteImmobilier(@PathVariable Long id) {
        immobilierService.deleteImmobilier(id);
    }

    @Operation(summary = "Récupérer un bien immobilier par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier trouvé"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé")
    })
    @GetMapping("/{id}")
    public Optional<ImmobilierDto> getById(@PathVariable Long id) {
        return immobilierService.getById(id);
    }

    @Operation(summary = "Lister tous les biens immobiliers")
    @ApiResponse(responseCode = "200", description = "Liste des biens immobiliers récupérée")
    @GetMapping("/all")
    public List<ImmobilierDto> getAllImmobiliers() {
        return immobilierService.getAll();
    }

    @Operation(summary = "Rechercher des biens immobiliers par mot-clé")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public List<ImmobilierDto> searchImmobilier(@RequestParam String keyword) {
        return immobilierService.search(keyword);
    }
}
