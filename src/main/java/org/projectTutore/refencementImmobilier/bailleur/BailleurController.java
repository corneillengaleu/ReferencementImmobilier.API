package org.projectTutore.refencementImmobilier.bailleur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.projectTutore.refencementImmobilier.Configuration.CustomApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bailleurs")
@Tag(name = "Bailleurs", description = "Opérations liées à la gestion des bailleurs")
public class BailleurController {

    @Autowired
    private BailleurService bailleurService;
    @Autowired
    private BailleurRepository bailleurRepository;


    @Operation(summary = "Créer un nouveau bailleur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bailleur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomApiResponse<BailleurDto>> createBailleur( @RequestBody BailleurDto bailleurDto) throws IOException {
        BailleurDto saved = bailleurService.createBailleur(bailleurDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(true, "Bailleur créé avec succès", saved, HttpStatus.CREATED));
    }

    @Operation(summary = "Lister tous les bailleurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des bailleurs récupérée")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<BailleurDto>>> getAllBailleurs() {
        List<BailleurDto> bailleurs = bailleurService.getAllBailleurs();
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Liste des bailleurs récupérée", bailleurs, HttpStatus.OK));
    }

    @Operation(summary = "Récupérer un bailleur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bailleur trouvé"),
            @ApiResponse(responseCode = "404", description = "Bailleur non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<BailleurDto>> getBailleurById(@PathVariable Long id) {
        return bailleurService.getBailleurById(id)
                .map(dto -> ResponseEntity.ok(new CustomApiResponse<>(true, "Bailleur trouvé", dto, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "Bailleur non trouvé", null, HttpStatus.NOT_FOUND)));
    }

    @Operation(summary = "Supprimer un bailleur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bailleur supprimé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteBailleur(@PathVariable Long id) {
        bailleurService.deleteBailleur(id);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Bailleur supprimé", null, HttpStatus.OK));
    }

    @Operation(summary = "Rechercher des bailleurs par mot-clé")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Résultats de la recherche")
    })
    @GetMapping("/search")
    public ResponseEntity<CustomApiResponse<List<BailleurDto>>> searchBailleurs(@RequestParam String keyword) {
        List<BailleurDto> result = bailleurService.searchBailleurs(keyword);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Résultats de la recherche", result, HttpStatus.OK));
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return bailleurService.getPhotoById(id);

    }

}
