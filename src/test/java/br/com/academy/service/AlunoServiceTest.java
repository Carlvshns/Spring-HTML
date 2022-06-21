package br.com.academy.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.academy.model.Aluno;
import br.com.academy.repositorios.AlunoRepositorio;
import br.com.academy.util.AlunoCriador;

@ExtendWith(SpringExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;
    @Mock
    private AlunoRepositorio alunoRepositoryMock;

    @BeforeEach
    void setup(){

        List<Aluno> listaAlunos = List.of(AlunoCriador.CriaAlunoParaSalvar());
        List<Aluno> listaAlunosInativos = List.of(AlunoCriador.CriaAlunoInativo());
        List<Aluno> listaAlunosTrancados = List.of(AlunoCriador.CriaAlunoTrancado());
        List<Aluno> listaAlunoscancelados = List.of(AlunoCriador.CriaAlunoCancelado());

        BDDMockito.when(alunoRepositoryMock.save(ArgumentMatchers.any(Aluno.class)))
        .thenReturn(AlunoCriador.CriaAlunoParaSalvar());

        BDDMockito.when(alunoRepositoryMock.findAll())
        .thenReturn(listaAlunos);

        BDDMockito.when(alunoRepositoryMock.findById(ArgumentMatchers.anyInt()))
        .thenReturn(Optional.of(AlunoCriador.CriaAlunoParaSalvar()));

        BDDMockito.doNothing().when(alunoRepositoryMock).delete(ArgumentMatchers.any(Aluno.class));

        BDDMockito.when(alunoRepositoryMock.findByNomeIgnoreCaseContaining(ArgumentMatchers.anyString()))
        .thenReturn(listaAlunos);

        BDDMockito.when(alunoRepositoryMock.findByStatusAtivo()).thenReturn(listaAlunos);

        BDDMockito.when(alunoRepositoryMock.findByStatusInativo()).thenReturn(listaAlunosInativos);

        BDDMockito.when(alunoRepositoryMock.findByStatusTrancado()).thenReturn(listaAlunosTrancados);

        BDDMockito.when(alunoRepositoryMock.findByStatusCancelado()).thenReturn(listaAlunoscancelados);
    }

    @Test
    @DisplayName("Save salva um Aluno no banco de dados quando bem sucedido")
    void save_SalvaAluno_QuandoBemSucedido() {
        Aluno alunoEsperado = AlunoCriador.CriaAlunoParaSalvar();
        Aluno aluno = alunoService.save(alunoEsperado);

        Assertions.assertNotNull(aluno);

        Assertions.assertEquals(alunoEsperado.getId(), AlunoCriador.CriaAlunoParaSalvar().getId());

        Assertions.assertEquals(alunoEsperado.getNome(), aluno.getNome());
    }

    @Test
    @DisplayName("findAll retorna uma lista de todos Alunos quando bem sucedido")
    void findAll_RetornaListaDeTodosAlunos_QuandoBemSucedido() {
        String nomeEsperadoAluno = AlunoCriador.CriaAlunoParaSalvar().getNome();
        List<Aluno> listaAlunos = alunoService.findAll();

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(1, listaAlunos.size());

        Assertions.assertEquals(nomeEsperadoAluno, listaAlunos.get(0).getNome());
    }

    @Test
    @DisplayName("findById procura e retorna um Aluno do banco de dados com base em seu ID quando bem sucedido")
    void findById_RetornaAluno_ComBaseNoID_QuandoBemSucedido() {
        Aluno alunoEsperado = AlunoCriador.CriaAlunoParaSalvar();
        Aluno aluno = alunoService.findById(1);

        Assertions.assertNotNull(aluno);

        Assertions.assertEquals(alunoEsperado.getId(), aluno.getId());

        Assertions.assertEquals(alunoEsperado.getNome(), aluno.getNome());
    }

    @Test
    @DisplayName("delete remove um Aluno com base em seu ID quando bem sucedido")
    void delete_removeAluno_ComBaseNoID_QuandoBemSucedido() {
        Aluno alunoEsperado = AlunoCriador.CriaAlunoParaSalvar();

        Assertions.assertDoesNotThrow(() -> alunoService.delete(alunoEsperado.getId()));
    }

    @Test
    @DisplayName("findByNameIgnoringCase procura e retorna uma lista de Alunos quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByNameIgnoringCase_RetornaListaDeAlunos_ComBaseNoNome_QuandoBemSucedido() {
        String nomeEsperadoAluno = AlunoCriador.CriaAlunoParaSalvar().getNome();
        List<Aluno> listaAlunos = alunoService.findByNomeIgnoreCaseContaining(nomeEsperadoAluno);

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(1, listaAlunos.size());

        Assertions.assertEquals(nomeEsperadoAluno, listaAlunos.get(0).getNome());
    }

    @Test
    @DisplayName("findByStatusAtivo procura e retorna uma lista de Alunos com base no status ativo quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByStatusAtivo_RetornaListaDeAlunos_ComBaseNoStatusAtivo_QuandoBemSucedido() {
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        List<Aluno> listaAlunos = alunoService.findByStatusAtivo();

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(aluno.getStatus(), listaAlunos.get(0).getStatus());

        Assertions.assertEquals(aluno.getNome(), listaAlunos.get(0).getNome());
    }

    @Test
    @DisplayName("findByStatusInativo procura e retorna uma lista de Alunos com base no status inativo quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByStatusInativo_RetornaListaDeAlunos_ComBaseNoStatusInativo_QuandoBemSucedido() {
        Aluno aluno = AlunoCriador.CriaAlunoInativo();
        List<Aluno> listaAlunos = alunoService.findByStatusInativo();

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(aluno.getStatus(), listaAlunos.get(0).getStatus());

        Assertions.assertEquals(aluno.getNome(), listaAlunos.get(0).getNome());
    }

    @Test
    @DisplayName("findByStatusTrancado procura e retorna uma lista de Alunos com base no status trancado quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByStatusTrancado_RetornaListaDeAlunos_ComBaseNoStatusTrancado_QuandoBemSucedido() {
        Aluno aluno = AlunoCriador.CriaAlunoTrancado();
        List<Aluno> listaAlunos = alunoService.findByStatusTrancado();

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(aluno.getStatus(), listaAlunos.get(0).getStatus());

        Assertions.assertEquals(aluno.getNome(), listaAlunos.get(0).getNome());
    }

    @Test
    @DisplayName("findByStatusCancelado procura e retorna uma lista de Alunos com base no status cancelado quando bem sucedido com base em seu nome ignorando maiusculas e minusculas")
    void findByStatusCancelado_RetornaListaDeAlunos_ComBaseNoStatusCancelado_QuandoBemSucedido() {
        Aluno aluno = AlunoCriador.CriaAlunoCancelado();
        List<Aluno> listaAlunos = alunoService.findByStatusCancelado();

        Assertions.assertNotNull(listaAlunos);

        Assertions.assertFalse(listaAlunos.isEmpty());

        Assertions.assertEquals(aluno.getStatus(), listaAlunos.get(0).getStatus());

        Assertions.assertEquals(aluno.getNome(), listaAlunos.get(0).getNome());
    }
}
