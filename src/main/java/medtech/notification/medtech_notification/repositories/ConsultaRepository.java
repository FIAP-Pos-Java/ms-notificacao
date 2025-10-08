package medtech.notification.medtech_notification.repositories;

import medtech.notification.medtech_notification.dto.ConsultaDTO;
import medtech.notification.medtech_notification.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

        @Query("""
        SELECT new medtech.notification.medtech_notification.dto.ConsultaDTO (
            c.id,
            c.dataHora,
            c.status,
            c.medicoId,
            c.pacienteId
        )
        FROM Consulta c
        WHERE c.dataHora BETWEEN :agora AND :daquiUmDia
    """)
        List<ConsultaDTO> findByDataBetween(LocalDateTime agora, LocalDateTime daquiUmDia);
}
