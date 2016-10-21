package br.com.servicedesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_chamado")
public class Status {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long idStatus;
	@Column(name = "status_nome")
	private String nomeStatus;
	
	public Status(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	public Status(){
	}
 
	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}
	
}
