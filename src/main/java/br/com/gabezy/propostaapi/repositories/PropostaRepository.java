package br.com.gabezy.propostaapi.repositories;

import br.com.gabezy.propostaapi.domain.models.Proposta;
import br.com.gabezy.propostaapi.domain.view.PropostaDadosView;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PropostaRepository extends ListCrudRepository<Proposta, Long> {

    @Query("SELECT p.id, u.nome, u.sobrenome, u.telefone, u.cpf, u.renda, p.valor_solicitado, p.integrado as integrada," +
            " p.prazo, p.aprovada, p.observacao, u.id as usuarioId " +
            " FROM proposta p " +
            " JOIN usuario u ON p.usuario_id = u.id")
    List<PropostaDadosView> findAllPropostaDadosView();

    @Modifying
    @Transactional
    @Query("UPDATE proposta SET integrado = :integrado WHERE id = :id")
    void updateIntegradoById(@Param("integrado") boolean integrado, @Param("id") Long id);

    @Query("SELECT p.id, u.nome, u.sobrenome, u.telefone, u.cpf, u.renda, p.valor_solicitado, p.integrado as integrada," +
            " p.prazo, p.aprovada, p.observacao, u.id as usuarioId " +
            " FROM proposta p " +
            " JOIN usuario u ON p.usuario_id = u.id " +
            " WHERE p.integrado = false")
    List<PropostaDadosView> findAllPropostaDadosViewByIntegradoIsFalse();

}
