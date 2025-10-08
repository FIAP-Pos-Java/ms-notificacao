package medtech.notification.medtech_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MedtechNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedtechNotificationApplication.class, args);
	}
}
