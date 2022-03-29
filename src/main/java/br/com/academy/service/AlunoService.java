package br.com.academy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academy.model.Aluno;
import br.com.academy.repositorios.AlunoRepositorio;

@Service
public class AlunoService {

    private AlunoRepositorio alunoRepositorio;

    public AlunoService(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public List<Aluno> findAll(){
        return alunoRepositorio.findAll();
    }

    public Aluno findById(Integer id){
        return alunoRepositorio.findById(id).get();
    }

    public Aluno getById(Integer id){
        return alunoRepositorio.getById(id);
    }

    public void delete(Integer id){
        alunoRepositorio.deleteById(findById(id).getId());
    }

    public List<Aluno> findByStatusAtivos(){
        return alunoRepositorio.findByStatusAtivos();
    }

    public List<Aluno> findByStatusInativos(){
        return alunoRepositorio.findByStatusInativos();
    }

    public List<Aluno> findByStatusTrancado(){
        return alunoRepositorio.findByStatusTrancado();
    }

    public List<Aluno> findByStatusCancelado(){
        return alunoRepositorio.findByStatusCancelado();
    }

    public List<Aluno> findByNomeContainingIgnoreCase(String nome){
        return alunoRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Aluno save(Aluno aluno){
        return alunoRepositorio.save(aluno);
    }

    public void replace(Aluno aluno) {
        Aluno savedAluno = findById(aluno.getId());
        aluno.setId(savedAluno.getId());
        alunoRepositorio.save(aluno);
    }

    public boolean AlunoValidacao(Aluno aluno){
        if(aluno.getNome().isEmpty() || aluno.getMatricula().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
