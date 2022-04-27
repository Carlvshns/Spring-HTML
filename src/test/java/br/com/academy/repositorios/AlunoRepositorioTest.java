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
    void save_AlunoESalvo_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        Assertions.assertNotNull(alunoSalvo);
        
        Assertions.assertNotNull(alunoSalvo.getId());
        
        Assertions.assertEquals(alunoSalvo.getNome(), alunoParaSalvar.getNome());
    }

    @Test
    @DisplayName("Procura e retorna uma lista de todos alunos no banco de dados quando bem sucedido")
    void findAll_RetornaListaDeAlunos_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findAll();

        Assertions.assertEquals("Carlvs", alunos.get(0).getNome());

        Assertions.assertFalse(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna um aluno vindo do banco de dados baseando-se no seu ID quando bem sucedido")
    void findById_RetornaAlunoComBaseNoID_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();

        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);

        Aluno aluno = this.alunoRepositorio.findById(alunoSalvo.getId()).get();

        Assertions.assertEquals("Carlvs", aluno.getNome());

        Assertions.assertEquals(Curso.PROGRAMACAO, aluno.getCurso());
    }

    @Test
    @DisplayName("Salva e atualiza dados do aluno no banco de dados quando bem sucedido")
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
    @DisplayName("Deleta aluno quando bem sucedido")
    void delete_RemoveAluno_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        this.alunoRepositorio.delete(alunoSalvo);

        Optional<Aluno> animeDeleted = this.alunoRepositorio.findById(alunoSalvo.getId());

        Assertions.assertTrue(animeDeleted.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista alunos baseando-se no nome ignorando maiusculas e minusculas quando bem sucedido")
    void findByNameIgnoringCase_RetornaListaDeAlunos_QuandoBemSucedido(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        String nome = alunoSalvo.getNome();

        List<Aluno> alunos = this.alunoRepositorio.findByNomeContainingIgnoreCase(nome);

        Assertions.assertFalse(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista vazia de alunos quando nao encontrado")
    void findByNameIgnoringCase_RetornaListaVazia_QuandoAlunoNaoEncontrado(){

        List<Aluno> alunos = this.alunoRepositorio.findByNomeContainingIgnoreCase("AAAAaaaaAAAAAaaa");

        Assertions.assertTrue(alunos.isEmpty());
    }

    @Test
    @DisplayName("Procura e retorna uma lista de alunos baseando-se em seu status ativo quando bem sucedido")
    void findByStatusAtivos(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findByStatusAtivos();

        Assertions.assertEquals(alunos.get(0).getStatus(), Status.ATIVO);
    }

}
