package org.projectTutore.refencementImmobilier.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDto createClient(ClientDto clientDto) {
        ClientEntity entity = convertToEntity(clientDto);
        ClientEntity saved = clientRepository.save(entity);
        return convertToDTO(saved);
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDto> searchClients(String keyword) {
        return clientRepository.findAll().stream()
                .filter(client ->
                        containsIgnoreCase(client.getNom(), keyword) ||
                                containsIgnoreCase(client.getPrenom(), keyword) ||
                                client.getTel().contains(keyword) ||
                                containsIgnoreCase(client.getEmail(), keyword))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔄 Conversion entity → DTO
    public ClientDto convertToDTO(ClientEntity client) {
        ClientDto dto = new ClientDto();
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setTel(client.getTel());
        dto.setEmail(client.getEmail());
        dto.setVille(client.getVille());
        return dto;
    }

    // 🔄 Conversion DTO → entity
    public ClientEntity convertToEntity(ClientDto dto) {
        ClientEntity entity = new ClientEntity();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTel(dto.getTel());
        entity.setEmail(dto.getEmail());
        entity.setVille(dto.getVille());
        return entity;
    }

    // 🔍 Comparaison insensible à la casse
    private boolean containsIgnoreCase(String field, String keyword) {
        return field != null && keyword != null && field.toLowerCase().contains(keyword.toLowerCase());
    }
}
