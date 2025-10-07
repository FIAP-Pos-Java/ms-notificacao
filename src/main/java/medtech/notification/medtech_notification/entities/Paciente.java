package medtech.notification.medtech_notification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "tb_paciente")
public final class Paciente extends Usuario{
}
