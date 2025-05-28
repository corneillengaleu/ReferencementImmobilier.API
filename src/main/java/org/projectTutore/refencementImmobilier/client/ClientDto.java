package org.projectTutore.refencementImmobilier.client;

import lombok.Data;

@Data
public class ClientDto {
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String ville;
}