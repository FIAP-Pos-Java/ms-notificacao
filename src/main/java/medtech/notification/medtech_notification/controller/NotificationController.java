package medtech.notification.medtech_notification.controller;

import medtech.notification.medtech_notification.dto.ConsultaAgendadaDTO;
import medtech.notification.medtech_notification.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")

public class NotificationController {
    private final NotificacaoService notificacaoService;

    public NotificationController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping("/consulta-agendada")
    public ResponseEntity<String> notificarConsultaAgendada(@RequestBody ConsultaAgendadaDTO consultaAgendadaDTO) {
        System.out.println("Recebida requisição para notificar consulta agendada: " + consultaAgendadaDTO);
        notificacaoService.enviarNotificacaoConsultaAgendada(consultaAgendadaDTO);
        return ResponseEntity.ok("Notificação de consulta agendada enviada com sucesso.");
    }

    //agora do editada e cancelada
    @PostMapping("/consulta-alterada")
    public ResponseEntity<String> notificarConsultaAlterada(@RequestBody ConsultaAgendadaDTO consultaAgendadaDTO) {
        notificacaoService.enviarNotificacaoConsultaAlterada(consultaAgendadaDTO);
        return ResponseEntity.ok("Notificação de consulta alterada enviada com sucesso.");
    }

    @PostMapping("/consulta-cancelada")
    public ResponseEntity<String> notificarConsultaCancelada(@RequestBody ConsultaAgendadaDTO consultaAgendadaDTO) {
        notificacaoService.enviarNotificacaoConsultaCancelada(consultaAgendadaDTO);
        return ResponseEntity.ok("Notificação de consulta cancelada enviada com sucesso.");
    }
}
