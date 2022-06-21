package br.com.academy.controllers;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;
import br.com.academy.service.AlunoService;
import br.com.academy.util.AlunoCriador;

@ExtendWith(SpringExtension.class)
public class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoController;
    @Mock
    private AlunoService alunoServiceMock;

    @BeforeEach
    void setup(){

        List<Aluno> listaAlunos = List.of(AlunoCriador.CriaAlunoParaSalvar());
        List<Aluno> listaAlunosInativos = List.of(AlunoCriador.CriaAlunoInativo());
        List<Aluno> listaAlunosTrancados = List.of(AlunoCriador.CriaAlunoTrancado());
        List<Aluno> listaAlunoscancelados = List.of(AlunoCriador.CriaAlunoCancelado());

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any(Aluno.class)))
        .thenReturn(AlunoCriador.CriaAlunoParaSalvar());

        BDDMockito.when(alunoServiceMock.findAll())
        .thenReturn(listaAlunos);

        BDDMockito.when(alunoServiceMock.findById(ArgumentMatchers.anyInt()))
        .thenReturn(AlunoCriador.CriaAlunoParaSalvar());

        BDDMockito.doNothing().when(alunoServiceMock).delete(ArgumentMatchers.anyInt());

        BDDMockito.when(alunoServiceMock.findByNomeIgnoreCaseContaining(ArgumentMatchers.anyString()))
        .thenReturn(listaAlunos);

        BDDMockito.when(alunoServiceMock.findByStatusAtivo()).thenReturn(listaAlunos);

        BDDMockito.when(alunoServiceMock.findByStatusInativo()).thenReturn(listaAlunosInativos);

        BDDMockito.when(alunoServiceMock.findByStatusTrancado()).thenReturn(listaAlunosTrancados);

        BDDMockito.when(alunoServiceMock.findByStatusCancelado()).thenReturn(listaAlunoscancelados);
    }

    @Test
    @DisplayName("index retorna um ModelAndView que redireciona para um arquivo html inicial no frontend quando bem sucedido")
    void index_RetornaModelAndView_ERedirecionaParaHtmlInicial(){
        ModelAndView mv = alunoController.index();
        Assertions.assertDoesNotThrow(() -> alunoController.index());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("InsertAlunos retorna um ModelAndView que redireciona para um arquivo html no frontend de inserir Aluno quando bem sucedido")
    void InsertAlunos_RetornaModelAndView_ERedirecionaParaHtmlDeInserirAlunos(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        Assertions.assertDoesNotThrow(() -> alunoController.InsertAlunos(aluno));
        ModelAndView mv = alunoController.InsertAlunos(aluno);

        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("InserirAluno retorna um ModelAndView que redireciona para um arquivo html no frontend de alunos ja inseridos quando bem sucedido, mas um nullPointerException é lancado por nao ter o bilding result")
    void InserirAluno_RetornaModelAndView_ERedirecionaParaHtmlDeAlunosJaInseridos(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        Assertions.assertThrows(NullPointerException.class, () -> alunoController.inserirAluno(aluno, ArgumentMatchers.any()));
    }

    @Test
    @DisplayName("listagemAlunos retorna um ModelAndView que redireciona para um arquivo html no frontend de todos alunos ja inseridos quando bem sucedido")
    void InserirAluno_RetornaModelAndView_ERedirecionaParaHtmlDeTodosAlunosJaInseridos(){
        Assertions.assertDoesNotThrow(() -> alunoController.listagemAlunos());
        ModelAndView mv = alunoController.listagemAlunos();

        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("alterar retorna um ModelAndView que redireciona para um arquivo html no frontend de alterar Aluno com base no ID quando bem sucedido")
    void alterar_RetornaModelAndView_ERedirecionaParaHtmlDeAlterarAlunoComBaseNoID(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        Assertions.assertDoesNotThrow(() -> alunoController.alterar(aluno.getId()));
        ModelAndView mv = alunoController.alterar(aluno.getId());

        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("alterar atualiza um Aluno e retorna um ModelAndView que redireciona para um arquivo html no frontend de alterar Aluno quando bem sucedido")
    void alterar_RetornaModelAndView_ERedirecionaParaHtmlDeAlterarAluno(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        Assertions.assertDoesNotThrow(() -> alunoController.alterar(aluno));
        ModelAndView mv = alunoController.alterar(aluno);

        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("excluirAluno remove um Aluno e retorna um ModelAndView que redireciona para um arquivo html no frontend de todos Alunos ja inseridos quando bem sucedido")
    void excluirAluno_RemoveAluno_ComBaseNoID_ERetornaModelAndView_ERedirecionaParaHtmlDeAlunosJaInseridos(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        Assertions.assertDoesNotThrow(() -> alunoController.excluirAluno(aluno.getId()));
        ModelAndView mv = alunoController.excluirAluno(aluno.getId());

        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("filtroAlunos retorna um ModelAndView que redireciona para um arquivo html com Alunos para filtrar no frontend quando bem sucedido")
    void filtroAlunos_RetornaModelAndView_ERedirecionaParaHtmlComAlunosParaFiltrar(){
        ModelAndView mv = alunoController.filtroAlunos();
        Assertions.assertDoesNotThrow(() -> alunoController.filtroAlunos());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("listaAlunosAtivos retorna um ModelAndView com base no status que redireciona para um arquivo html com Alunos ativos no frontend quando bem sucedido")
    void listaAlunosAtivos_RetornaModelAndView__ComBaseNoStatus_ERedirecionaParaHtmlComAlunosAtivos(){
        ModelAndView mv = alunoController.listaAlunosAtivos();
        Assertions.assertDoesNotThrow(() -> alunoController.listaAlunosAtivos());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("listaAlunosInativos retorna um ModelAndView com base no status que redireciona para um arquivo html com Alunos inativos no frontend quando bem sucedido")
    void listaAlunosInativos_RetornaModelAndView__ComBaseNoStatus_ERedirecionaParaHtmlComAlunosInativos(){
        ModelAndView mv = alunoController.listaAlunosInativos();
        Assertions.assertDoesNotThrow(() -> alunoController.listaAlunosInativos());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("listaAlunosTrancados retorna um ModelAndView com base no status que redireciona para um arquivo html com Alunos trancados no frontend quando bem sucedido")
    void listaAlunosTrancados_RetornaModelAndView__ComBaseNoStatus_ERedirecionaParaHtmlComAlunosTrancados(){
        ModelAndView mv = alunoController.listaAlunosTrancados();
        Assertions.assertDoesNotThrow(() -> alunoController.listaAlunosTrancados());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("listaAlunosCancelados retorna um ModelAndView com base no status que redireciona para um arquivo html com Alunos cancelados no frontend quando bem sucedido")
    void listaAlunosCancelados_RetornaModelAndView_ComBaseNoStatus_ERedirecionaParaHtmlComAlunosCancelados(){
        ModelAndView mv = alunoController.listaAlunosCancelados();
        Assertions.assertDoesNotThrow(() -> alunoController.listaAlunosCancelados());
        Assertions.assertNotNull(mv);
    }

    @Test
    @DisplayName("pesquisarAluno retorna um ModelAndView com base no nome pesquisado que redireciona para um arquivo html com Alunos resultado da pesquisa no frontend quando bem sucedido")
    void pesquisarAluno_RetornaModelAndView_ComBaseNoNome_ERedirecionaParaHtmlComAlunosResultadosDaPesquisa(){
        Aluno aluno = AlunoCriador.CriaAlunoParaSalvar();
        ModelAndView mv = alunoController.pesquisarAluno(aluno.getNome());
        Assertions.assertDoesNotThrow(() -> alunoController.pesquisarAluno(aluno.getNome()));
        Assertions.assertNotNull(mv);
    }
}
