package br.com.gabezy.propostaapi.services;

import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificaoService {

    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchangePropostaPendente;

    public void notificarPropostaPendente(PropostaResponseDTO propostaResponseDTO) {
        rabbitTemplate.convertAndSend(fanoutExchangePropostaPendente.getName(), "", propostaResponseDTO);
    }

}
