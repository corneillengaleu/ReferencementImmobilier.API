package org.projectTutore.refencementImmobilier.image_immobilier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageImmobilierRepository extends JpaRepository<ImageImmobilierEntity, Long> {

    @Query(value = "SELECT * FROM image_immobilier WHERE immobilier_id = :id", nativeQuery = true)
    Optional<ImageImmobilierEntity> findByImmobilier_IDNative(@Param("id") long id);




}

