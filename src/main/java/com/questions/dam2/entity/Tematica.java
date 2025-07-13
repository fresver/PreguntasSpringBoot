package com.questions.dam2.entity;

public enum Tematica {

	GEO("Geografía"),
	PRO("Programacón"),
	AST("Astronomía"),
	VID("Videojuegos"),
	CIN("Cine"),
	LIT("Literatura"),
	COM("Comida"),
	OTR("Otra");
	
	private String tematica;

	private Tematica(String tematica) {
		this.tematica = tematica;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

}
