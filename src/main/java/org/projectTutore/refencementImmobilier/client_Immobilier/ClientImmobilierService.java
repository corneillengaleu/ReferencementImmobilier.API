package org.projectTutore.refencementImmobilier.client_Immobilier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientImmobilierService {

    @Autowired
    private ClientImmobilierRepository clientImmobilierRepository;

    public ClientImmobilierDto createClient(ClientImmobilierDto clientImmobilierDto) {
        ClientImmobilierEntity entity = convertToEntity(clientImmobilierDto);
        ClientImmobilierEntity saved = clientImmobilierRepository.save(entity);
        return convertToDTO(saved);
    }

    public List<ClientImmobilierDto> getAllClientsImmobilier() {
        return clientImmobilierRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClientImmobilierDto> getClientImmobilierById(Long id) {
        return clientImmobilierRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteClientImmobilier(Long id) {
        clientImmobilierRepository.deleteById(id);
    }

    public List<ClientImmobilierDto> searchClientsImmobilier(String keyword) {
        return clientImmobilierRepository.findAll().stream()
                .filter(clientImmobilier ->
                                containsIgnoreCase(String.valueOf(clientImmobilier.getImmobilier()), keyword) ||
                                containsIgnoreCase(String.valueOf(clientImmobilier.getClient()), keyword) ||
                                        containsIgnoreCase(String.valueOf(clientImmobilier.getIsTake()), keyword) ||
                                        clientImmobilier.getAvis().contains(keyword) ||
                                        containsIgnoreCase(String.valueOf(clientImmobilier.getDate()), keyword))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Conversion entity → DTO
    public ClientImmobilierDto convertToDTO(ClientImmobilierEntity clientImmobilier) {
        ClientImmobilierDto dto = new ClientImmobilierDto();
        dto.setImmobilier(clientImmobilier.getImmobilier());
        dto.setClient(clientImmobilier.getClient());
        dto.setAvis(clientImmobilier.getAvis());
        dto.setDate(clientImmobilier.getDate());
        dto.setALouer(clientImmobilier.getIsTake());
        return dto;
    }

    // 🔄 Conversion DTO → entity
    public ClientImmobilierEntity convertToEntity(ClientImmobilierDto dto) {
        ClientImmobilierEntity entity = new ClientImmobilierEntity();
        entity.setImmobilier(dto.getImmobilier());
        entity.setClient(dto.getClient());
        entity.setAvis(dto.getAvis());
        entity.setDate(dto.getDate());
        entity.setIsTake(dto.getALouer());

        return entity;
    }

    // 🔍 Comparaison insensible à la casse
    private boolean containsIgnoreCase(String field, String keyword) {
        return field != null && keyword != null && field.toLowerCase().contains(keyword.toLowerCase());
    }
}
