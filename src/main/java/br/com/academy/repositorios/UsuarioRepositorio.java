package br.com.academy.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.academy.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	@Query("select i from Usuario i where  i.email = :email")
	public Usuario findByEmail(@Param("email") String email);
	
	@Query("select j from Usuario j where  j.user = :user and j.senha = :senha")
	public Usuario buscarLogin(@Param("user") String user, @Param("senha")String senha);

}