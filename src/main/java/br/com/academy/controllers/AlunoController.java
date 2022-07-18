package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Controlador para gerenciar Alunos")
public class AlunoController {

	private AlunoService alunoService;

	public AlunoController(AlunoService alunoService){
		this.alunoService = alunoService;
	}

	@ApiOperation(value = "Redireciona para a pagina inicial")
	@ApiResponse(code = 200, message = "OK", response = ModelAndView.class)
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@ApiOperation(value = "Redireciona para uma pagina de inserir novos Alunos")
	@ApiResponse(code = 200, message = "OK", response = ModelAndView.class)
	@GetMapping("/inserirAlunos")
	public ModelAndView InsertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@ApiOperation(value = "Insere um novo Aluno no Banco de Dados")
  	@ApiResponses(value = {@ApiResponse(code = 201, message = "Criado/Salvo", response = ModelAndView.class), 
  	@ApiResponse(code = 400, message = "Requisicao Mal Sucedida", response = ModelAndView.class)})
	@PostMapping("InsertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno  aluno, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Aluno/formAluno");
			mv.addObject(aluno);
			mv.setStatus(HttpStatus.BAD_REQUEST);
		}else if(alunoService.AlunoValidacao(aluno)) {	
		mv.setViewName("redirect:/alunos-adicionados");
		alunoService.save(aluno);
		mv.setStatus(HttpStatus.CREATED);
		}else {
			mv = InsertAlunos(aluno);
			mv.setStatus(HttpStatus.BAD_REQUEST);
		}
		return mv;
	}

	@ApiOperation(value = "Redireciona uma pagina com listagem de todos Alunos")
	@ApiResponse(code = 200, message = "OK", response = ModelAndView.class)
	@GetMapping( "alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/listAlunos");
		mv.addObject("alunosList",(alunoService.findAll()));
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Redireciona uma pagina para alterar dados do Aluno")
	@ApiResponse(code = 200, message = "OK", response = ModelAndView.class)
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alterar");
		Aluno aluno = alunoService.findById(id);
		mv.addObject("aluno", aluno);
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Salva as alteracoes feitas nos dados do Aluno e redireciona para pagina de listagem de todos Alunos")
	@ApiResponse(code = 200, message = "OK", response = ModelAndView.class)
	@PostMapping("alterar")
	public ModelAndView alterar(@RequestBody Aluno  aluno) {
		ModelAndView mv = new ModelAndView();
		alunoService.replace(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@ApiOperation(value = "Exclui um Aluno baseando-se no seu ID")
	@ApiResponse(code = 204, message = "Sem Conteudo", response = ModelAndView.class)
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		alunoService.delete(id);
		mv.setViewName("redirect:/alunos-adicionados");
		mv.setStatus(HttpStatus.NO_CONTENT);
		return mv;
	}

	@ApiOperation(value = "Redireciona para uma pagina de filtragem de Alunos")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/filtroAlunos");
		mv.addObject("aluno", new Aluno());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Redireciona para uma pagina de Aluno filtrados por Status Ativo")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-ativos");
		mv.addObject("alunosAtivos", alunoService.findByStatusAtivo());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Redireciona para uma pagina de Alunos filtrados por Status Inativo")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@GetMapping("alunos-inativos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunoService.findByStatusInativo());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Redireciona para uma pagina de Alunos filtrados por Status Trancado")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@GetMapping("alunos-trancados")
	public ModelAndView listaAlunosTrancados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-trancados");
		mv.addObject("alunosTrancados", alunoService.findByStatusTrancado());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@ApiOperation(value = "Redireciona para uma pagina de Alunos filtrados por Status Cancelado")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@GetMapping("alunos-cancelados")
	public ModelAndView listaAlunosCancelados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-cancelados");
		mv.addObject("alunosCancelados", alunoService.findByStatusCancelado());
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@ApiOperation(value = "Redireciona para uma pagina de Alunos baseando-se no nome passando como argumento")
	@ApiResponse(code = 200, message = "Ok", response = ModelAndView.class)
	@PostMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno> listAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listAlunos = alunoService.findAll();
		}else {
			listAlunos = alunoService.findByNomeIgnoreCaseContaining(nome);
		}
		mv.addObject("listaDeAlunos", listAlunos);
		mv.setViewName("Aluno/pesquisa-resultado");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
}
