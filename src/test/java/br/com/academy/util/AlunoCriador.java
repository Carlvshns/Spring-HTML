package br.com.academy.util;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import br.com.academy.Enums.Turno;
import br.com.academy.model.Aluno;

public class AlunoCriador {
    
    public static Aluno CriaAlunoParaSalvar(){
        Aluno aluno = new Aluno();
        Curso curso = Curso.PROGRAMACAO;
        Status status = Status.ATIVO;
        Turno turno = Turno.MATUTINO;
        aluno.setId(1);
        aluno.setNome("Carlvs");
        aluno.setCurso(curso);
        aluno.setMatricula("ACA1234");
        aluno.setStatus(status);
        aluno.setTurno(turno);
        return aluno;
    }

    public static Aluno CriaAlunoInativo(){
        Aluno aluno = new Aluno();
        Curso curso = Curso.PROGRAMACAO;
        Status status = Status.INATIVO;
        Turno turno = Turno.MATUTINO;
        aluno.setId(1);
        aluno.setNome("Edward");
        aluno.setCurso(curso);
        aluno.setMatricula("ACA1234");
        aluno.setStatus(status);
        aluno.setTurno(turno);
        return aluno;
    }

    public static Aluno CriaAlunoTrancado(){
        Aluno aluno = new Aluno();
        Curso curso = Curso.PROGRAMACAO;
        Status status = Status.TRANCADO;
        Turno turno = Turno.MATUTINO;
        aluno.setId(1);
        aluno.setNome("Edward");
        aluno.setCurso(curso);
        aluno.setMatricula("ACA1234");
        aluno.setStatus(status);
        aluno.setTurno(turno);
        return aluno;
    }

    public static Aluno CriaAlunoCancelado(){
        Aluno aluno = new Aluno();
        Curso curso = Curso.PROGRAMACAO;
        Status status = Status.CANCELADO;
        Turno turno = Turno.MATUTINO;
        aluno.setId(1);
        aluno.setNome("Edward");
        aluno.setCurso(curso);
        aluno.setMatricula("ACA1234");
        aluno.setStatus(status);
        aluno.setTurno(turno);
        return aluno;
    }
}
