package org.projectTutore.refencementImmobilier.immobilier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmobilierRepository extends JpaRepository<ImmobilierEntity, Long> {
}
