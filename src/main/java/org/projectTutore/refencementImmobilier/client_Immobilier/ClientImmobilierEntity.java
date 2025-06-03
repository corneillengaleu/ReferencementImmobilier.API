package org.projectTutore.refencementImmobilier.client_Immobilier;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.projectTutore.refencementImmobilier.client.ClientEntity;
import org.projectTutore.refencementImmobilier.immobilier.ImmobilierEntity;

import java.time.LocalDate;


@Entity
@Table(name = "client_immobilier")
@Getter
@Setter
public class ClientImmobilierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientImmobilier_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "immobilier_id")
    private ImmobilierEntity immobilier;
    private String avis;
    @NotNull
    private LocalDate date;

    private Boolean isTake ;


}

