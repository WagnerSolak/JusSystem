package com.jussystem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProcuracaoAdJudiciaPF implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataDocumento;
	
	private ClientePessoaFisica clientePessoaFisica;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column
	@Temporal(TemporalType.DATE)
	public Date getDataDocumento() {
		return dataDocumento;
	}
	
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public void setClientePessoaFisica(ClientePessoaFisica clientePessoaFisica) {
		this.clientePessoaFisica = clientePessoaFisica;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "clientePessoaFisica_id")
	public ClientePessoaFisica getClientePessoaFisica() {
		return clientePessoaFisica;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcuracaoAdJudiciaPF other = (ProcuracaoAdJudiciaPF) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
