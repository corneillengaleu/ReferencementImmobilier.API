package org.projectTutore.refencementImmobilier.image_immobilier;

import org.projectTutore.refencementImmobilier.categorie_Immobilier.CategorieImmobilierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageImmobilierService {

    @Autowired
    private ImageImmobilierRepository repository;

    @Autowired
    private CategorieImmobilierRepository categorieImmobilierRepository;

    public ImageImmobilierDto create(ImageImmobilierDto dto) throws IOException {
        ImageImmobilierEntity entity = new ImageImmobilierEntity();
        entity.setImmobilier(dto.getImmobilier());
        entity.setSuperficie(dto.getSuperficie());
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
        ImageImmobilierEntity saved = repository.save(entity);
        return convertToDto(saved);
    }

    public List<ImageImmobilierDto> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ImageImmobilierDto> getById(Long id) {
        return repository.findById(id).map(this::convertToDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public ResponseEntity<byte[]> getPhotoById(Long id) {
        return repository.findByImmobilier_IDNative(id)
                .map(entity -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    return new ResponseEntity<>(entity.getPhoto(), headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }




    private ImageImmobilierDto convertToDto(ImageImmobilierEntity entity) {
        ImageImmobilierDto dto = new ImageImmobilierDto();
        dto.setImmobilier(entity.getImmobilier());
        dto.setSuperficie(entity.getSuperficie());
        return dto;
    }

    private ImageImmobilierEntity convertToEntity(ImageImmobilierDto dto) {
        ImageImmobilierEntity entity = new ImageImmobilierEntity();
        entity.setImmobilier(dto.getImmobilier());
        entity.setSuperficie(dto.getSuperficie());
        return entity;
    }
}
