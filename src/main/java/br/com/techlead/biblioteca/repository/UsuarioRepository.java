package br.com.techlead.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.techlead.biblioteca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);

	@Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.login = :username")
    Usuario findByUsernameFetchRoles(@Param("username") String username);
}
