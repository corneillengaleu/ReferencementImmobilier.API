package org.projectTutore.refencementImmobilier.immobilier;

import org.projectTutore.refencementImmobilier.bailleur.BailleurRepository;
import org.projectTutore.refencementImmobilier.categorie_Immobilier.CategorieImmobilierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImmobilierService {

    @Autowired
    private ImmobilierRepository immobilierRepository;

    @Autowired
    private BailleurRepository bailleurRepository;

    @Autowired
    private CategorieImmobilierRepository categorieImmobilierRepository;

    // ✅ Ajouter un bien immobilier
    public ImmobilierDto createImmobilier(ImmobilierDto dto, Long bailleurId) {
        ImmobilierEntity entity = convertToEntity(dto);
        entity.setBailleur(bailleurRepository.findById(bailleurId).orElseThrow(
                () -> new RuntimeException("Bailleur introuvable")
        ));
        entity.setCategorieImmobilier(categorieImmobilierRepository.findById(dto.getCategorieImmobilierId()).orElseThrow(
                () -> new RuntimeException("Catégorie immobilière introuvable")
        ));
        ImmobilierEntity saved = immobilierRepository.save(entity);
        return convertToDto(saved);
    }

    // ✅ Récupérer tous les biens
    public List<ImmobilierDto> getAll() {
        return immobilierRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ✅ Supprimer un bien
    public void deleteImmobilier(Long id) {
        immobilierRepository.deleteById(id);
    }

    // ✅ Rechercher des biens selon des critères
    public List<ImmobilierDto> search(String keyword) {
        return immobilierRepository.findAll().stream()
                .filter(i ->
                        containsIgnoreCase(i.getTitre(), keyword) ||
                                containsIgnoreCase(i.getAdresse(), keyword) ||
                                containsIgnoreCase(i.getAvis(), keyword) ||
                                String.valueOf(i.getSuperficie()).contains(keyword) ||
                                String.valueOf(i.getNbreChambre()).contains(keyword)
                )
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 🔄 Entity → DTO
    private ImmobilierDto convertToDto(ImmobilierEntity entity) {
        ImmobilierDto dto = new ImmobilierDto();
        dto.setTitre(entity.getTitre());
        dto.setDescription(entity.getDescription());
        dto.setSuperficie(entity.getSuperficie());
        dto.setNbreChambre(entity.getNbreChambre());
        dto.setAdresse(entity.getAdresse());
        dto.setPris(entity.getPris());
        dto.setAvis(entity.getAvis());
        dto.setDate(entity.getDate());
        dto.setNombre(entity.getNombre());
        dto.setPhotoAccueil(entity.getPhotoAccueil());
        dto.setCategorieImmobilierId(entity.getCategorieImmobilier().getCategorieImmobilierId());
        dto.setBailleurId(entity.getBailleur().getBailleurId());
        return dto;
    }

    // 🔄 DTO → Entity
    private ImmobilierEntity convertToEntity(ImmobilierDto dto) {
        ImmobilierEntity entity = new ImmobilierEntity();
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setSuperficie(dto.getSuperficie());
        entity.setNbreChambre(dto.getNbreChambre());
        entity.setAdresse(dto.getAdresse());
        entity.setPris(dto.getPris());
        entity.setAvis(dto.getAvis());
        entity.setDate(dto.getDate());
        entity.setNombre(dto.getNombre());
        entity.setPhotoAccueil(dto.getPhotoAccueil());
        return entity;
    }

    private boolean containsIgnoreCase(String field, String keyword) {
        return field != null && keyword != null && field.toLowerCase().contains(keyword.toLowerCase());
    }
}
