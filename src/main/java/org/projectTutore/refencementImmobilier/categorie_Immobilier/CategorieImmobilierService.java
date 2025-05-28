package org.projectTutore.refencementImmobilier.categorie_Immobilier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategorieImmobilierService {

    @Autowired
    private CategorieImmobilierRepository repository;

    public CategorieImmobilierDto create(CategorieImmobilierDto dto) {
        CategorieImmobilierEntity entity = convertToEntity(dto);
        CategorieImmobilierEntity saved = repository.save(entity);
        return convertToDto(saved);
    }

    public List<CategorieImmobilierDto> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CategorieImmobilierDto> getById(Long id) {
        return repository.findById(id).map(this::convertToDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CategorieImmobilierDto> search(String keyword) {
        return repository.findAll().stream()
                .filter(cat -> containsIgnoreCase(cat.getNom(), keyword)
                        || containsIgnoreCase(cat.getStanding(), keyword)
                        || containsIgnoreCase(cat.getDescription(), keyword))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private boolean containsIgnoreCase(String field, String keyword) {
        return field != null && keyword != null && field.toLowerCase().contains(keyword.toLowerCase());
    }

    private CategorieImmobilierDto convertToDto(CategorieImmobilierEntity entity) {
        CategorieImmobilierDto dto = new CategorieImmobilierDto();
        dto.setNom(entity.getNom());
        dto.setStanding(entity.getStanding());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private CategorieImmobilierEntity convertToEntity(CategorieImmobilierDto dto) {
        CategorieImmobilierEntity entity = new CategorieImmobilierEntity();
        entity.setNom(dto.getNom());
        entity.setStanding(dto.getStanding());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
