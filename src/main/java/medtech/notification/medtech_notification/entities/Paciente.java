package medtech.notification.medtech_notification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_paciente")
public final class Paciente extends Usuario{
}
