package br.com.gabezy.propostaapi.repositories;

import br.com.gabezy.propostaapi.domain.models.Proposta;
import br.com.gabezy.propostaapi.domain.view.PropostaDadosView;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends ListCrudRepository<Proposta, Long> {


    @Query("SELECT p.id, u.nome, u.sobrenome, u.telefone, u.cpf, u.renda, p.valor_solicitado," +
            " p.prazo, p.aprovada, p.observacao " +
            "FROM proposta p " +
            "JOIN usuario u ON p.usuario_id = u.id")
    List<PropostaDadosView> findAllPropostaDadosView();

}
