package medtech.notification.medtech_notification.repositories;

import medtech.notification.medtech_notification.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    Optional<Medico> findByLogin_Id(UUID loginId);
    Optional<Medico> findByLoginEmail(String email);
}