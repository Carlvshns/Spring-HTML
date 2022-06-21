package br.com.academy.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.academy.model.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Integer>{
	
	@Query("select j from Aluno j where j.status = 'ATIVO' ")
	public List<Aluno> findByStatusAtivo();
	
	@Query("select j from Aluno j where j.status = 'INATIVO' ")
	public List<Aluno> findByStatusInativo();
	
	@Query("select j from Aluno j where j.status = 'CANCELADO' ")
	public List<Aluno> findByStatusCancelado();
	
	@Query("select j from Aluno j where j.status = 'TRANCADO' ")
	public List<Aluno> findByStatusTrancado();
	
	public List<Aluno> findByNomeIgnoreCaseContaining(String nome);
}
