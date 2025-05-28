package org.projectTutore.refencementImmobilier.bailleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BailleurService {

    @Autowired
    private BailleurRepository bailleurRepository;

    public BailleurDto createBailleur(BailleurDto bailleurDto) {
        BailleurEntity entity = convertToEntity(bailleurDto);
        BailleurEntity saved = bailleurRepository.save(entity);
        return convertToDto(saved);
    }

    public List<BailleurDto> getAllBailleurs() {
        return bailleurRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<BailleurDto> getBailleurById(Long id) {
        return bailleurRepository.findById(id)
                .map(this::convertToDto);
    }

    public void deleteBailleur(Long id) {
        bailleurRepository.deleteById(id);
    }

    public List<BailleurDto> searchBailleurs(String keyword) {
        return bailleurRepository.findAll().stream()
                .filter(b -> b.getNom().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getPrenom().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getTel().contains(keyword) ||
                        b.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getCni().toLowerCase().contains(keyword.toLowerCase()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BailleurDto convertToDto(BailleurEntity entity) {
        BailleurDto dto = new BailleurDto();
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setTel(entity.getTel());
        dto.setEmail(entity.getEmail());
        dto.setCni(entity.getCni());
        dto.setPhoto(entity.getPhoto());
        dto.setResidence(entity.getResidence());
        return dto;
    }

    private BailleurEntity convertToEntity(BailleurDto dto) {
        BailleurEntity entity = new BailleurEntity();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTel(dto.getTel());
        entity.setEmail(dto.getEmail());
        entity.setCni(dto.getCni());
        entity.setPhoto(dto.getPhoto());
        entity.setResidence(dto.getResidence());
        return entity;
    }
}
