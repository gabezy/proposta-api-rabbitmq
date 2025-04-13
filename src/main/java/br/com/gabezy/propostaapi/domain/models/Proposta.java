package br.com.gabezy.propostaapi.domain.models;

import br.com.gabezy.propostaapi.domain.dtos.PropostaRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record Proposta(

    @Id
    Long id,

    @Column
    Double valorSolicitado,

    @Column
    int prazo,

    @Column
    Boolean aprovada,

    @Column
    boolean integrado,

    @Column
    String observacao,

     Long usuarioId

) {

    public Proposta(PropostaRequestDTO dto, Long usuarioId) {
        this(null, dto.valorSolicitado(), dto.prazoPagamento(), null, false, null, usuarioId);
    }

}
