package br.com.gabezy.propostaapi.domain.view;

public record PropostaDadosView(
        Long id,

        String nome,

        String sobrenome,

        String telefone,

        String cpf,

        double renda,

        double valorSolicitado,

        boolean integrada,

        int prazo,

        Boolean aprovada,

        String observacao,

        Long usuarioId
) {
}
