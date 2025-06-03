package org.projectTutore.refencementImmobilier.image_immobilier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.projectTutore.refencementImmobilier.Configuration.CustomApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/image")
@Tag(name = "Immobiliers", description = "Opérations liées à la gestion des images immobiliers")
public class ImageImmobilierController {

    @Autowired
    private ImageImmobilierService service;

    @Operation(summary = "Créer une nouvelle images immobilier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "images créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomApiResponse<ImageImmobilierDto>> create(@RequestBody ImageImmobilierDto dto) throws IOException {
        ImageImmobilierDto created = service.create(dto);
        return new ResponseEntity<>(
                new CustomApiResponse<>(true, "images créée avec succès", created, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Lister toutes les images immobilier")
    @ApiResponse(responseCode = "200", description = "Liste récupérée")
    @GetMapping("/list/all")
    public ResponseEntity<CustomApiResponse<List<ImageImmobilierDto>>> getAll() {
        List<ImageImmobilierDto> list = service.getAll();
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Liste récupérée", list, HttpStatus.OK));
    }

    @Operation(summary = "Récupérer une images immobilier par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "images trouvée"),
            @ApiResponse(responseCode = "404", description = "images non trouvée")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<CustomApiResponse<ImageImmobilierDto>> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(dto -> ResponseEntity.ok(new CustomApiResponse<>(true, "images trouvée", dto, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(false, "images non trouvée", null, HttpStatus.NOT_FOUND)));
    }

    @Operation(summary = "Supprimer une images immobilier par ID")
    @ApiResponse(responseCode = "200", description = "images supprimée")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "images supprimée", null, HttpStatus.OK));
    }

    @Operation(summary = "Afficher les images des immobilier")
    @ApiResponse(responseCode = "200", description = "image immobiliers")
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return service.getPhotoById(id);

    }

}
