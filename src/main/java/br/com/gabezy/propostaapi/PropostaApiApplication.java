package br.com.gabezy.propostaapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class PropostaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropostaApiApplication.class, args);
    }

    @PostConstruct
    void init() {
        Locale.setDefault(Locale.of("pt", "BR"));
    }

}
