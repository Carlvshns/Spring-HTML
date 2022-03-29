package br.com.academy.Enums;

public enum Turno {
	MATUTINO("Matutino"),
	VESPERTINO("Vespertino"),
	NOTURNO("Noturno");
	
	private String turno;
	
	private Turno(String turno) {
		this.setTurno(turno);
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
}
