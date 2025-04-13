package br.com.gabezy.propostaapi.domain.dtos;

public record PropostaResponseDTO(

        Long id,

        String nome,

        String sobrenome,

        String telefone,

        String cpf,

        double renda,

        String valorSolicitadoFmt,

        int prazoPagamento,

        Boolean aprovada,

        String observacao
) {}
