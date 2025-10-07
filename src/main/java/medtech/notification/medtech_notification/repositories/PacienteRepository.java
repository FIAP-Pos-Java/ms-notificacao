package medtech.notification.medtech_notification.repositories;

import medtech.notification.medtech_notification.entities.Paciente;
import medtech.notification.medtech_notification.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByLogin_Id(UUID loginId);
    Optional<Usuario> findByLoginEmail(String email);
}