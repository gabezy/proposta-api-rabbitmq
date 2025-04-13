package br.com.gabezy.propostaapi.repositories;

import br.com.gabezy.propostaapi.domain.models.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

}
