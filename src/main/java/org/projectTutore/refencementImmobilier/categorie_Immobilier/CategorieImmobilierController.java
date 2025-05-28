package org.projectTutore.refencementImmobilier.categorie_Immobilier;


import org.projectTutore.refencementImmobilier.Configuration.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-immobilier")
public class CategorieImmobilierController {

    @Autowired
    private CategorieImmobilierService service;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CategorieImmobilierDto>> create(@RequestBody CategorieImmobilierDto dto) {
        CategorieImmobilierDto created = service.create(dto);
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Catégorie créée avec succès", created, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/list/all")
    public ResponseEntity<ApiResponse<List<CategorieImmobilierDto>>> getAll() {
        List<CategorieImmobilierDto> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Liste récupérée", list, HttpStatus.OK));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<CategorieImmobilierDto>> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(dto -> ResponseEntity.ok(new ApiResponse<>(true, "Catégorie trouvée", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Catégorie non trouvée", null, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Catégorie supprimée", null, HttpStatus.OK));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CategorieImmobilierDto>>> search(@RequestParam String keyword) {
        List<CategorieImmobilierDto> results = service.search(keyword);
        return ResponseEntity.ok(new ApiResponse<>(true, "Résultats de la recherche", results, HttpStatus.OK));
    }
}
