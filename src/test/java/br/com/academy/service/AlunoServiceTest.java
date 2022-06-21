package br.com.academy.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
}
