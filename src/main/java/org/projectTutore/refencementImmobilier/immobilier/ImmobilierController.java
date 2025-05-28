package org.projectTutore.refencementImmobilier.immobilier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/immobiliers")
public class ImmobilierController {

    @Autowired
    private ImmobilierService immobilierService;

    // ✅ Créer un bien immobilier
    @PostMapping("/create")
    public ImmobilierDto createImmobilier(
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) {
        return immobilierService.createImmobilier(dto, bailleurId, categorieImmobilierId);
    }

    // ✅ Mettre à jour un bien immobilier
    @PutMapping("/update/{id}")
    public ImmobilierDto updateImmobilier(
            @PathVariable Long id,
            @RequestBody ImmobilierDto dto,
            @RequestParam Long bailleurId,
            @RequestParam Long categorieImmobilierId
    ) {
        return immobilierService.updateImmobilier(id, dto, bailleurId, categorieImmobilierId);
    }

    // ✅ Supprimer un bien immobilier
    @DeleteMapping("/delete/{id}")
    public void deleteImmobilier(@PathVariable Long id) {
        immobilierService.deleteImmobilier(id);
    }

    // ✅ Récupérer un bien immobilier par ID
    @GetMapping("/{id}")
    public Optional<ImmobilierDto> getById(@PathVariable Long id) {
        return immobilierService.getById(id);
    }

    // ✅ Lister tous les biens immobiliers
    @GetMapping("/all")
    public List<ImmobilierDto> getAllImmobiliers() {
        return immobilierService.getAll();
    }

    // ✅ Rechercher des biens par mot-clé
    @GetMapping("/search")
    public List<ImmobilierDto> searchImmobilier(@RequestParam String keyword) {
        return immobilierService.search(keyword);
    }
}
