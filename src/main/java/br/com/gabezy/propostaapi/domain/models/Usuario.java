package br.com.gabezy.propostaapi.domain.models;

import br.com.gabezy.propostaapi.domain.dtos.PropostaRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record Usuario(

    @Id
    Long id,

    @Column
    String nome,

    @Column
    String sobrenome,

    @Column
    String cpf,

    @Column
    String telefone,

    @Column
    Double renda

    // TODO: Verificar pq quando utilizado, fazer um findById traz vários registros se
    // a tabela de propostas tiver mais de um registro com o ID do usuário
//    @MappedCollection(idColumn = "usuario_id")
//    Proposta proposta

) {

    public Usuario(PropostaRequestDTO dto) {
        this(null, dto.nome(), dto.sobrenome(), dto.cpf(), dto.telefone(), dto.renda());
    }

}
