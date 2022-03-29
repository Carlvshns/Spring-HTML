package br.com.academy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import br.com.academy.Enums.Turno;

@Entity
public class Aluno{
	
	public Aluno() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nome")
	@NotEmpty(message = "O campo status nao pode ser nulo")
	private String nome;
	
	@Column(name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@Column(name= "matricula")
	@NotEmpty(message ="Clique no botão gerar!")
	private String matricula;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotEmpty(message = "O campo status nao pode ser nulo")
	private Status status;
	
	@Column(name = "turno")
	@Enumerated(EnumType.STRING)
	@NotEmpty(message = "O campo turno nao pode ser nulo")
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
