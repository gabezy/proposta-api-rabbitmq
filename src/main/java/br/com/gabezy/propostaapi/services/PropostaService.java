package br.com.gabezy.propostaapi.services;

import br.com.gabezy.propostaapi.domain.dtos.PropostaRequestDTO;
import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import br.com.gabezy.propostaapi.domain.models.Proposta;
import br.com.gabezy.propostaapi.domain.models.Usuario;
import br.com.gabezy.propostaapi.domain.view.PropostaDadosView;
import br.com.gabezy.propostaapi.repositories.PropostaRepository;
import br.com.gabezy.propostaapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificaoService notificaoService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PropostaResponseDTO save(final PropostaRequestDTO request)  {
        Usuario usuario = usuarioRepository.findByCpf(request.cpf())
                .orElseGet(() -> usuarioRepository.save(new Usuario(request)));

        Proposta proposta = propostaRepository.save(new Proposta(request, usuario.id()));

        PropostaResponseDTO propostaResponse = convertToResponse(proposta, usuario);

        notificaoService.notificarPropostaPendente(propostaResponse);

        return propostaResponse;
    }

    public List<PropostaResponseDTO> obterPropostas() {
        return propostaRepository.findAllPropostaDadosView().stream()
                .map(this::convertDadosViewToResponseDTO)
                .toList();
    }

    private PropostaResponseDTO convertToResponse(final Proposta proposta, final Usuario usuario) {
        return new PropostaResponseDTO(
                proposta.id(),
                usuario.nome(),
                usuario.sobrenome(),
                usuario.telefone(),
                usuario.cpf(),
                usuario.renda(),
                formatarValorSolicitado(proposta.valorSolicitado()),
                proposta.prazo(),
                proposta.aprovada(),
                proposta.observacao());
    }

    private PropostaResponseDTO convertDadosViewToResponseDTO(PropostaDadosView view) {
        return new PropostaResponseDTO(
                view.id(),
                view.nome(),
                view.sobrenome(),
                view.telefone(),
                view.cpf(),
                view.renda(),
                formatarValorSolicitado(view.valorSolicitado()),
                view.prazo(),
                view.aprovada(),
                view.observacao());
    }

    private String formatarValorSolicitado(Double valorSolicitado) {
        return NumberFormat.getCurrencyInstance().format(valorSolicitado);
    }

}
