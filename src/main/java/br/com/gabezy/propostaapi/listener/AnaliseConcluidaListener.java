package br.com.gabezy.propostaapi.listener;

import br.com.gabezy.propostaapi.domain.dtos.PropostaMessageDTO;
import br.com.gabezy.propostaapi.domain.models.Proposta;
import br.com.gabezy.propostaapi.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnaliseConcluidaListener {

    private final PropostaRepository propostaRepository;

    @RabbitListener(queues = "${proposta-api.queue-name.proposta-concluida-proposta}")
    public void processarPropostaAnalisada(PropostaMessageDTO propostaMessage) {
        propostaRepository.save(convertMessageToModel(propostaMessage));
    }

    private Proposta convertMessageToModel(PropostaMessageDTO propostaMessage) {
        return new Proposta(
                propostaMessage.id(),
                propostaMessage.valorSolicitado(),
                propostaMessage.prazo(),
                propostaMessage.aprovada(),
                propostaMessage.integrado(),
                propostaMessage.observacao(),
                propostaMessage.usuarioId()
        );
    }

}
