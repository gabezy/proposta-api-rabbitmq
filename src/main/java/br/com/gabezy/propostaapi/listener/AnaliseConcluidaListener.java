package br.com.gabezy.propostaapi.listener;

import br.com.gabezy.propostaapi.domain.dtos.PropostaMessageDTO;
import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import br.com.gabezy.propostaapi.repositories.PropostaRepository;
import br.com.gabezy.propostaapi.services.WebSocketService;
import br.com.gabezy.propostaapi.utils.FormatterValorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnaliseConcluidaListener {

    private final PropostaRepository propostaRepository;
    private final WebSocketService webSocketService;

    @RabbitListener(queues = "${proposta-api.queue-name.proposta-concluida-proposta}")
    public void processarPropostaAnalisada(PropostaMessageDTO propostaMessage) {
        propostaRepository.atualizarPropostaAnalisada(propostaMessage.id(), propostaMessage.aprovada(), propostaMessage.observacao());
        webSocketService.notificar(convertMessageToResponse(propostaMessage));
    }

    private PropostaResponseDTO convertMessageToResponse(PropostaMessageDTO propostaMessage) {
        return new PropostaResponseDTO(
                propostaMessage.id(),
                propostaMessage.nome(),
                propostaMessage.sobrenome(),
                "telefone",
                propostaMessage.cpf(),
                propostaMessage.renda(),
                FormatterValorUtils.formatarValorParaBRL(propostaMessage.valorSolicitado()),
                propostaMessage.prazo(),
                propostaMessage.aprovada(),
                propostaMessage.observacao()
        );
    }

}
