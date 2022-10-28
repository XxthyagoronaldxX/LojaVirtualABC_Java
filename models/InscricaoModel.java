package com.abctreinamentos.lojavirtual.models;

import java.time.LocalDateTime;

public class InscricaoModel {
	private int id;
	private ClienteModel cliente;
	private CursoModel curso;
	private LocalDateTime created_at;

	public InscricaoModel(int id, ClienteModel cliente, CursoModel curso, LocalDateTime created_at) {
		this.id = id;
		this.cliente = cliente;
		this.curso = curso;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public CursoModel getCurso() {
		return curso;
	}

	public void setCurso(CursoModel curso) {
		this.curso = curso;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

}
