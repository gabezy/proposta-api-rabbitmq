package br.com.gabezy.propostaapi;

import br.com.gabezy.propostaapi.config.properties.PropostaApiProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties({PropostaApiProperties.class})
public class PropostaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropostaApiApplication.class, args);
    }

    @PostConstruct
    void init() {
        Locale.setDefault(Locale.of("pt", "BR"));
    }

}
