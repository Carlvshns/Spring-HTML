package br.com.academy.repositorios;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import br.com.academy.model.Aluno;
import br.com.academy.util.AlunoCriador;

@DataJpaTest
@DisplayName("Testes de Aluno Repositorio")
public class AlunoRepositorioTest {

    @Autowired
    private AlunoRepositorio alunoRepositorio;

    @Test
    @DisplayName("Salva um Aluno no banco de dados quando bem sucedido")
    void save_SalvaAluno_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        Assertions.assertNotNull(alunoSalvo);
        
        Assertions.assertNotNull(alunoSalvo.getId());
        
        Assertions.assertEquals(alunoSalvo.getNome(), alunoParaSalvar.getNome());
    }

    @Test
    @DisplayName("Procura e retorna uma lista de todos Alunos no banco de dados quando bem sucedido")
    void findAll_RetornaListaDeTodosAlunos_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findAll();

        Assertions.assertEquals("Carlvs", alunos.get(0).getNome());

        Assertions.assertFalse(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna um Aluno do banco de dados com base no seu ID quando bem sucedido")
    void findById_RetornaAluno_ComBaseNoID_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();

        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);

        Aluno aluno = this.alunoRepositorio.findById(alunoSalvo.getId()).get();

        Assertions.assertEquals("Carlvs", aluno.getNome());

        Assertions.assertEquals(Curso.PROGRAMACAO, aluno.getCurso());
    }

    @Test
    @DisplayName("Salva e atualiza dados do Aluno no banco de dados quando bem sucedido")
    void replace_AtualizaAluno_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);

        alunoSalvo.setNome("Edward");

        Aluno alunoAtualizado = this.alunoRepositorio.save(alunoSalvo);

        Assertions.assertNotNull(alunoAtualizado);

        Assertions.assertNotNull(alunoAtualizado.getId());

        Assertions.assertEquals(alunoSalvo.getNome(), alunoAtualizado.getNome());
    }

    @Test
    @DisplayName("Deleta um Aluno quando bem sucedido")
    void delete_RemoveAluno_ComBaseNoID_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        this.alunoRepositorio.delete(alunoSalvo);

        Optional<Aluno> animeDeleted = this.alunoRepositorio.findById(alunoSalvo.getId());

        Assertions.assertTrue(animeDeleted.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista de Alunos quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByNameIgnoringCase_RetornaListaDeAlunos_ComBaseNoNome_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        String nome = alunoSalvo.getNome();

        List<Aluno> alunos = this.alunoRepositorio.findByNomeIgnoreCaseContaining(nome);

        Assertions.assertFalse(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista vazia de Alunos quando nao encontrado")
    void findByNameIgnoringCase_RetornaListaVazia_QuandoAlunoNaoEncontrado(){

        List<Aluno> alunos = this.alunoRepositorio.findByNomeIgnoreCaseContaining("AAAAaaaaAAAAAaaa");

        Assertions.assertTrue(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista de Alunos ativos baseando-se no status ativo registrado no sistema quando bem sucedido")
    void findByStatusAtivos_RetornaListaDeAlunos_ComBaseNoStatusAtivo_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findByStatusAtivo();

        Assertions.assertEquals(alunos.get(0).getStatus(), Status.ATIVO);
    }

    @Test
    @DisplayName("Procura e retorna uma lista de Alunos inativos baseando-se no status inativo registrado no sistema quando bem sucedido")
    void findByStatusInativos_RetornaListaDeAlunos_ComBaseNoStatusInativo_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoInativo();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findByStatusInativo();

        Assertions.assertEquals(alunos.get(0).getStatus(), Status.INATIVO);
    }

    @Test
    @DisplayName("Procura e retorna uma lista de Alunos trancados baseando-se no status trancado registrado no sistema quando bem sucedido")
    void findByStatusTrancado_RetornaListaDeAlunos_ComBaseNoStatusTrancado_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoTrancado();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findByStatusTrancado();

        Assertions.assertEquals(alunos.get(0).getStatus(), Status.TRANCADO);
    }

    @Test
    @DisplayName("Procura e retorna uma lista de Alunos cancelados baseando-se no status cancelado registrado no sistema quando bem sucedido")
    void findByStatusCancelado_RetornaListaDeAlunos_ComBaseNoStatusCancelado_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoCancelado();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findByStatusCancelado();

        Assertions.assertEquals(alunos.get(0).getStatus(), Status.CANCELADO);
    }

}
