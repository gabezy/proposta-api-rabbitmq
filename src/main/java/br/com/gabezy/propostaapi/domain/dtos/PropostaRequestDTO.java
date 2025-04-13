package br.com.gabezy.propostaapi.domain.dtos;

public record PropostaRequestDTO(

        String nome,

        String sobrenome,

        String telefone,

        String cpf,

        double renda,

        double valorSolicitado,

        int prazoPagamento
) {}
