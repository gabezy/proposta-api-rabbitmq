package br.com.gabezy.propostaapi.services;

import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private static final String PROPOSTAS_TOPIC = "/propostas";

    public void notificar(PropostaResponseDTO proposta) {
        simpMessagingTemplate.convertAndSend(PROPOSTAS_TOPIC, proposta);
    }

}
