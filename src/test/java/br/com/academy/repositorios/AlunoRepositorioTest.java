package br.com.academy.repositorios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.academy.model.Aluno;
import br.com.academy.util.AlunoCriador;

@DataJpaTest
@DisplayName("Tests de Aluno Repositorio")
public class AlunoRepositorioTest {

    @Autowired
    private AlunoRepositorio alunoRepositorio;

    @Test
    @DisplayName("Salva aluno no banco de dados quando bem sucedido")
    void save_PersistAnime_WhenSucessful(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        Aluno alunoSalvo = this.alunoRepositorio.save(alunoParaSalvar);
        
        Assertions.assertNotNull(alunoSalvo);
        
        Assertions.assertNotNull(alunoSalvo.getId());
        
        Assertions.assertEquals(alunoSalvo.getNome(), alunoParaSalvar.getNome());
    }

    @Test
    @DisplayName("findAll retorna uma lista de alunos quando bem sucedido")
    void findByName_ReturnsListOfAnime_WhenSucessful(){
        Aluno alunoParaSalvar = AlunoCriador.CriaAlunoParaSalvar();
        
        this.alunoRepositorio.save(alunoParaSalvar);

        List<Aluno> alunos = this.alunoRepositorio.findAll();

        Assertions.assertEquals("Carlvs", alunos.get(0).getNome());

        Assertions.assertFalse(alunos.isEmpty());
    }

}
