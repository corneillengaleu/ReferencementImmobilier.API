package org.projectTutore.refencementImmobilier.immobilier;

import org.projectTutore.refencementImmobilier.bailleur.BailleurEntity;
import org.projectTutore.refencementImmobilier.bailleur.BailleurRepository;
import org.projectTutore.refencementImmobilier.categorie_Immobilier.CategorieImmobilierEntity;
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
    public ImmobilierDto createImmobilier(ImmobilierDto dto, Long bailleurId, Long categorieImmobilierId) {
        ImmobilierEntity entity = convertToEntity(dto);
        entity.setBailleur(getBailleurOrThrow(bailleurId));
        entity.setCategorieImmobilier(getCategorieOrThrow(categorieImmobilierId));
        ImmobilierEntity saved = immobilierRepository.save(entity);
        return convertToDto(saved);
    }

    // ✅ Modifier un bien immobilier
    public ImmobilierDto updateImmobilier(Long id, ImmobilierDto dto, Long bailleurId, Long categorieImmobilierId) {
        ImmobilierEntity existing = immobilierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien immobilier introuvable avec l'ID : " + id));

        existing.setTitre(dto.getTitre());
        existing.setDescription(dto.getDescription());
        existing.setSuperficie(dto.getSuperficie());
        existing.setNbreChambre(dto.getNbreChambre());
        existing.setAdresse(dto.getAdresse());
        existing.setPrix(dto.getPris());
        existing.setAvis(dto.getAvis());
        existing.setDate(dto.getDate());
        existing.setNombre(dto.getNombre());
        existing.setPhotoAccueil(dto.getPhotoAccueil());
        existing.setBailleur(getBailleurOrThrow(bailleurId));
        existing.setCategorieImmobilier(getCategorieOrThrow(categorieImmobilierId));

        ImmobilierEntity updated = immobilierRepository.save(existing);
        return convertToDto(updated);
    }

    // ✅ Supprimer un bien immobilier
    public void deleteImmobilier(Long id) {
        immobilierRepository.deleteById(id);
    }

    // ✅ Obtenir un bien immobilier par ID
    public Optional<ImmobilierDto> getById(Long id) {
        return immobilierRepository.findById(id).map(this::convertToDto);
    }

    // ✅ Lister tous les biens
    public List<ImmobilierDto> getAll() {
        return immobilierRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ✅ Recherche filtrée
    public List<ImmobilierDto> search(String keyword) {
        return immobilierRepository.findAll().stream()
                .filter(i ->
                        containsIgnoreCase(i.getTitre(), keyword) ||
                                containsIgnoreCase(i.getAdresse(), keyword) ||
                                containsIgnoreCase(i.getAvis(), keyword) ||
                                containsIgnoreCase(i.getDescription(), keyword) ||
                                String.valueOf(i.getSuperficie()).contains(keyword) ||
                                String.valueOf(i.getNbreChambre()).contains(keyword)
                )
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 🔁 Conversion DTO → Entity
    private ImmobilierEntity convertToEntity(ImmobilierDto dto) {
        ImmobilierEntity entity = new ImmobilierEntity();
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setSuperficie(dto.getSuperficie());
        entity.setNbreChambre(dto.getNbreChambre());
        entity.setAdresse(dto.getAdresse());
        entity.setPrix(dto.getPris());
        entity.setAvis(dto.getAvis());
        entity.setDate(dto.getDate());
        entity.setNombre(dto.getNombre());
        entity.setPhotoAccueil(dto.getPhotoAccueil());
        return entity;
    }

    // 🔁 Conversion Entity → DTO
    private ImmobilierDto convertToDto(ImmobilierEntity entity) {
        ImmobilierDto dto = new ImmobilierDto();
        dto.setTitre(entity.getTitre());
        dto.setDescription(entity.getDescription());
        dto.setSuperficie(entity.getSuperficie());
        dto.setNbreChambre(entity.getNbreChambre());
        dto.setAdresse(entity.getAdresse());
        dto.setPris(entity.getPrix());
        dto.setAvis(entity.getAvis());
        dto.setDate(entity.getDate());
        dto.setNombre(entity.getNombre());
        dto.setPhotoAccueil(entity.getPhotoAccueil());
        dto.setBailleurId(entity.getBailleur().getBailleurId());
        dto.setCategorieImmobilierId(entity.getCategorieImmobilier().getCategorieImmobilierId());
        return dto;
    }

    // 🔍 Vérification sensible à la casse
    private boolean containsIgnoreCase(String field, String keyword) {
        return field != null && keyword != null && field.toLowerCase().contains(keyword.toLowerCase());
    }

    // 🔐 Getters sécurisés
    private BailleurEntity getBailleurOrThrow(Long id) {
        return bailleurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bailleur introuvable avec l'ID : " + id));
    }

    private CategorieImmobilierEntity getCategorieOrThrow(Long id) {
        return categorieImmobilierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie immobilière introuvable avec l'ID : " + id));
    }
}
