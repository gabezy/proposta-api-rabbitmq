package br.com.gabezy.propostaapi.services;

import br.com.gabezy.propostaapi.config.properties.PropostaApiProperties;
import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificaoService {

    private final RabbitTemplate rabbitTemplate;
    private final PropostaApiProperties properties;

    public void notificarPropostaPendente(PropostaResponseDTO propostaResponseDTO) {
        rabbitTemplate.convertAndSend(properties.getExchangeName().getPropostaPendente(), "", propostaResponseDTO);
    }

}
