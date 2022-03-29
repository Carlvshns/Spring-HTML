package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;
import br.com.academy.service.AlunoService;

@RestController
public class AlunoController {

	private AlunoService alunoService;

	public AlunoController(AlunoService alunoService){
		this.alunoService = alunoService;
	}
	
	@GetMapping("/inserirAlunos")
	public ModelAndView InsertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	
	@PostMapping("InsertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno  aluno, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Aluno/formAluno");
			mv.addObject(aluno);
		}else if(alunoService.AlunoValidacao(aluno)) {	
		mv.setViewName("redirect:/alunos-adicionados");
		alunoService.save(aluno);
		new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			mv = InsertAlunos(aluno);
		}
		return mv;
	}
	@GetMapping( "alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/listAlunos");
		mv.addObject("alunosList",(alunoService.findAll()));
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alterar");
		Aluno aluno = alunoService.getById(id);
		mv.addObject("aluno", aluno);
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@PostMapping("alterar")
	public ModelAndView alterar(@RequestBody Aluno  aluno) {
		ModelAndView mv = new ModelAndView();
		alunoService.replace(aluno);
		new ResponseEntity<>(HttpStatus.NO_CONTENT);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		alunoService.delete(id);
		new ResponseEntity<>(HttpStatus.NO_CONTENT);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/filtroAlunos");
		mv.addObject("aluno", new Aluno());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-ativos");
		mv.addObject("alunosAtivos", alunoService.findByStatusAtivos());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@GetMapping("alunos-inativos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunoService.findByStatusInativos());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@GetMapping("alunos-cancelados")
	public ModelAndView listaAlunosCancelados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-cancelados");
		mv.addObject("alunosCancelados", alunoService.findByStatusCancelado());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@GetMapping("alunos-trancados")
	public ModelAndView listaAlunosTrancados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-trancados");
		mv.addObject("alunosTrancados", alunoService.findByStatusTrancado());
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
	@PostMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno> listAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listAlunos = alunoService.findAll();
		}else {
			listAlunos = alunoService.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("listaDeAlunos", listAlunos);
		mv.setViewName("Aluno/pesquisa-resultado");
		new ResponseEntity<>(HttpStatus.OK);
		return mv;
	}
}
