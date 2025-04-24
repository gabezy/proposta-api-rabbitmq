package br.com.gabezy.propostaapi.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("proposta-api")
public class PropostaApiProperties {

    private ExchangeName exchangeName;
    private QueueName queueName;

    @Getter
    @Setter
    public static class ExchangeName {
        private String propostaPendente;
        private String propostaConcluida;
        private String propostaPendenteDeadLetter;
    }

    @Getter
    @Setter
    public static class QueueName {
        private String propostaPendenteAnaliseCredito;
        private String propostaPendenteNotificacao;
        private String propostaConcluidaProposta;
        private String propostaConcluidaNotificacao;
        private String propostaPendenteDeadLetter;
    }

}
