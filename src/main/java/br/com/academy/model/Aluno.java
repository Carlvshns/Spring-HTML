package br.com.academy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import br.com.academy.Enums.Turno;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Aluno{
	
	public Aluno() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "ID do Aluno", example = "1", required = true)
	private Integer id;
	
	@Column(name = "nome")
	@NotNull(message = "O campo nome nao pode ser nulo")
	@ApiModelProperty(notes = "Nome do Aluno", example = "Carlos Eduardo", required = true)
	private String nome;
	
	@Column(name = "curso")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo curso nao pode ser nulo")
	@ApiModelProperty(notes = "Curso que o Aluno esta matriculado", example = "Programacao", required = true)
	private Curso curso;
	
	@Column(name= "matricula")
	@NotNull(message ="Clique no botão gerar!")
	@ApiModelProperty(notes = "Codigo da Matricula", example = "ACA113", required = true)
	private String matricula;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo status nao pode ser nulo")
	@ApiModelProperty(notes = "Status do Aluno na entidade", example = "Ativo", required = true)
	private Status status;
	
	@Column(name = "turno")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo turno nao pode ser nulo")
	@ApiModelProperty(notes = "Turno que o Aluno frequenta", example = "Matutino", required = true)
	private Turno turno;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public Status getStatus() {
		return status;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
}
