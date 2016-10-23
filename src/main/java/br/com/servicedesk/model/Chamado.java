package br.com.servicedesk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.servicedesk.dao.ChamadoDao;

@Entity
@Table(name = "chamado")
public class Chamado {
	
	@Column(name="numero_chamado")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long numero;
    private String descricao;
    @Temporal(TemporalType.DATE)
	@Column(name="data_fechamento")
    private Date dataDeFechamento;
    @Temporal(TemporalType.DATE)
	@Column(name="data_abertura")
    private Date dataAbertura;
	@Column(name="status_id")
    private int status;
	@Column(name="fila_id")
    private int fila;
	
	@Transient
	private ChamadoDao dao; 

    public Chamado(long numero, String descricao, Date dataDeFechamento, Date dataAbertura, int status,int fila) {
        this.numero = numero;
        this.descricao = descricao;
        this.dataDeFechamento = dataDeFechamento;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.fila = fila;
        dao = new ChamadoDao();
    }

    public Chamado(String descricao,int status,int fila) {
    	this.descricao = descricao;
        this.dataAbertura = new Date();
        this.status = status;
        this.fila = fila;
        dao = new ChamadoDao();
    }


    public int getFila() {
        return fila;
    }
    
    public Chamado(){
    	
    }

    public void setFila(int fila) {
        this.fila = fila;
    }


    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeFechamento() {
        return dataDeFechamento;
    }

    public void setDataDeFechamento(Date dataDeFechamento) {
        this.dataDeFechamento = dataDeFechamento;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Chamado create(){
    	return dao.create(this);
    }
    
    public int closeById(){
    	return dao.closeById(this);
    }
    
    public ArrayList<Chamado> listByFilaId(){
    	return dao.listByFilaId(this.getFila());
    }

}

