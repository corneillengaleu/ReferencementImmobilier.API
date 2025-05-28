package org.projectTutore.refencementImmobilier.bailleur;

import lombok.Data;

@Data
public class BailleurDto {
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String cni;
    private String photo;
    private String residence;
}
