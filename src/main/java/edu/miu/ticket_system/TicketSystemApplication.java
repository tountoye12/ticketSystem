package edu.miu.ticket_system;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TicketSystemApplication {



	public static void main(String[] args) {


		SpringApplication.run(TicketSystemApplication.class, args);


	}


}
