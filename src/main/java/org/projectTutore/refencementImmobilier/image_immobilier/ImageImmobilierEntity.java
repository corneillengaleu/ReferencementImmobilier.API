package org.projectTutore.refencementImmobilier.image_immobilier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.projectTutore.refencementImmobilier.immobilier.ImmobilierEntity;

@Entity
@Table(name = "Image_immobilier")
@Getter
@Setter
public class ImageImmobilierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ImageImmobilier_Id;


    @NotNull
    private double superficie;


    @Lob // Indique que ce champ sera stocké comme un BLOB
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "Immobilier_Id", nullable = false)
    @NotNull
    private ImmobilierEntity Immobilier;


}
