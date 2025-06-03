package org.projectTutore.refencementImmobilier.immobilier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.projectTutore.refencementImmobilier.Configuration.CustomApiResponse;
import org.projectTutore.refencementImmobilier.bailleur.BailleurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<CustomApiResponse<ImmobilierDto>> createImmobilier(
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(true, "immobilier créé avec succès", immobilierService.createImmobilier(dto, bailleurId, categorieImmobilierId), HttpStatus.CREATED));

    }

    @Operation(summary = "Mettre à jour un bien immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomApiResponse<ImmobilierDto>>  updateImmobilier(
            @PathVariable Long id,
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomApiResponse<>(true, "immobilier mis a jour avec  succès", immobilierService.updateImmobilier(id, dto, bailleurId, categorieImmobilierId), HttpStatus.CREATED));


    }

    @Operation(summary = "Supprimer un bien immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteImmobilier(@PathVariable Long id) {
        immobilierService.deleteImmobilier(id);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "immobilier supprimé", null, HttpStatus.OK));


    }

    @Operation(summary = "Récupérer un bien immobilier par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien immobilier trouvé"),
            @ApiResponse(responseCode = "404", description = "Bien immobilier non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ImmobilierDto>> getById(@PathVariable Long id) {
        return immobilierService.getById(id)
                .map(dto -> ResponseEntity.ok(new CustomApiResponse<>(true, "immobilier trouvé", dto, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "immobilier non trouvé", null, HttpStatus.NOT_FOUND)));

    }

    @Operation(summary = "Lister tous les biens immobiliers")
    @ApiResponse(responseCode = "200", description = "Liste des biens immobiliers récupérée")
    @GetMapping("/all")
    public ResponseEntity<CustomApiResponse<List<ImmobilierDto>>> getAllImmobiliers() {
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Liste des immobilier récupérée", immobilierService.getAll(), HttpStatus.OK));

    }

    @Operation(summary = "Rechercher des biens immobiliers par mot-clé")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public ResponseEntity<CustomApiResponse<List<ImmobilierDto>>> searchImmobilier(@RequestParam String keyword) {
        List<ImmobilierDto> result = immobilierService.search(keyword);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Résultats de la recherche", result, HttpStatus.OK));

    }


    @Operation(summary = "Affichage de la photo  d'accueil de l'immobilier")
    @ApiResponse(responseCode = "200", description = "photo accueil")
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return immobilierService.getPhotoById(id);

    }
}
