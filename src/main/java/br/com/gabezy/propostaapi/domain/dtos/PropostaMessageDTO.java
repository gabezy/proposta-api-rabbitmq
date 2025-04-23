package br.com.gabezy.propostaapi.domain.dtos;

import br.com.gabezy.propostaapi.domain.models.Proposta;
import br.com.gabezy.propostaapi.domain.models.Usuario;
import br.com.gabezy.propostaapi.domain.view.PropostaDadosView;

public record PropostaMessageDTO(
        Long id,

        double valorSolicitado,

        double renda,

        int prazo,

        Boolean aprovada,

        boolean integrado,

        String observacao,

        Long usuarioId,

        String nome,

        String sobrenome,

        String cpf
) {

    public PropostaMessageDTO(Proposta proposta, Usuario usuario) {
        this(
                proposta.id(),
                proposta.valorSolicitado(),
                usuario.renda(),
                proposta.prazo(),
                proposta.aprovada(),
                proposta.integrado(),
                proposta.observacao(),
                usuario.id(),
                usuario.nome(),
                usuario.sobrenome(),
                usuario.cpf()
        );
    }

    public PropostaMessageDTO(PropostaDadosView propostaDadosView) {
        this(
                propostaDadosView.id(),
                propostaDadosView.valorSolicitado(),
                propostaDadosView.renda(),
                propostaDadosView.prazo(),
                propostaDadosView.aprovada(),
                propostaDadosView.integrada(),
                propostaDadosView.observacao(),
                propostaDadosView.usuarioId(),
                propostaDadosView.nome(),
                propostaDadosView.sobrenome(),
                propostaDadosView.cpf()
        );
    }

}
