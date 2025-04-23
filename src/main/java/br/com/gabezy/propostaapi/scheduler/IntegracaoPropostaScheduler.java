package br.com.gabezy.propostaapi.scheduler;

import br.com.gabezy.propostaapi.domain.dtos.PropostaMessageDTO;
import br.com.gabezy.propostaapi.repositories.PropostaRepository;
import br.com.gabezy.propostaapi.services.NotificaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntegracaoPropostaScheduler {

    private final PropostaRepository propostaRepository;
    private final NotificaoService notificaoService;

//    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void integrarPropostas() {
        propostaRepository.findAllPropostaDadosViewByIntegradoIsFalse().forEach(propostaDadosView -> {
            try {
                notificaoService.notificarPropostaPendente(new PropostaMessageDTO(propostaDadosView));
                propostaRepository.updateIntegradoById(true, propostaDadosView.id());
            } catch (RuntimeException e) {
                log.error("An error has occurred while trying to integrate the proposal with id: {}", propostaDadosView.id(), e);
            }
        });
    }

}
