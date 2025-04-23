package br.com.gabezy.propostaapi.services;

import br.com.gabezy.propostaapi.config.properties.PropostaApiProperties;
import br.com.gabezy.propostaapi.domain.dtos.PropostaMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificaoService {

    private final RabbitTemplate rabbitTemplate;
    private final PropostaApiProperties properties;

    public void notificarPropostaPendente(PropostaMessageDTO propostaMessage) {
        rabbitTemplate.convertAndSend(properties.getExchangeName().getPropostaPendente(), "", propostaMessage);
    }

}
