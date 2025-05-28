package org.projectTutore.refencementImmobilier.bailleur;

import org.projectTutore.refencementImmobilier.Configuration.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bailleurs")
public class BailleurController {

    @Autowired
    private BailleurService bailleurService;

    @PostMapping
    public ResponseEntity<ApiResponse<BailleurDto>> createBailleur(@RequestBody BailleurDto bailleurDto) {
        BailleurDto saved = bailleurService.createBailleur(bailleurDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Bailleur créé avec succès", saved, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BailleurDto>>> getAllBailleurs() {
        List<BailleurDto> bailleurs = bailleurService.getAllBailleurs();
        return ResponseEntity.ok(new ApiResponse<>(true, "Liste des bailleurs récupérée", bailleurs, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BailleurDto>> getBailleurById(@PathVariable Long id) {
        return bailleurService.getBailleurById(id)
                .map(dto -> ResponseEntity.ok(new ApiResponse<>(true, "Bailleur trouvé", dto, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Bailleur non trouvé", null, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBailleur(@PathVariable Long id) {
        bailleurService.deleteBailleur(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bailleur supprimé", null, HttpStatus.OK));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BailleurDto>>> searchBailleurs(@RequestParam String keyword) {
        List<BailleurDto> result = bailleurService.searchBailleurs(keyword);
        return ResponseEntity.ok(new ApiResponse<>(true, "Résultats de la recherche", result, HttpStatus.OK));
    }
}
