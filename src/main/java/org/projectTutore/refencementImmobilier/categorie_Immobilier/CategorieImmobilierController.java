package org.projectTutore.refencementImmobilier.categorie_Immobilier;

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
@RequestMapping("/api/categories-immobilier")
@Tag(name = "Catégories Immobilier", description = "Gestion des catégories immobilier")
public class CategorieImmobilierController {

    @Autowired
    private CategorieImmobilierService service;

    @Operation(summary = "Créer une nouvelle catégorie immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomApiResponse<CategorieImmobilierDto>> create(@RequestBody CategorieImmobilierDto dto) {
        CategorieImmobilierDto created = service.create(dto);
        return new ResponseEntity<>(
                new CustomApiResponse<>(true, "Catégorie créée avec succès", created, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Lister toutes les catégories immobilier")
    @ApiResponse(responseCode = "200", description = "Liste récupérée")
    @GetMapping("/list/all")
    public ResponseEntity<CustomApiResponse<List<CategorieImmobilierDto>>> getAll() {
        List<CategorieImmobilierDto> list = service.getAll();
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Liste récupérée", list, HttpStatus.OK));
    }

    @Operation(summary = "Récupérer une catégorie immobilier par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<CustomApiResponse<CategorieImmobilierDto>> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(dto -> ResponseEntity.ok(new CustomApiResponse<>(true, "Catégorie trouvée", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "Catégorie non trouvée", null, HttpStatus.NOT_FOUND)));
    }

    @Operation(summary = "Supprimer une catégorie immobilier par ID")
    @ApiResponse(responseCode = "200", description = "Catégorie supprimée")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Catégorie supprimée", null, HttpStatus.OK));
    }

    @Operation(summary = "Rechercher des catégories immobilier par mot-clé")
    @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    @GetMapping("/search")
    public ResponseEntity<CustomApiResponse<List<CategorieImmobilierDto>>> search(@RequestParam String keyword) {
        List<CategorieImmobilierDto> results = service.search(keyword);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Résultats de la recherche", results, HttpStatus.OK));
    }
}
