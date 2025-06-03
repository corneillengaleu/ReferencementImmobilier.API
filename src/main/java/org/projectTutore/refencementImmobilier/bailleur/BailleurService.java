package org.projectTutore.refencementImmobilier.bailleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BailleurService {

    @Autowired
    private BailleurRepository bailleurRepository;

    public BailleurDto createBailleur(BailleurDto dto) throws IOException {

        BailleurEntity entity = new BailleurEntity();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTel(dto.getTel());
        entity.setEmail(dto.getEmail());
        entity.setCni(dto.getCni());
        entity.setResidence(dto.getResidence());
        byte[] imageBytes;
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {

            if (dto.getPhoto().startsWith("http")) {
                // Cas : URL HTTP/HTTPS
                URL url = new URL(dto.getPhoto());
                try (InputStream input = url.openStream()) {
                    imageBytes = input.readAllBytes();
                }
            } else {
                // Cas : chemin local
                Path path = Paths.get(dto.getPhoto());
                imageBytes = Files.readAllBytes(path);
            }
            entity.setPhoto(imageBytes);

        } else {
            throw new IllegalArgumentException("La photo est obligatoire.");
        }

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
    public ResponseEntity<byte[]> getPhotoById(Long id) {
        return bailleurRepository.findById(id)
                .map(entity -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    return new ResponseEntity<>(entity.getPhoto(), headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
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
        dto.setResidence(entity.getResidence());
        return dto;
    }

    private BailleurEntity convertToEntity(BailleurDto dto) throws IOException {
        BailleurEntity entity = new BailleurEntity();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTel(dto.getTel());
        entity.setEmail(dto.getEmail());
        entity.setCni(dto.getCni());
        entity.setResidence(dto.getResidence());
        entity.setPhoto(dto.getPhoto().getBytes());
        return entity;
    }



    }
