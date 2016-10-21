package br.com.servicedesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fila")
public class Fila {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fila_id")
	private Long idFila;
	@Column(name="fila_nome")
	private String nomeFila;
		
	public Fila(){
		
	}
	
	public Fila(long idFila){
		this.idFila = idFila;
	}
	
	
	public Long getIdFila() {
		return idFila;
	}

	public void setIdFila(Long idFila) {
		this.idFila = idFila;
	}

	public String getNomeFila() {
		return nomeFila;
	}

	public void setNomeFila(String nomeFila) {
		this.nomeFila = nomeFila;
	}
}
